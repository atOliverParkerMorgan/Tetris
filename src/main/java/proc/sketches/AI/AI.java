package proc.sketches.AI;

import proc.sketches.Blocks.Block;
import proc.sketches.Grid.Spot;
import proc.sketches.MAIN;
import proc.sketches.Shapes.*;

import java.util.List;

public class AI extends MAIN {

    private int[][] bitMap;


    public AI(Spot[][] Grid, Shape movingSpot) {

        this.bitMap = getGridInBitsWithoutMovingShape(movingSpot,Grid);


    }

    public AI(){
    }

    private int[][] getGridInBitsWithoutMovingShape(Shape movingSpot, Spot[][]Grid) {
        int[][] bitMap = new int[Shape.getNum_Y()][Shape.getNum_X()];
        for (int y = 0; y < Shape.getNum_Y(); y++) {
            for (int x = 0; x < Shape.getNum_X(); x++) {
                if (Grid[y][x].isOccupied()) {
                    bitMap[y][x] = 0;
                } else {
                    bitMap[y][x] = 1;
                }
            }
        }

        for (Block block : movingSpot.getAllblocks()) {
            if ((int) block.y / Shape.getSIZE() > 0 && (int) block.y / Shape.getSIZE() < Shape.getNum_Y()) {
                bitMap[(int) block.y / Shape.getSIZE()][(int) block.x / Shape.getSIZE()] = 1;
            }

        }


        return bitMap;


    }
    private int[][] getGridInBitsWithMovingShape(Spot[][]Grid) {
        int[][] bitMap = new int[Shape.getNum_Y()][Shape.getNum_X()];
        for (int y = 0; y < Shape.getNum_Y(); y++) {
            for (int x = 0; x < Shape.getNum_X(); x++) {
                if (Grid[y][x].isOccupied()) {
                    bitMap[y][x] = 0;
                } else {
                    bitMap[y][x] = 1;
                }
            }
        }

        return bitMap;


    }

    private boolean isHole(int x, int y, int[][] bitMap) {
        if (x != Shape.getNum_X() - 1) {
            if (bitMap[y][x + 1] == 0) {
                return true;
            }
        }
        if (x != 0) {
            if (bitMap[y][x - 1] == 0) {
                return true;
            }
        }
        if (y != 0) {
            return bitMap[y - 1][x] == 0;
        }
        return false;

    }


    public int getNumberOfHoles(Shape movingBlock,Spot[][] Grid) {

        bitMap = getGridInBitsWithoutMovingShape(movingBlock, Grid);
        int foundHoles = 0;

        for (int y = 0; y < Shape.getNum_Y(); y++) {
            for (int x = 0; x < Shape.getNum_X(); x++) {
                if (bitMap[y][x] == 1) {
                    if (isHole(x, y, bitMap)) {
                        foundHoles++;

                    }

                }
            }
        }


        return foundHoles;
    }

    public int getBumps(Shape moveShape, Spot[][] Grid) {
        int[][] bitmap = getGridInBitsWithoutMovingShape(moveShape, Grid);

        int[] values = new int[Shape.getNum_X()];

        int index = 0;
        for (int x = 0; x < Shape.getNum_X(); x++) {
            int highest = 0;
            for (int y = 0; y < Shape.getNum_Y(); y++) {
                if (bitmap[y][x] == 0) {
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
        int[][] bitmap = getGridInBitsWithoutMovingShape(moveShape, Grid);

        int[] values = new int[Shape.getNum_X()];

        int index = 0;
        for (int x = 0; x < Shape.getNum_X(); x++) {
            int highest = 0;
            for (int y = 0; y < Shape.getNum_Y(); y++) {
                if (bitmap[y][x] == 0) {
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
        int[][] bitmap = getGridInBitsWithoutMovingShape(moveShape, Grid);
        int numberOfLines = 0;

        for (int y = 0; y < Shape.getNum_Y(); y++) {
            int blocksInLine = 0;
            for (int x = 0; x < Shape.getNum_X(); x++) {
                if (bitmap[y][x] == 0) {
                    blocksInLine++;
                }
            }
            if (blocksInLine == Shape.getNum_X()) {
                numberOfLines++;
            }
        }

        return numberOfLines;
    }

    public double[][] bestMove(Shape moveShape) {
        double weight_a = -0.510066;
        double weight_b = 0.76066;
        double weight_c = -0.35663;
        double weight_d = -0.18443;

        double[][] currentXYofShape = new double[][]{{0,0},{0,0},{0,0},{0,0}};




        return currentXYofShape;
    }

    private void printGrid(){
        int[][]bitMap = getGridInBitsWithMovingShape(Spot.getGrid());

        for(int[] line: bitMap){
            StringBuilder printLine = new StringBuilder();
            for(int bit:line){
                printLine.append(bit).append(' ');
            }
            System.out.println(printLine.toString());
        }
        System.out.println();


    }






    public int[][] getBitMap() {
        return bitMap;
    }
}
