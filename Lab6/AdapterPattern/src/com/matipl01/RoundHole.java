package com.matipl01;

public class RoundHole {  // Client
    private final int radius;

    public RoundHole(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public boolean fits(IRoundPeg peg) {
        return peg.getRadius() <= getRadius();
    }
}
