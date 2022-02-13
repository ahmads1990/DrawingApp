package com.example.drawingapp.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;


public class Line {

    private Path path;
    private Paint paint;

    public Line(Path path, Paint paint) {
        this.path = path;
        this.paint = paint;
    }

    public void drawLine(Canvas canvas) {
        canvas.drawPath(path, paint);
    }
}
