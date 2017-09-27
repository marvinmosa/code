package com.mower.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by marvinmosa on 9/7/17.
 */

public class Instruction {
    private List<String> mInstructionList;

    public Instruction(){
        mInstructionList = new ArrayList<>();
    }
    public Instruction(String instruction) {
        mInstructionList = decode(instruction);
    }

    public List<String> decode(String instruction) {
        String[] list = instruction.split("");
        mInstructionList = Arrays.asList(list);
        return mInstructionList;
    }

    public int getSize() {
        return mInstructionList.size();
    }

    public List<String> getInstructionList(){
        return mInstructionList;
    }

    public void addInstruction(String instruction) {
        this.mInstructionList.add(instruction);
    }
}
