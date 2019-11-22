package proc.sketches.AI;

import proc.sketches.Grid.Spot;
import proc.sketches.MAIN;
import proc.sketches.Shapes.Shape;

public class AI extends MAIN {
    private Bitmap bitMap;
    public AI(Shape movingShape){

        this.bitMap = new Bitmap(getCords(movingShape),movingShape.getType());
    }

    private int[][] getCords(Shape movingShape){
        int[][] cordOfMovingShape = new int[4][2];

        for(int i = 0;i<Shape.numberOfBlocks;i++){
            cordOfMovingShape[i][0] = (int) movingShape.getAllblocks().get(i).x / Shape.getSIZE();
            cordOfMovingShape[i][1] = (int) movingShape.getAllblocks().get(i).y / Shape.getSIZE();
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


    private int getNumberOfHoles() {

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

    private int getBumps() {

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

    private int aggregateHeights() {

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

    private int getNumberOfLines() {
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

        double[][] currentXYofShape = new double[][]{{0,0},{0,0},{0,0},{0,0},{Integer.MIN_VALUE}};
        double fitness;


        bitMap = new Bitmap(getCords(moveShape),moveShape.getType());

        for(int outerIndex=0;outerIndex<moveShape.states;outerIndex++) {
            bitMap.resetBitmap();
            bitMap.rotate();
            bitMap.moveShapeLeft();
            bitMap.syncBitmapWithGrid(Grid, moveShape);





            int cycle = bitMap.getNumberOfCycles();
            for (int innerIndex = 0; innerIndex < cycle + 1; innerIndex++) {
                bitMap.moveMovingShapeDown();
                bitMap.syncBitmapWithGrid(Grid, moveShape);
               // printGrid();
                fitness = weight_a * aggregateHeights() + weight_b * getNumberOfLines() + weight_c * getNumberOfHoles() + weight_d * getBumps();
                if (currentXYofShape[4][0] < fitness) {
                    currentXYofShape[4][0] = fitness;
                    currentXYofShape = bitMap.getCords(currentXYofShape);
                }


                bitMap.moveMovingShapeUp();
                bitMap.syncBitmapWithGrid(Grid, moveShape);


                if (cycle != innerIndex) {
                    bitMap.moveMovingShapeRight();
                }

            }
        }





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
