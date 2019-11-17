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


    private int getNumberOfHoles(Spot[][] Grid) {

        bitMap = getGridInBitsWithMovingShape(Grid);
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

    private int getBumps(Spot[][] Grid) {
        int[][] bitmap = getGridInBitsWithMovingShape(Grid);

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

        System.out.println(all);
        return all;
    }

    private int aggregateHeights(Spot[][] Grid) {
        int[][] bitmap = getGridInBitsWithMovingShape(Grid);

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
        System.out.println(all);
        return all;


    }

   private int getNumberOfLines(Spot[][] Grid) {
        int[][] bitmap = getGridInBitsWithMovingShape(Grid);
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

        List<Shape> AllShapesClone = Shape.getAll_Shapes();
        Spot[][] GridClone = Spot.getGrid();
        int start_X = 0;


        double[][] currentXYofShape = new double[][]{{0,0},{0,0},{0,0},{0,0},{0}};


        for (int x = 0; x < Shape.getNum_X()-1; x++) {
            // rotating

            if (moveShape.getType() == 0) {
                moveShape = new Blue_line(start_X+Shape.getSIZE());
                Blue_line R = (Blue_line) moveShape;
                for (int r = 0; r < moveShape.states; r++) {
                    R.rotate_All();
                    currentXYofShape = moveDownAndEvaluate(R,AllShapesClone,GridClone, currentXYofShape);

                }


            } else if (moveShape.getType() == 1) {
                moveShape = new DarkBlue_L(start_X);
                DarkBlue_L R = (DarkBlue_L) moveShape;
                for (int r = 0; r < moveShape.states; r++) {
                    R.rotate_All();
                    currentXYofShape = moveDownAndEvaluate(R,AllShapesClone,GridClone, currentXYofShape);

                }

            } else if (moveShape.getType() == 2) {
                moveShape = new Green_S(start_X);
                Green_S R = (Green_S) moveShape;
                for (int r = 0; r < moveShape.states; r++) {
                    R.rotate_All();
                    currentXYofShape = moveDownAndEvaluate(R,AllShapesClone,GridClone, currentXYofShape);

                }

            } else if (moveShape.getType() == 3) {
                moveShape = new Orange_L(start_X);
                Orange_L R = (Orange_L) moveShape;
                for (int r = 0; r < moveShape.states; r++) {
                    R.rotate_All();
                    currentXYofShape = moveDownAndEvaluate(R,AllShapesClone,GridClone, currentXYofShape);

                }

            } else if (moveShape.getType() == 4) {
                moveShape = new Purple_T(start_X+Shape.getSIZE());
                Purple_T R = (Purple_T) moveShape;
                for (int r = 0; r < moveShape.states; r++) {
                    R.rotate_All();
                    currentXYofShape = moveDownAndEvaluate(R,AllShapesClone,GridClone, currentXYofShape);

                }

            } else if (moveShape.getType() == 5) {
                moveShape = new Red_Z(start_X);
                Red_Z R = (Red_Z) moveShape;
                for (int r = 0; r < moveShape.states; r++) {
                    R.rotate_All();
                    currentXYofShape = moveDownAndEvaluate(R,AllShapesClone,GridClone, currentXYofShape);

                }

            }else {
                moveShape = new Yellow_square(start_X);

                currentXYofShape = moveDownAndEvaluate(moveShape,AllShapesClone,GridClone, currentXYofShape);


            }


            start_X+=Shape.getSIZE();
        }

        return currentXYofShape;
    }

    private double[][] moveDownAndEvaluate(Shape moveShape, List<Shape> AllShapesClone, Spot[][] GridClone, double[][] currentXYofShape){
        double weight_a = -0.510066;
        double weight_b = 0.76066;
        double weight_c = -0.35663;
        double weight_d = -0.18443;


        AllShapesClone.remove(AllShapesClone.size()-1);
        AllShapesClone.add(moveShape);

        while (!checkCollisionForAI(moveShape)){
            moveShape.move_down();
        }

        Spot[][] Grid = Spot.resetGrid(Shape.getAll_Shapes(), GridClone);
        printGrid();

        double fitness = weight_a * aggregateHeights(Grid) + weight_b * getNumberOfLines(Grid) +
                weight_c * getNumberOfHoles(Grid) + weight_d * getBumps(Grid);


        if(fitness<currentXYofShape[4][0]){
            currentXYofShape[4][0] = fitness;

            int index = 0;
            for(Block block:moveShape.getAllblocks()){
                currentXYofShape[index] = new double[]{block.x,block.y};
                index++;
            }


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




}
