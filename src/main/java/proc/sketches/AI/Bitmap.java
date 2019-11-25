package proc.sketches.AI;

import proc.sketches.Blocks.Block;
import proc.sketches.Grid.Spot;
import proc.sketches.Shapes.Shape;

public class Bitmap {
    /*
    Bitmap stores all grid information in simplified array
    1) if there is a block occupying a spot it is represented as a 1
    2) if these is spot that isn't occupied by a block than it is represented by a 0

    *
    in this bitmap the moving shape is just an array of coordinates (or indexes according the bitmap) of the blocks
    the block that has the smallest x and y values (is the closest to the top left corner) is the first cords in this
    array !but only in the default structure! this doesn't have to apply if the shape rotates
    *

    (the rotation has it's own rules):
    1) the array that contains zero values is the center axis of the rotation
    2) each rotation depend on the values of previous rotation

    we can do these operation with the moving shape:
    1) rotate
    2) move down => move down until collision
    3) move up => move up to until any block of the shape has the value: {y = 0}
    4) move left => move left to until any block of the shape has the value: {x = 0}
    5) move right

     */


    // bitmap values
    int[][] Bitmap;
    private int[][] movingShapeCords;
    private int categoryOfMovingShape;
    // number of possible rotation states => 2, 3, 4
    private int state = 0;


    // different rotation positions for different shapes
    private static final int[][] I1 = new int[][]{{-1,1},{0,0},{1,-1},{2, -2}};
    private static final int[][] I2 = new int[][]{{1,-1},{0,0},{-1,1},{-1*2, 2}};
    private static final int[][][] IALL = new int[][][]{I2,I1};

    private static final int[][] L1 = new int[][]{{0, -2},{1,-1},{0,0},{-1,1}};
    private static final int[][] L2 = new int[][]{{2,0},{1,1},{0,0},{-1,-1}};
    private static final int[][] L3 = new int[][]{{0, 2},{-1,1},{0,0},{1,-1}};
    private static final int[][] L4 = new int[][]{{-2,0},{-1,-1},{0,0},{1,1}};
    private static final int[][][] LALL = new int[][][]{L1,L2,L3,L4};

    private static final int[][] S1 = new int[][]{{2,0},{1,-1},{0,0},{-1,-1}};
    private static final int[][] S2 = new int[][]{{-2,0},{-1,1},{0,0},{1,1}};
    private static final int[][][] SALL = new int[][][]{S1,S2};

    private static final int[][] J1 = new int[][]{{2,0},{1,-1},{0,0},{-1,1}};
    private static final int[][] J2 = new int[][]{{0, 2},{1,1},{0,0},{-1,-1}};
    private static final int[][] J3 = new int[][]{{-2,0},{-1,1},{0,0},{1,-1}};
    private static final int[][] J4 = new int[][]{{0, -2},{-1,-1},{0,0},{1,1}};
    private static final int[][][] JALL = new int[][][]{J1,J2,J3,J4};

    private static final int[][] T1 = new int[][]{{-1,-1},{1,-1},{0,0},{-1,1}};
    private static final int[][] T2 = new int[][]{{1,-1},{1,1},{0,0},{-1,-1}};
    private static final int[][] T3 = new int[][]{{1,1},{-1,1},{0,0},{1,-1}};
    private static final int[][] T4 = new int[][]{{-1,1},{-1,-1},{0,0},{1,1}};
    private static final int[][][] TALL = new int[][][]{T1,T2,T3,T4};

    private static final int[][] Z1 = new int[][]{{0, 2},{-1,1},{0,0},{-1,-1}};
    private static final int[][] Z2 = new int[][]{{0, -2},{1,-1},{0,0},{1,1}};
    private static final int[][][] ZALL = new int[][][]{Z1,Z2};

    // an array that contains all rotation cords (so it's easier to access them in a for loop)
    private static final int[][][][] ALLALL = new int[][][][]{IALL,LALL,SALL,JALL,TALL,ZALL};



    Bitmap(int[][] movingShapeCords, int Category){
        this.categoryOfMovingShape = Category;
        this.movingShapeCords = movingShapeCords;
        this.Bitmap = new int[Shape.getNum_Y()][Shape.getNum_X()];
        //syncBitmapWithGrid(Grid);


    }
    void resetBitmap(){
        // set all bitmap values to 1 or unoccupied
        for(int y=0;y<Shape.getNum_Y();y++){
            for(int x=0;x<Shape.getNum_X();x++){
                Bitmap[y][x] = 1;
            }
        }


    }


    private void moveToCenter(){
        // this method is accessed so when the shape rotates it doesn't overlap the canvas

        // the space between the top of the canvas and the shape
        int bound = 2;

        while (true) {
            if (movingShapeCords[0][1] > bound) {
                moveUp();
            }else if(movingShapeCords[0][1] < bound){
                moveDown();
            }else if(movingShapeCords[0][0] > Shape.getNum_X()/2){
                moveLeft();
            }else if(movingShapeCords[0][0] < Shape.getNum_X()/2){
                moveRight();
            }else {
                break;
            }
        }
    }

    void syncBitmapWithGrid(Spot[][] Grid, Shape movingShape){
        // sets all values in bitmap to reflect the Grid boolean occupied values

        for (int y = 0; y < Shape.getNum_Y(); y++) {
            for (int x = 0; x < Shape.getNum_X(); x++) {
                if (Grid[y][x].isOccupied()) {
                    Bitmap[y][x] = 0;
                } else {
                    Bitmap[y][x] = 1;
                }
            }
        }
        // delete the values of the original movingShape
        for(Block block: movingShape.getAllblocks()){
            Bitmap[(int) block.y/Shape.getSIZE()][(int) block.x/Shape.getSIZE()] = 1;
        }

        // add the the values of the bitmap movingShape cords
        for (int[] movingShapeCord : movingShapeCords) {

            Bitmap[movingShapeCord[1]][movingShapeCord[0]] = 0;
        }




    }


    void moveMovingShapeDown(){
        // move shape down until collision
        mainLoop:
        while (true){
            for(int[] cords : movingShapeCords){
                if(cords[1]+1==Shape.getNum_Y()){

                    break mainLoop;

                }

                else if(getBit(cords[0],cords[1]+1)==0){
                    boolean found = false;
                    for(int[] c : movingShapeCords) {
                        if (c[1] == cords[1] + 1 && c[0] == cords[0]) {
                            found = true;
                            break;
                        }
                    }
                    if(!found) {
                        break mainLoop;
                    }

                }
            }

            moveDown();


        }

    }
    void moveLeft(){
        // move shape left and update values (set old spot to 1 and new to 0)
        for(int i=0;i<movingShapeCords.length;i++){
            Bitmap[movingShapeCords[i][1]][movingShapeCords[i][0]] = 1;
            movingShapeCords[i][0] --;
            Bitmap[movingShapeCords[i][1]][movingShapeCords[i][0]] = 0;
        }

    }
    boolean checkMoveLeft(){
        for (int[] cord:movingShapeCords) {
            if(cord[0]==0) {
                return false;
            }
            if (getBit(cord[0] - 1, cord[1]) == 0) {
                return false;
            }
        }
        return true;

    }

    void moveRight(){
        // move shape right and update values
        for(int i=0;i<movingShapeCords.length;i++){
            Bitmap[movingShapeCords[i][1]][movingShapeCords[i][0]] = 1;
            movingShapeCords[i][0] ++;
            Bitmap[movingShapeCords[i][1]][movingShapeCords[i][0]] = 0;
        }
    }
    boolean checkMoveRight(){
        for (int[] cord:movingShapeCords) {
            if(cord[0]+1==Shape.getNum_X()) {
                return false;
            }
            if (getBit(cord[0] + 1, cord[1]) == 0) {
                return false;
            }
        }
        return true;

    }




    private void moveDown(){
        // move shape down and update values
        for(int i=0;i<movingShapeCords.length;i++){
            Bitmap[movingShapeCords[i][1]][movingShapeCords[i][0]] = 1;
            movingShapeCords[i][1] ++;
            Bitmap[movingShapeCords[i][1]][movingShapeCords[i][0]] = 0;
        }
    }

    int getBit(int x, int y){
        // get the bit in the array
        return Bitmap[y][x];

    }
    void moveShapeLeft(){
        // move the shape all the way to the left
        mainLoop:
        while (true){
            for (int[] cord:movingShapeCords) {
                if(cord[0]==0){
                    break mainLoop;
                }
            }
            moveLeft();
        }
    }




    int getNumberOfCycles(){
        // get the number of cycles needed in a for loop to go through all the possible possibilities (from left to right)
        int biggestX = Integer.MIN_VALUE;

        for (int[] cord:movingShapeCords) {
            if(cord[0]>biggestX){
                biggestX = cord[0];

            }
        }
        return Shape.getNum_X()-1-biggestX;

    }

    public void rotate(){
        // rotate shape and update the state
        if(this.categoryOfMovingShape!=6) {
            moveToCenter();
            for (int i = 0; i < Shape.numberOfBlocks; i++) {

                movingShapeCords[i][0] += ALLALL[categoryOfMovingShape][state][i][0];
                movingShapeCords[i][1] += ALLALL[categoryOfMovingShape][state][i][1];
            }
            if (categoryOfMovingShape == 0 || categoryOfMovingShape == 2 || categoryOfMovingShape == 5) {
                if (state == 1) {
                    state = 0;
                } else if (state == 0) {
                    state = 1;
                }
            } else {
                if (state == 3) {
                    state = 0;
                } else {
                    state++;
                }

            }

            while (true) {
                byte check = 0;

                for (int[] movingShapeCord : movingShapeCords) {
                    if (movingShapeCord[0] >= 0 && movingShapeCord[1] >= 0) {
                        check++;

                    }
                }
                if (check == 4) {
                    break;
                }


                for (int i = 0; i < movingShapeCords.length; i++) {
                    if (movingShapeCords[i][0] < 0) {
                        movingShapeCords[i][0]++;
                    }
                    if (movingShapeCords[i][1] < 0) {
                        movingShapeCords[i][1]++;
                    }
                }
            }
        }
    }

    void moveMovingShapeUp(){
        // move the shape to the top
        mainLoop:
        while (true){
            for(int[] cords : movingShapeCords){
                if(cords[1]<=0){
                    break mainLoop;
                }
            }
           moveUp();

        }
    }
    private void moveUp(){
        // move shape up and update values
        for(int i=0;i<movingShapeCords.length;i++){
            Bitmap[movingShapeCords[i][1]][movingShapeCords[i][0]] = 1;
            movingShapeCords[i][1] --;
            Bitmap[movingShapeCords[i][1]][movingShapeCords[i][0]] = 0;
        }
    }

    double[][] getCords(double[][] cords){
        // get the coordinates of the moving shape
        for(int i = 0;i<4;i++ ){
            cords[i][0] = movingShapeCords[i][0];
            cords[i][1] = movingShapeCords[i][1];
        }
        return cords;


    }







}
