package com.example.drawingapp;

public class Arrow {
    public float startX, startY, stopX, stopY;
    float joinX, joinY = 0;
    public Arrow(float startX, float startY, float stopX, float stopY) {
        this.startX = startX;
        this.startY = startY;
        this.stopX = stopX;
        this.stopY = stopY;
    }
    public Arrow(float startX, float startY) { // for convenience
        this(startX, startY, startX, startY);
    }
}

