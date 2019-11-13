package proc.sketches.AI;

import proc.sketches.Blocks.Block;
import proc.sketches.Grid.Spot;
import proc.sketches.Shapes.Shape;

public class AI {
    private Spot[][] Grid;
    private int[][] bitMap;


    public AI(Spot[][] Grid, Shape movingSpot) {

        this.bitMap = getGridInBits(movingSpot);
        this.Grid = Grid;

    }

    public AI() {
        this.Grid = null;
    }

    private int[][] getGridInBits(Shape movingSpot) {
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


    public int getNumberOfHoles(Shape movingBlock) {

        bitMap = getGridInBits(movingBlock);
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

    public int getBumps(Shape moveShape) {
        int[][] bitmap = getGridInBits(moveShape);

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
            System.out.println(values[v]);
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

    public int aggregateHeights(Shape moveShape) {
        int[][] bitmap = getGridInBits(moveShape);

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

    public int getNumberOfLines(Shape moveShape) {
        int[][] bitmap = getGridInBits(moveShape);
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

    public int[] bestMove(Shape moveShape) throws CloneNotSupportedException {
        double weight_a = -0.510066;
        double weight_b = 0.76066;
        double weight_c = -0.35663;
        double weight_d = -0.18443;
        moveShape.clone();
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


        for (int x = 0; x < Shape.getNum_X(); x++) {
            for (int r = 0; r < moveShape.states; r++) {

                Spot[][] Grid = Spot.getGrid().clone();


                double fitness = weight_a * aggregateHeights(moveShape) + weight_b * getNumberOfLines(moveShape) +
                        weight_c * getNumberOfHoles(moveShape) + weight_d * getBumps(moveShape);


            }
            moveShape.move_right();
        }

        return new int[]{0,0};
    }





    public void setGrid(Spot[][] grid) {
        Grid = grid;
    }

    public Spot[][] getGrid() {
        return Grid;
    }



    public int[][] getBitMap() {
        return bitMap;
    }
}
