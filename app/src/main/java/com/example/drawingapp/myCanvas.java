package com.example.drawingapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.drawingapp.shapes.Arrow;
import com.example.drawingapp.shapes.Circle;
import com.example.drawingapp.shapes.Line;
import com.example.drawingapp.shapes.Rectangle;

import java.util.ArrayList;


public class myCanvas extends View {

    static brushTypes currentBrushType;
    static Paint paint;
    Path path;
    //shapes
    ArrayList<Line> lineArrayList;
    ArrayList<Arrow> arrowArrayList;
    ArrayList<Rectangle> rectArrayList;
    ArrayList<Circle> circleArrayList;
    //points for drawing shapes
    PointF startPoint;
    PointF endPoint;
    boolean doneDrawing = false;
    public myCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        Log.d("mytag", "started here");
        currentBrushType = brushTypes.DOT;

        startPoint = new PointF();
        endPoint = new PointF();

        lineArrayList = new ArrayList<>();
        arrowArrayList = new ArrayList<>();
        rectArrayList = new ArrayList<>();
        circleArrayList = new ArrayList<>();

        paint = new Paint();
        path = new Path();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int i = 1;

        //
        Log.d("mytag", "current " + currentBrushType.toString());

        if (doneDrawing) {
            switch (currentBrushType) {
                case DOT:
                    Log.d("mytag", "0");
                    //canvas.drawPath(path, paint);
                    //   lineArrayList.add(new Line(path, paint));
                    break;
                case LINE:
                    Log.d("mytag", "1");
                    arrowArrayList.add(new Arrow(startPoint.x, startPoint.y, endPoint.x, endPoint.y, new Paint(paint)));
                    break;
                case RECT:
                    Log.d("mytag", "2");
                    rectArrayList.add(new Rectangle(startPoint.x, startPoint.y, endPoint.x, endPoint.y, new Paint(paint)));
                    break;

                case CIRCLE:
                    Log.d("mytag", "3");
                    circleArrayList.add(new Circle(startPoint.x, startPoint.y, endPoint.x, endPoint.y, new Paint(paint)));
                    break;

                default:
                    break;
            }
        }
        for (Line line : lineArrayList) {
            //Log.d("mytag1", "draw " + i);
            i++;
            line.drawLine(canvas);
        }
        for (Arrow arrow : arrowArrayList) {
            arrow.drawArrow(canvas);
        }
        for (Rectangle rec : rectArrayList) {
            rec.drawRect(canvas);
        }
        for (Circle circle : circleArrayList) {
            circle.drawCircle(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path = new Path();
                //on key down move the path start to x,y
                path.moveTo(x, y);
                startPoint.x = x;
                startPoint.y = y;


                //add path to arraylist
                if (currentBrushType == brushTypes.DOT) {
                    lineArrayList.add(new Line(path, new Paint(paint)));
                }
                doneDrawing = false;
                break;

            case MotionEvent.ACTION_MOVE:
                //on key movement add the new points to path
                path.lineTo(x, y);
                break;

            case MotionEvent.ACTION_UP:
                //on key up the drawing is finished
                //set end point x y
                endPoint.x = x;
                endPoint.y = y;
                doneDrawing = true;
                break;

            default:
                return false;
        }

        invalidate();
        return true;
    }

    public void changeBrush(int i) {

        //change brush type based on the order of the tab
        switch (i) {
            case 0:
                currentBrushType = brushTypes.DOT;
                Log.d("mytag", "changed dot");
                break;
            case 1:
                currentBrushType = brushTypes.LINE;
                Log.d("mytag", "changed line");
                break;
            case 2:
                currentBrushType = brushTypes.RECT;
                break;
            case 3:
                currentBrushType = brushTypes.CIRCLE;
                break;
            default:
                Log.d("mytag", "default");
                break;
        }
        Log.d("mytag", "state " + currentBrushType.toString());
    }

    public void changeColor(int i) {
        switch (i) {
            case 0:
                paint.setColor(Color.RED);
                break;
            case 1:
                paint.setColor(Color.GREEN);
                break;
            case 2:
                paint.setColor(Color.BLUE);
                break;
            case 3:
                paint.setColor(Color.BLACK);
                break;
            default:
                paint.setColor(Color.BLACK);
                break;
        }
    }

    public enum brushTypes {
        DOT,
        LINE,
        RECT,
        CIRCLE
    }


}
