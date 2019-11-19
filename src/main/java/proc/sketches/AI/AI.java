package proc.sketches.AI;

import proc.sketches.Blocks.Block;
import proc.sketches.Grid.Spot;
import proc.sketches.MAIN;
import proc.sketches.Shapes.*;

import java.util.List;

public class AI extends MAIN {
    private Bitmap bitMap;
    public AI(Shape movingShape, Spot[][] Grid){

        this.bitMap = new Bitmap(getCords(movingShape), Grid);
    }

    private int[][] getCords(Shape movingShape){
        int[][] cordOfMovingShape = new int[4][2];

        for(int i = 0;i<4;i++){
            cordOfMovingShape[0][0] = (int) movingShape.getAllblocks().get(i).x / Shape.getNum_X();
            cordOfMovingShape[0][1] = (int) movingShape.getAllblocks().get(i).y / Shape.getNum_Y();
        }
        return cordOfMovingShape;
    }


    private boolean isHole(int x, int y) {
        if (x != Shape.getNum_X() - 1) {
            if (bitMap.getBit(x+1,y) == 0) {
                return true;
            }
        }
        if (x != 0) {
            if (bitMap.getBit(x-1,y) == 0) {
                return true;
            }
        }
        if (y != 0) {
            return bitMap.getBit(x,y-1) == 0;
        }
        return false;

    }


    public int getNumberOfHoles() {

        int foundHoles = 0;

        for (int y = 0; y < Shape.getNum_Y(); y++) {
            for (int x = 0; x < Shape.getNum_X(); x++) {
                if (bitMap.getBit(x,y) == 1) {
                    if (isHole(x, y)) {
                        foundHoles++;

                    }

                }
            }
        }


        return foundHoles;
    }

    public int getBumps(Shape moveShape, Spot[][] Grid) {

        int[] values = new int[Shape.getNum_X()];

        int index = 0;
        for (int x = 0; x < Shape.getNum_X(); x++) {
            int highest = 0;
            for (int y = 0; y < Shape.getNum_Y(); y++) {
                if (bitMap.getBit(x,y) == 0) {
                    if (highest < 20 - y) {
                        highest = 20 - y;
                    }
                }
            }
            values[index] = highest;
            index++;
        }
        int all = 0;
        for (int v = 0; v < Shape.getNum_X(); v++) {
            if (v + 1 < values.length) {
                int difference = values[v] - values[v + 1];
                if (difference < 0) {
                    all += -difference;
                } else {
                    all += difference;
                }
            }
        }


        return all;
    }

    public int aggregateHeights(Shape moveShape, Spot[][] Grid) {

        int[] values = new int[Shape.getNum_X()];

        int index = 0;
        for (int x = 0; x < Shape.getNum_X(); x++) {
            int highest = 0;
            for (int y = 0; y < Shape.getNum_Y(); y++) {
                if (bitMap.getBit(x,y) == 0) {
                    if (highest < 20 - y) {
                        highest = 20 - y;
                    }
                }
            }
            values[index] = highest;
            index++;
        }
        int all = 0;
        for (int v : values) {
            all += v;
        }

        return all;


    }

    public int getNumberOfLines(Shape moveShape, Spot[][] Grid) {
        int numberOfLines = 0;

        for (int y = 0; y < Shape.getNum_Y(); y++) {
            int blocksInLine = 0;
            for (int x = 0; x < Shape.getNum_X(); x++) {
                if (bitMap.getBit(x,y) == 0) {
                    blocksInLine++;
                }
            }
            if (blocksInLine == Shape.getNum_X()) {
                numberOfLines++;
            }
        }

        return numberOfLines;
    }

    public double[][] bestMove(Shape moveShape,Spot[][] Grid) {
        double weight_a = -0.510066;
        double weight_b = 0.76066;
        double weight_c = -0.35663;
        double weight_d = -0.18443;

        double[][] currentXYofShape = new double[][]{{0,0},{0,0},{0,0},{0,0}};
        bitMap.syncBitmapWithGrid(Grid,getCords(moveShape));

        bitMap



        return currentXYofShape;
    }

    private void printGrid(){

        for(int[] line: bitMap.Bitmap){
            StringBuilder printLine = new StringBuilder();
            for(int bit:line){
                printLine.append(bit).append(' ');
            }
            System.out.println(printLine.toString());
        }
        System.out.println();


    }







}
