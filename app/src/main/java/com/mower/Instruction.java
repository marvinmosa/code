package com.mower;

import java.util.List;

/**
 * Created by marvinmosa on 9/7/17.
 */

public class Instruction {
    private String[] mInstructionList;

    public Instruction(String instruction) {
        mInstructionList = decode(instruction);
    }

    public String[] decode(String instruction) {
        return instruction.split("");
    }

    public int getSize() {
        return mInstructionList.length;
    }

    public String[] getInstructionList(){
        return mInstructionList;
    }
}
