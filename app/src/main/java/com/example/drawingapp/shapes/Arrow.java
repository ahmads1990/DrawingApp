package com.example.drawingapp.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Arrow {

    //length of the edge = 0.4 the length of the arrow
    private final static float arrowEdgeLen = 0.4f;
    private final static float angle = 30f;
    //paint information
    Paint paint;
    private float startX, startY, endX, endY;
    //edge left and right points
    //x->[0] y->[1]
    private float edgeRight[];
    private float edgeLeft[];

    public Arrow(float startX, float startY, float stopX, float stopY, Paint paint) {
        this.startX = startX;
        this.startY = startY;
        this.endX = stopX;
        this.endY = stopY;
        this.paint = paint;

        edgeLeft = new float[]{0f, 0f};
        edgeRight = new float[]{0f, 0f};
        initEdges();
    }

    private void initEdges() {
        //x1,y1 start point and x2,y2 end point
        //x3=x2+L2L1[(x1−x2)cosθ+(y1−y2)sinθ]
        //y3=y2+L2L1[(y1−y2)cosθ−(x1−x2)sinθ]
        //x4=x2+L2L1[(x1−x2)cosθ−(y1−y2)sinθ]
        //y4=y2+L2L1[(y1−y2)cosθ+(x1−x2)sinθ]

        //left edge
        edgeLeft[0] = endX + (arrowEdgeLen * (float) (((startX - endX) * Math.cos(angle)) + ((startY - endY) * Math.cos(angle))));
        edgeLeft[1] = endY + (arrowEdgeLen * (float) (((startY - endY) * Math.cos(angle)) - ((startX - endX) * Math.cos(angle))));

        //right edge
        edgeRight[0] = endX + (arrowEdgeLen * (float) (((startX - endX) * Math.cos(angle)) - ((startY - endY) * Math.cos(angle))));
        edgeRight[1] = endY + (arrowEdgeLen * (float) (((startY - endY) * Math.cos(angle)) + ((startX - endX) * Math.cos(angle))));
    }

    public void drawArrow(Canvas canvas) {
        //draw arrow body
        canvas.drawLine(startX, startY, endX, endY, paint);
        //draw edges
        canvas.drawLine(endX, endY, edgeLeft[0], edgeLeft[1], paint);
        canvas.drawLine(endX, endY, edgeRight[0], edgeRight[1], paint);
    }
}

