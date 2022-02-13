package com.example.drawingapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.drawingapp.shapes.Arrow;
import com.example.drawingapp.shapes.Circle;
import com.example.drawingapp.shapes.Rectangle;

import java.util.ArrayList;


public class myCanvas extends View {


    public enum brushTypes{
        DOT,
        LINE,
        RECT,
        CIRCLE
    }
    static brushTypes currentBrushType;
    Paint paint;
    Path path;

    ArrayList<Path> pathArrayList;
    //shapes
    ArrayList<Arrow> lineArrayList;
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

        pathArrayList = new ArrayList<>();
        lineArrayList = new ArrayList<>();
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

        int i =1;

        //
        Log.d("mytag", "current " + currentBrushType.toString());

        if (doneDrawing) {
            switch (currentBrushType) {
                case DOT:
                    Log.d("mytag", "0");
                    //canvas.drawPath(path, paint);
                    break;
                case LINE:
                    Log.d("mytag", "1");
                    lineArrayList.add(new Arrow(startPoint.x, startPoint.y, endPoint.x, endPoint.y, paint));
                    break;
                case RECT:
                    Log.d("mytag", "2");
                    rectArrayList.add(new Rectangle(startPoint.x, startPoint.y, endPoint.x, endPoint.y, paint));
                    break;

                case CIRCLE:
                    Log.d("mytag", "3");
                    circleArrayList.add(new Circle(startPoint.x, startPoint.y, endPoint.x, endPoint.y, paint));
                    break;

                default:
                    break;
            }
        }
        for (Path path : pathArrayList) {
            //Log.d("mytag1", "draw " + i);
            i++;
            canvas.drawPath(path, paint);
        }
        for (Arrow lin : lineArrayList){
            lin.drawArrow(canvas);
        }
        for (Rectangle rec: rectArrayList){
            rec.drawRect(canvas);
        }
        for (Circle circle: circleArrayList){
            circle.drawCircle(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path = new Path();
                //on key down move the path start to x,y
                path.moveTo(x, y);
                startPoint.x=x;
                startPoint.y=y;

                //add path to arraylist
                if (currentBrushType == brushTypes.DOT) {
                    Log.d("mytag1", "h1h1");
                    pathArrayList.add(path);
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
                endPoint.x=x;
                endPoint.y=y;
                doneDrawing = true;
                break;

            default:
                return false;
        }

        invalidate();
        return true;
    }


    public void changeBrush(int i){

        //change brush type based on the order of the tab
        switch (i){
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
        Log.d("mytag","state " + currentBrushType.toString());
    }
}
