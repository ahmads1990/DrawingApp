package com.example.drawingapp.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class Circle {


    //rect
    RectF oval;
    Paint paint;

    public Circle(float aX, float aY, float cX, float cY, Paint paint) {
        this.paint = paint;
        oval = new RectF(aX, aY, cX, cY);
    }

    public void drawCircle(Canvas canvas) {
        canvas.drawOval(oval, paint);
    }
}
