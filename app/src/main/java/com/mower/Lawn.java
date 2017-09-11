package com.mower;

/**
 * Created by marvinmosa on 9/6/17.
 */

public class Lawn {
    private boolean mDone;
    private boolean isMowerHere;
    public String name;
    private int mXLocation,mYLocation;

    public Lawn(int x, int y) {
        this.mXLocation = x;
        this.mYLocation = y;
        name = "(" + x + "," + y+")";
    }

    public boolean isDone() {
        return mDone;
    }

    public void setDone(boolean done) {
        this.mDone = done;
    }

    public boolean isMowerHere() {
        return isMowerHere;
    }

    public void setMowerHere(boolean mowerHere) {
        isMowerHere = mowerHere;
    }

    public String getName(String string){
        return string + name;
    }

    public String display(String string) {
        if (isDone()) return string + "X  ";
        return string + "O  ";
    }

    public int getXLocation() {
        return mXLocation;
    }

    public void setXLocation(int x) {
        this.mXLocation = x;
    }

    public int getYLocation() {
        return mYLocation;
    }

    public void setYLocation(int y) {
        this.mYLocation = y;
    }
}
