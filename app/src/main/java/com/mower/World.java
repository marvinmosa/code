package com.mower;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by marvinmosa on 9/7/17.
 */

public class World {
    Lawn[][] mLawns;
    List<Mower> mMowers;

    public World() {
        int x = 3;
        int y = 4;


        initializeLand(x, y);
        mMowers = new ArrayList<>();
    }

    public void initializeLand(int width, int height) {
        mLawns = new Lawn[height][width];
        for (int x = 0; x < mLawns.length; x++) {
            for (int y = 0; y < mLawns[x].length; y++) {
                mLawns[x][y] = new Lawn(x, y);
            }
        }
    }

    public void play() {
        int longestCommandLength = 0;
        for (Mower mower : mMowers) {
            if (longestCommandLength < mower.getInstruction().getSize()) {
                longestCommandLength = mower.getInstruction().getSize();
            }
        }
        Log.d("Marvin", "longest: " + longestCommandLength);

        int ctr = 1;
        while (ctr < longestCommandLength) {
            //execute commands
            for (Mower mower : mMowers) {
                //delete old location
//                for (int x = 0; x < mLawns.length; x++) {
//                    for (int y = 0; y < mLawns[x].length; y++) {
//                        if(mLawns[x][y].getXLocation() == mower.getX() && mLawns[x][y].getYLocation() == mower.getY()){
//                            mLawns[x][y].setMowerHere(false);
//                        }
//                    }
//                }
                mLawns[mower.getY()][mower.getX()].setMowerHere(false);
                mower.executeMovement(mower.getInstruction().getInstructionList()[ctr]);
                //update mower location
//                for (int x = 0; x < mLawns.length; x++) {
//                    for (int y = 0; y < mLawns[x].length; y++) {
//                        if(mLawns[x][y].getXLocation() == mower.getX() && mLawns[x][y].getYLocation() == mower.getY()){
//                            mLawns[x][y].setMowerHere(true);
//                        }
//                    }
//                }
                mLawns[mower.getY()][mower.getX()].setMowerHere(true);

                Log.d("Marvin", "Mower initial position(" + mower.getX() + "," + mower.getY()
                        + ") and heading " + mower.getDirection().toString());
            }
            //display all
            displayLand();
            displayLand2();
            ctr++;
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

    public void addMower(int x, int y, int direction, String instruction) {
        Mower mower = new Mower(x, y, direction);
        mower.setInstruction(new Instruction(instruction));
        Log.d("Marvin", "Mower initial position(" + mower.getX() + "," + mower.getY()
                + ") and heading " + mower.getDirection().toString());
        mLawns[x][y].setMowerHere(true);
        mMowers.add(mower);
    }


    public Lawn[] reverse(Lawn[] arr) {
        List<Lawn> list = Arrays.asList(arr);
        Collections.reverse(list);
        return (Lawn[]) list.toArray();
    }

    public void displayLand() {
        String data = "";
        Lawn[][] temp = mLawns.clone();
        for (int x = 0; x < mLawns.length; x++) {
            temp[mLawns.length - 1 - x] = mLawns[x];
        }

        for (int x = 0; x < temp.length; x++) {
            for (int y = 0; y < temp[x].length; y++) {
                data = temp[x][y].display(data);
            }
            data = data + "\n";
        }
        Log.d("Marvin", data);
    }

    public void displayLand2() {
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

}
