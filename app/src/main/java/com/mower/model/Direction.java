package com.mower.model;

/**
 * Created by marvinmosa on 9/6/17.
 */

public class Direction {
    public static final int NORTH = 100;
    public static final int SOUTH = 101;
    public static final int EAST = 102;
    public static final int WEST = 103;
    private int mCurrent;

    public Direction(int current) {
        this.mCurrent = current;
    }

    public int getCurrentDirection() {
        return this.mCurrent;
    }


    public int getLeft() {
        switch (mCurrent) {
            case NORTH:
                return WEST;
            case EAST:
                return NORTH;
            case SOUTH:
                return EAST;
            default:
                return SOUTH;
        }
    }

    public int getRight() {
        switch (mCurrent) {
            case NORTH:
                return EAST;
            case EAST:
                return SOUTH;
            case SOUTH:
                return WEST;
            default:
                return NORTH;
        }
    }

    public String toString() {
        switch (mCurrent) {
            case NORTH:
                return "North";
            case EAST:
                return "East";
            case SOUTH:
                return "South";
            default:
                return "West";
        }
    }

    public String toString( int current) {
        switch (current) {
            case NORTH:
                return "N";
            case EAST:
                return "E";
            case SOUTH:
                return "S";
            default:
                return "W";
        }
    }

    public void setCurrentDirection(int current) {
        this.mCurrent = current;
    }
}
