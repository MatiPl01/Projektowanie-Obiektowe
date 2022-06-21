package com.matipl01;

public class RoundPeg implements IRoundPeg {
    private final int radius;

    public RoundPeg(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }
}
