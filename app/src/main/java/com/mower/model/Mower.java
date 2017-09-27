package com.mower.model;

import java.util.List;

/**
 * Created by marvinmosa on 9/6/17.
 */

public class Mower {
    public static final String INSTRUCTION_FORWARD = "M";
    public static final String INSTRUCTION_RIGHT = "R";
    public static final String INSTRUCTION_LEFT = "L";
    private Direction mDirection;
    private int mXLocation, mYLocation;
    private int mInitialXLocation, mInitialYLocation;
    private Instruction mInstruction;
    private String mName;
    private World mWorld;

    public Mower(String name, int x, int y) {
        setLocation(x, y);
        mInitialXLocation = x;
        mYLocation = y;
        mName = name;
    }

    public Mower(String name, int x, int y, int direction) {
        setLocation(x, y);
        mDirection = new Direction(direction);
        mName = name;
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

    public boolean validateInstruction(String instruction, List<Mower> mowers) {
        if (instruction.compareTo(INSTRUCTION_LEFT) == 0 || instruction.compareTo(INSTRUCTION_RIGHT) == 0)
            return true;

        switch (mDirection.getCurrentDirection()) {
            case Direction.NORTH:
                for (Mower mower : mowers) {
                    if (mower.getName().compareToIgnoreCase(mName) == 0) continue;
                    if (mower.getY() == (mYLocation + 1) && mower.getX() == mXLocation)
                        return false;
                }

                if (mYLocation < (mWorld.getHeight() - 1)) {
                    if (mWorld.getLawn(mXLocation, mYLocation + 1).isDone()) return false;
                    return true;
                }
                break;
            case Direction.SOUTH:
                for (Mower mower : mowers) {
                    if (mower.getName().compareToIgnoreCase(mName) == 0) continue;
                    if (mower.getY() == (mYLocation - 1) && mower.getX() == mXLocation)
                        return false;
                }

                if (mYLocation != 0) {
                    if (mWorld.getLawn(mXLocation, mYLocation - 1).isDone()) return false;
                    return true;
                }
                break;
            case Direction.EAST:
                for (Mower mower : mowers) {
                    if (mower.getName().compareToIgnoreCase(mName) == 0) continue;
                    if (mower.getX() == (mXLocation + 1) && mower.getY() == mYLocation)
                        return false;
                }

                if (mXLocation < (mWorld.getWidth() - 1)) {
                    if (mWorld.getLawn(mXLocation + 1, mYLocation).isDone()) return false;
                    return true;
                }
                break;
            case Direction.WEST:
                for (Mower mower : mowers) {
                    if (mower.getName().compareToIgnoreCase(mName) == 0) continue;
                    if (mower.getX() == (mXLocation - 1) && mower.getY() == mYLocation)
                        return false;
                }

                if (mXLocation != 0) {
                    if (mWorld.getLawn(mXLocation - 1, mYLocation).isDone()) return false;
                    return true;
                }
                break;
        }
        return false;
    }

    public boolean isFrontMower(List<Mower> list) {
        switch (mDirection.getCurrentDirection()) {
            case Direction.NORTH:
                for (Mower mower : list) {
                    if (mower.getName().compareToIgnoreCase(mName) == 0) continue;
                    if (mower.getY() == (mYLocation + 1) && mower.getX() == mXLocation)
                        return false;
                }
                break;
            case Direction.SOUTH:
                for (Mower mower : list) {
                    if (mower.getName().compareToIgnoreCase(mName) == 0) continue;
                    if (mower.getY() == (mYLocation - 1) && mower.getX() == mXLocation)
                        return false;
                }
                break;

            case Direction.EAST:
                for (Mower mower : list) {
                    if (mower.getName().compareToIgnoreCase(mName) == 0) continue;
                    if (mower.getX() == (mXLocation + 1) && mower.getY() == mYLocation)
                        return false;
                }
                break;

            case Direction.WEST:
                for (Mower mower : list) {
                    if (mower.getName().compareToIgnoreCase(mName) == 0) continue;
                    if (mower.getX() == (mXLocation - 1) && mower.getY() == mYLocation)
                        return false;
                }
        }
        return true;
    }

    public boolean isLeftPassable() {
        switch (mDirection.getCurrentDirection()) {
            case Direction.NORTH:
                if (mXLocation == 0) return false;
                if (getWorld().getLawn(mXLocation - 1, mYLocation).isDone()) return false;
                break;
            case Direction.SOUTH:
                if (mXLocation == (mWorld.getWidth() - 1)) return false;
                if (getWorld().getLawn(mXLocation + 1, mYLocation).isDone()) return false;
                break;

            case Direction.EAST:
                if (mYLocation == (mWorld.getHeight() - 1)) return false;
                if (getWorld().getLawn(mXLocation, mYLocation + 1).isDone()) return false;
                break;

            case Direction.WEST:
                if (mYLocation == 0) return false;
                if (getWorld().getLawn(mXLocation, mYLocation - 1).isDone()) return false;
                break;
        }
        return true;
    }

    public boolean isRightPassable() {
        switch (mDirection.getCurrentDirection()) {
            case Direction.NORTH:
                if (mXLocation == mWorld.getWidth() - 1) return false;
                if (getWorld().getLawn(mXLocation + 1, mYLocation).isDone()) return false;
                break;
            case Direction.SOUTH:
                if (mXLocation == 0) return false;
                if (getWorld().getLawn(mXLocation - 1, mYLocation).isDone()) return false;
                break;

            case Direction.EAST:
                if (mYLocation == 0) return false;
                if (getWorld().getLawn(mXLocation, mYLocation - 1).isDone()) return false;
                break;

            case Direction.WEST:
                if (mYLocation == (mWorld.getHeight() - 1)) return false;
                if (getWorld().getLawn(mXLocation, mYLocation + 1).isDone()) return false;
                break;
        }
        return true;
    }


    public void moveForward() {
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
                break;
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

    public void setDirection(int direction) {
        this.mDirection = new Direction(direction);
    }

    public Direction getDirection() {
        return this.mDirection;
    }

    public String display(String markers) {
        String identifier = "";
        switch (mDirection.getCurrentDirection()) {
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
        return markers + identifier + "  ";
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public World getWorld() {
        return mWorld;
    }

    public void setWorld(World world) {
        this.mWorld = world;
    }

    public int getInitialXLocation() {
        return mInitialXLocation;
    }

    public void setInitialXLocation(int x) {
        this.mInitialXLocation = x;
    }

    public int getInitialYLocation() {
        return mInitialYLocation;
    }

    public void setInitialYLocation(int y) {
        this.mInitialYLocation = y;
    }
}
