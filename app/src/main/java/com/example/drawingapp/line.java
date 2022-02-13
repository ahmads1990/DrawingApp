package com.example.drawingapp;

public class line {
    public float startX, startY, stopX, stopY;
    float joinX, joinY = 0;
    public line(float startX, float startY, float stopX, float stopY) {
        this.startX = startX;
        this.startY = startY;
        this.stopX = stopX;
        this.stopY = stopY;
    }
    public line(float startX, float startY) { // for convenience
        this(startX, startY, startX, startY);
    }
}

