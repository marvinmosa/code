package com.mower;

import android.util.Log;

/**
 * Created by marvinmosa on 9/6/17.
 */

public class Mower {
    public static final String INSTRUCTION_FORWARD = "M";
    public static final String INSTRUCTION_RIGHT = "R";
    public static final String INSTRUCTION_LEFT = "L";
    private Direction mDirection;
    private int mXLocation, mYLocation;
    private Instruction mInstruction;

    public Mower(int x, int y, int direction) {
        setLocation(x, y);
        mDirection = new Direction(direction);
    }

    public Instruction getInstruction() {
        return mInstruction;
    }

    public void setInstruction(Instruction instruction) {
        this.mInstruction = instruction;
    }

    public void executeMovement(String instruction) {
        switch (instruction) {
            case INSTRUCTION_RIGHT:
                rotateRight();
                break;

            case INSTRUCTION_LEFT:
                rotateLeft();
                break;

            case INSTRUCTION_FORWARD:
                moveForward();
                break;
        }
    }

    public void moveForward() {
        Log.d("Marvin", "Direction: " + mDirection.toString());
        switch (mDirection.getCurrentDirection()) {
            case Direction.NORTH:
                mYLocation++;
                break;
            case Direction.SOUTH:
                mYLocation--;
                break;
            case Direction.EAST:
                mXLocation++;
                break;
            case Direction.WEST:
                mXLocation--;
        }
    }

    public void rotateLeft() {
        mDirection.setCurrentDirection(mDirection.getLeft());
    }

    public void rotateRight() {
        mDirection.setCurrentDirection(mDirection.getRight());
    }

    public int getX() {
        return mXLocation;
    }

    public int getY() {
        return mYLocation;
    }

    public void setLocation(int x, int y) {
        this.mXLocation = x;
        this.mYLocation = y;
    }

    public Direction getDirection() {
        return this.mDirection;
    }

    public String display(String icon){
        String identifier ="";
        switch(mDirection.getCurrentDirection()) {
            case Direction.NORTH:
                identifier = "↑";
                break;
            case Direction.SOUTH:
                identifier = "↓";
                break;
            case Direction.EAST:
                identifier = "→";
                break;
            case Direction.WEST:
                identifier = "←";
                break;
        }
        return icon + identifier + " ";
    }
}
