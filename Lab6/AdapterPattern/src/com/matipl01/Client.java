package com.matipl01;

public class Client implements IClient {
    @Override
    public IRoundPeg squareToRoundPeg(ISquarePeg squarePeg) {
        return new SquarePegAdapter(squarePeg);
    }

    @Override
    public void run() {  // I don't know how to name it (this task is so silly)
        RoundHole hole = new RoundHole(5);
        IRoundPeg rpeg = new RoundPeg(5);

        System.out.println(hole.fits(rpeg));  // true

        ISquarePeg small_sqpeg = new SquarePeg(5);
        ISquarePeg large_sqpeg = new SquarePeg(10);

//        System.out.println(hole.fits(small_sqpeg));  // This won't work as a square peq cannot fit a round hole

        SquarePegAdapter small_sqpeg_adapter = new SquarePegAdapter(small_sqpeg);
        SquarePegAdapter large_sqpeg_adapter = new SquarePegAdapter(large_sqpeg);

        System.out.println(hole.fits(small_sqpeg_adapter));  // true
        System.out.println(hole.fits(large_sqpeg_adapter));  // true
    }
}
