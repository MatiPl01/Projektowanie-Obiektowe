package com.matipl01;

public class SquarePeg implements ISquarePeg {  // Adaptee
    private final int width;

    public SquarePeg(int width) {
        this.width = width;
    }

    public int getWidth() {  // specificRequest
        return width;
    }
}
