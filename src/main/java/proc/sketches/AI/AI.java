package proc.sketches.AI;

import proc.sketches.Blocks.Block;
import proc.sketches.Grid.Spot;
import proc.sketches.MAIN;
import proc.sketches.Shapes.*;

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

    public double[][] bestMove(Shape moveShape) throws CloneNotSupportedException {
        double weight_a = -0.510066;
        double weight_b = 0.76066;
        double weight_c = -0.35663;
        double weight_d = -0.18443;

        Spot[][] GridClone = Spot.getGrid().clone();
        Spot[][] Grid = null;
        // move left
        // move left
        boolean moveLeft = true;
        while (moveLeft) {
            moveShape.move_left();
            for (Block block : moveShape.getAllblocks()) {
                if (block.x == 0) {
                    moveLeft = false;
                    break;
                }
            }
        }


        double highestFitness = 0;
        double[][] currentXYofShape = new double[][]{{0,0},{0,0},{0,0},{0,0}};


        for (int x = 0; x < Shape.getNum_X(); x++) {
            for (int r = 0; r < moveShape.states; r++) {
                Shape moveShapeClone = (Shape) moveShape.clone();

                // rotating
                if (moveShapeClone.getType() == 0) {
                    Blue_line line = (Blue_line) moveShapeClone;
                    line.rotate_All();
                } else if (moveShapeClone.getType() == 1) {
                    DarkBlue_L L = (DarkBlue_L) moveShapeClone;
                    L.rotate_All();
                } else if (moveShapeClone.getType() == 2) {
                    Green_S S = (Green_S) moveShapeClone;
                    S.rotate_All();
                } else if (moveShapeClone.getType() == 3) {
                    Orange_L L = (Orange_L) moveShapeClone;
                    L.rotate_All();
                } else if (moveShapeClone.getType() == 4) {
                    Purple_T T = (Purple_T) moveShapeClone;
                    T.rotate_All();
                } else if (moveShapeClone.getType() == 5) {
                    Red_Z Z = (Red_Z) moveShapeClone;
                    Z.rotate_All();
                }

                while (!checkCollision(moveShapeClone)){
                    moveShapeClone.move_down();
               }

                printGrid();

                double fitness = weight_a * aggregateHeights(moveShape, Grid) + weight_b * getNumberOfLines(moveShape,Grid) +
                        weight_c * getNumberOfHoles(moveShape,Grid) + weight_d * getBumps(moveShape,Grid);


                if(fitness>highestFitness){
                    highestFitness = fitness;

                    int index = 0;
                    for(Block block:moveShapeClone.getAllblocks()){
                        currentXYofShape[index] = new double[]{block.x,block.y};
                        index++;
                    }

                }


            }
            moveShape.move_right();
        }

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
