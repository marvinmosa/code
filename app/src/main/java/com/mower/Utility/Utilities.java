package com.mower.Utility;

import com.mower.model.Direction;

import java.util.List;

/**
 * Created by marvinmosa on 9/10/17.
 */

public class Utilities {

    public static boolean isOdd(int x) {
        if ((x % 2) == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static int[] splitIntoParts(int whole, int parts) {
        int[] arr = new int[parts];
        int remain = whole;
        int partsLeft = parts;
        for (int i = 0; partsLeft > 0; i++) {
            int size = (remain + partsLeft - 1) / partsLeft;
            arr[i] = size;
            remain -= size;
            partsLeft--;
        }
        return arr;
    }

    public static String arrayToString(List<String> list) {
        String output = "";
        for( String data : list) {
            output = output + data;
        }
        return output;
    }

    public static int directionConverter(String direction){
        switch (direction) {
            case "N":
                return Direction.NORTH;
            case "S":
                return Direction.SOUTH;
            case "W":
                return Direction.WEST;
            default:
                return Direction.EAST;
        }
    }

}
