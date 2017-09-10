package com.mower;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marvinmosa on 9/7/17.
 */

public class World {
    private Lawn[][] mLawns;
    private List<Mower> mMowers;
    private int mWidth, mHeight;
    private MainActivity mActivity;

    public World(MainActivity activity, int width, int height) {
        mWidth = width;
        mHeight = height;
        mActivity = activity;
        initializeLand(mWidth, mHeight);
        mMowers = new ArrayList<>();
    }

    public void initializeLand(int width, int height) {
        mLawns = new Lawn[height][width];
        for (int x = 0; x < mLawns.length; x++) {
            for (int y = 0; y < mLawns[x].length; y++) {
                mLawns[x][y] = new Lawn(y, x);
            }
        }
    }

    public void run(int num) {
        switch (num) {
            case 1:
                if (mHeight >= mWidth) {
                    Mower mower = new Mower("Mower 1", 0, 0);
                    mower.setDirection(Direction.NORTH);
                    mower.setInstruction(new Instruction());
                    mower.setWorld(this);
                    mMowers.add(mower);
                    getLawn(mower.getX(), mower.getY()).setDone(true);
                } else {
                    Mower mower = new Mower("Mower 1", 0, mHeight - 1);
                    mower.setDirection(Direction.EAST);
                    mower.setInstruction(new Instruction());
                    mower.setWorld(this);
                    mMowers.add(mower);
                    getLawn(mower.getX(), mower.getY()).setDone(true);
                }
                break;
//            case 2:
//                if (mHeight >= mWidth) {
//                    Mower mower = new Mower("Mower 1", 0, 0);
//                    mower.setDirection(Direction.NORTH);
//                    mower.setInstruction(new Instruction());
//                    mower.setWorld(this);
//                    mMowers.add(mower);
//                    getLawn(mower.getX(), mower.getY()).setDone(true);
//                    mActivity.addLog(mower.getName() + " initial position(" + mower.getX() + "," + mower.getY()
//                            + ") and heading " + mower.getDirection().toString());
//                    Log.d("Marvin", mower.getName() + " initial position(" + mower.getX() + "," + mower.getY()
//                            + ") and heading " + mower.getDirection().toString());
//
//                    Mower mower2 = new Mower("Mower 2", mWidth - 1, mHeight - 1);
//                    mower2.setDirection(Direction.SOUTH);
//                    mower2.setInstruction(new Instruction());
//                    mower2.setWorld(this);
//                    mMowers.add(mower2);
//                    getLawn(mower2.getX(), mower2.getY()).setDone(true);
//                    mActivity.addLog(mower2.getName() + " initial position(" + mower2.getX() + "," + mower2.getY()
//                            + ") and heading " + mower.getDirection().toString());
//                    Log.d("Marvin", mower2.getName() + " initial position(" + mower2.getX() + "," + mower2.getY()
//                            + ") and heading " + mower2.getDirection().toString());
//                } else {
//                    Mower mower = new Mower("Mower 1", 0, mHeight - 1);
//                    mower.setDirection(Direction.EAST);
//                    mower.setInstruction(new Instruction());
//                    mower.setWorld(this);
//                    mMowers.add(mower);
//                    getLawn(mower.getX(), mower.getY()).setDone(true);
//                    mActivity.addLog(mower.getName() + " initial position(" + mower.getX() + "," + mower.getY()
//                            + ") and heading " + mower.getDirection().toString());
//                    Log.d("Marvin", mower.getName() + " initial position(" + mower.getX() + "," + mower.getY()
//                            + ") and heading " + mower.getDirection().toString());
//                    Mower mower2 = new Mower("Mower 2", mWidth - 1, 0);
//                    mower2.setDirection(Direction.WEST);
//                    mower2.setInstruction(new Instruction());
//                    mower2.setWorld(this);
//                    mMowers.add(mower2);
//                    getLawn(mower2.getX(), mower2.getY()).setDone(true);
//                    mActivity.addLog(mower2.getName() + " initial position(" + mower2.getX() + "," + mower2.getY()
//                            + ") and heading " + mower.getDirection().toString());
//                    Log.d("Marvin", mower2.getName() + " initial position(" + mower2.getX() + "," + mower2.getY()
//                            + ") and heading " + mower.getDirection().toString());
//                }
//                break;
            default:
                if (mHeight >= mWidth) {
                    int[] locList = Utilities.splitIntoParts(mWidth, num);
                    int partsRemaing = mWidth;
                    int partsRemaining2 = 0;
                    for (int i = 0; i < num; i++) {

                        Mower mower;
                        if(Utilities.isOdd(i)) {
                            mower = new Mower("Mower " + (i+1), partsRemaing -1, 0);
                            mower.setDirection(Direction.NORTH);
                        } else {
                            mower = new Mower("Mower " + (i+1), partsRemaing -1, mHeight - 1);
                            mower.setDirection(Direction.SOUTH);
                        }

                        partsRemaing = partsRemaing - locList[i];
                        partsRemaining2 = partsRemaining2 + locList[i];

                        mower.setInstruction(new Instruction());
                        mower.setWorld(this);
                        mMowers.add(mower);
                        getLawn(mower.getX(), mower.getY()).setDone(true);
                        mActivity.addLog(mower.getName() + " initial position(" + mower.getX() + "," + mower.getY()
                                + ") and heading " + mower.getDirection().toString());
                        Log.d("Marvin", mower.getName() + " initial position(" + mower.getX() + "," + mower.getY()
                                + ") and heading " + mower.getDirection().toString());
                    }

                } else {

                }

                break;
        }

        while (getAvailableLawn() != 0) {
            for (Mower mower : mMowers) {
                if (mower.validateInstruction("M", mWidth, mHeight, mMowers)) {
                    mower.getInstruction().addInstruction("M");
                    mower.executeMovement("M");
                    getLawn(mower.getX(), mower.getY()).setDone(true);
                    Log.d("Marvin", "size: " + mower.getInstruction().getSize() + "(" + mower.getX() + "," + mower.getY() + ")");
                } else {
                    if(!mower.isFrontMower(mMowers)) {
                        continue;
                    }
                    if(!mower.isFrontMower(mMowers) && !mower.isLeftPassable()) {
                        mower.getInstruction().addInstruction("R");
                        mower.executeMovement("R");
                        getLawn(mower.getX(), mower.getY()).setDone(true);
                        Log.d("Marvin", "size: " + mower.getInstruction().getSize());
                    } else {
                        if(mower.isRightPassable()) {
                            mower.getInstruction().addInstruction("R");
                            mower.executeMovement("R");
                            getLawn(mower.getX(), mower.getY()).setDone(true);
                            Log.d("Marvin", "size: " + mower.getInstruction().getSize());
                        }
                    }
                }
            }
            displayLand();
        }

        String output = "";
        for (Mower mower : mMowers) {
            output = output + mower.getInitialXLocation() + " " + mower.getInitialYLocation() + " "+ mower.getDirection().toString(mower.getDirection().getCurrentDirection())+"\n";
            output = output + Utilities.arrayToString(mower.getInstruction().getInstructionList()) +"\n\n";
            Log.d("Marvin", "size: " + mower.getInstruction().getSize());
        }
        mActivity.showOutput(output);
    }

    public void run() {
        int longestCommandLength = 0;
        for (Mower mower : mMowers) {
            if (longestCommandLength < mower.getInstruction().getSize()) {
                longestCommandLength = mower.getInstruction().getSize();
            }
        }

        int ctr = 0;
        while (ctr < longestCommandLength) {
            for (Mower mower : mMowers) {
                if (mower.getInstruction().getInstructionList().size() > ctr) {
                    if (mower.validateInstruction(mower.getInstruction().getInstructionList().get(ctr), mWidth, mHeight, mMowers)) {
                        mower.executeMovement(mower.getInstruction().getInstructionList().get(ctr));
                        mActivity.addLog(mower.getName() + " position(" + mower.getX() + "," + mower.getY()
                                + ") and heading " + mower.getDirection().toString());
                        Log.d("Marvin", mower.getName() + " position(" + mower.getX() + "," + mower.getY()
                                + ") and heading " + mower.getDirection().toString());
                    } else {
                        mActivity.addLog(mower.getName() + " invalid move");
                        Log.d("Marvin", mower.getName() + " invalid move");
                    }
                }
            }
            displayLand();
            ctr++;
        }

        String output = "";
        for (Mower mower : mMowers) {
            output = output + mower.getX() + " " + mower.getY() + " " + mower.getDirection().toString(mower.getDirection().getCurrentDirection()) + "\n";
        }
        mActivity.showOutput(output);
    }

    public void addMower(String name, int x, int y, int direction, String instruction) {
        Mower mower = new Mower(name, x, y, direction);
        mower.setInstruction(new Instruction(instruction));
        mActivity.addLog(name + " initial position(" + mower.getX() + "," + mower.getY()
                + ") and heading " + mower.getDirection().toString());
        Log.d("Marvin", name + " initial position(" + mower.getX() + "," + mower.getY()
                + ") and heading " + mower.getDirection().toString());
        mMowers.add(mower);
    }

    public Lawn getLawn(int xLoc, int yLoc) {
        for (int x = 0; x < mLawns.length; x++) {
            for (int y = 0; y < mLawns[x].length; y++) {
                if (mLawns[x][y].getXLocation() == xLoc && mLawns[x][y].getYLocation() == yLoc) {
                    return mLawns[x][y];
                }
            }
        }
        return null;
    }

    public void displayLand() {
        String data = "";
        Lawn[][] temp = mLawns.clone();
        for (int x = 0; x < mLawns.length; x++) {
            temp[mLawns.length - 1 - x] = mLawns[x];
        }

        for (int x = 0; x < temp.length; x++) {
            for (int y = 0; y < temp[x].length; y++) {
                boolean isMatch = false;
                for (Mower mower : mMowers) {
                    if (temp[x][y].getXLocation() == mower.getX() && temp[x][y].getYLocation() == mower.getY()) {
                        data = mower.display(data);
                        isMatch = true;
                        break;
                    }
                }

                if (!isMatch) {
                    data = temp[x][y].display(data);
                }
            }
            data = data + "\n";
        }
        Log.d("Marvin", data);
        mActivity.addLog(data);
    }

    public void displayMatrixCoordinate() {
        String data = "";

        Lawn[][] temp = mLawns.clone();
        for (int x = 0; x < mLawns.length; x++) {
            temp[mLawns.length - 1 - x] = mLawns[x];
        }

        for (int x = 0; x < temp.length; x++) {
            for (int y = 0; y < temp[x].length; y++) {
                data = temp[x][y].getName(data);
            }
            data = data + "\n";
        }
        Log.d("Marvin", data);
    }

    public int getAvailableLawn() {
        int count = 0;
        for (int x = 0; x < mLawns.length; x++) {
            for (int y = 0; y < mLawns[x].length; y++) {
                if (!mLawns[x][y].isDone()) count++;
            }
        }

        return count;
    }

    public int getWidth() {
        return mWidth;
    }

    public void setWidth(int w) {
        this.mWidth = w;
    }

    public int getHeight() {
        return mHeight;
    }

    public void setHeight(int h) {
        this.mHeight = h;
    }
}
