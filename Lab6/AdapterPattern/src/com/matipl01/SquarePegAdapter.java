package com.matipl01;

public class SquarePegAdapter implements IRoundPeg {
    private final ISquarePeg peg;

    public SquarePegAdapter(ISquarePeg peg) {
        this.peg = peg;
    }

    public int getRadius() {  // request
        return (int) (peg.getWidth() * Math.sqrt(2) / 2);  // specificRequest
    }
}
