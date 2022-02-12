package com.example.drawingapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class myCanvas extends View {


    public enum brushTypes{
        DOT,
        LINE,
        RECT,
        CIRCLE
    }
    brushTypes currentBrushType;
    Paint paint;
    Path path;


    //points for drawing shapes
    PointF startPoint;
    PointF endPoint;


    public myCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        Log.d("mytag", "started here");
        currentBrushType = brushTypes.DOT;

        startPoint = new PointF();
        endPoint = new PointF();
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

        Log.d("mytag", "current " + currentBrushType.toString());
        switch (currentBrushType){
            case DOT:
                Log.d("mytag", "0");
                canvas.drawPath(path, paint);
                break;
            case LINE:
                Log.d("mytag", "1");
                canvas.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y, paint);
                break;
            case RECT:
                break;

            case CIRCLE:
                break;

            default:
                break;
        }
    }

    private void brushDrawLine(Canvas canvas) {

        canvas.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y, paint);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x,y);
                startPoint = new PointF(x,y);
                break;

            case MotionEvent.ACTION_MOVE:
                path.lineTo(x,y);
                break;

            case MotionEvent.ACTION_UP:
                endPoint = new PointF(x,y);
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
                Log.d("mytag","state " + currentBrushType.toString());
                break;
            case 1:
                currentBrushType = brushTypes.LINE;
                Log.d("mytag", "changed line");
                Log.d("mytag","state " + currentBrushType.toString());
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
    }
}
