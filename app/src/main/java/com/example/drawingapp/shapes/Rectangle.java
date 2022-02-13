package com.example.drawingapp.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class Rectangle {

    //rect abcd
    //a b
    //c d
    RectF rectangle;
    Paint paint;

    public Rectangle(float aX, float aY, float cX, float cY, Paint paint) {
        this.paint = paint;
        rectangle = new RectF(aX, aY, cX, cY);
    }

    public void drawRect(Canvas canvas) {
        canvas.drawRect(rectangle, paint);
    }
}
