package proc.sketches.AI;

import proc.sketches.Blocks.Block;
import proc.sketches.Grid.Spot;
import proc.sketches.Shapes.Shape;

public class Bitmap {

    int[][] Bitmap;
    private int[][] movingShapeCords;
    private int categoryOfMovingShape;
    private int state = 0;

    private static final int[][] I1 = new int[][]{{-1,1},{0,0},{1,-1},{2, -2}};
    private static final int[][] I2 = new int[][]{{1,-1},{0,0},{-1,1},{-1*2, 2}};
    private static final int[][][] IALL = new int[][][]{I1,I2};

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

    private static final int[][][][] ALLALL = new int[][][][]{IALL,LALL,SALL,JALL,TALL,ZALL};



    Bitmap(int[][] movingShapeCords,Spot[][] Grid, int Category){
        this.categoryOfMovingShape = Category;
        this.movingShapeCords = movingShapeCords;
        this.Bitmap = new int[Shape.getNum_Y()][Shape.getNum_X()];
        //syncBitmapWithGrid(Grid);


    }

    private void resetBitmap(int[][] movingShapeCords){
        for(int[] line: Bitmap){
            for(int bit: line){
                bit = 1;
            }
        }


    }

    void syncBitmapWithGrid(Spot[][] Grid, Shape movingShape){


        for (int y = 0; y < Shape.getNum_Y(); y++) {
            for (int x = 0; x < Shape.getNum_X(); x++) {
                if (Grid[y][x].isOccupied()) {
                    Bitmap[y][x] = 0;
                } else {
                    Bitmap[y][x] = 1;
                }
            }
        }
        for(Block block: movingShape.getAllblocks()){
            Bitmap[(int) block.y/Shape.getSIZE()][(int) block.x/Shape.getSIZE()] = 1;
        }

        for (int[] movingShapeCord : movingShapeCords) {

            Bitmap[movingShapeCord[1]][movingShapeCord[0]] = 0;
        }




    }


    void moveMovingShapeDown(){
        mainLoop:
        while (true){
            for(int[] cords : movingShapeCords){
                if(cords[1]+1==Shape.getNum_Y()){

                    break mainLoop;

                }

                else if(getBit(cords[0],cords[1]+1)==0){
                   break mainLoop;
                }
            }

            moveDown();


        }

    }
    private void moveMovingShapeLeft(){
        for(int i=0;i<movingShapeCords.length;i++){
            Bitmap[movingShapeCords[i][1]][movingShapeCords[i][0]] = 1;
            movingShapeCords[i][0] --;
            Bitmap[movingShapeCords[i][1]][movingShapeCords[i][0]] = 0;
        }

    }
    void moveMovingShapeRight(){
        for(int i=0;i<movingShapeCords.length;i++){
            Bitmap[movingShapeCords[i][1]][movingShapeCords[i][0]] = 1;
            movingShapeCords[i][0] ++;
            Bitmap[movingShapeCords[i][1]][movingShapeCords[i][0]] = 0;
        }
    }
    private void moveDown(){
        for(int i=0;i<movingShapeCords.length;i++){
            Bitmap[movingShapeCords[i][1]][movingShapeCords[i][0]] = 1;
            movingShapeCords[i][1] ++;
            Bitmap[movingShapeCords[i][1]][movingShapeCords[i][0]] = 0;
        }
    }

    int getBit(int x, int y){
        return Bitmap[y][x];

    }
    void moveShapeLeft(){
        mainLoop:
        while (true){
            for (int[] cord:movingShapeCords) {
                if(cord[0]==0){
                    break mainLoop;
                }
            }
            moveMovingShapeLeft();
        }
    }

    int getNumberOfCycles(){
        int biggestX = Integer.MIN_VALUE;

        for (int[] cord:movingShapeCords) {
            if(cord[0]>biggestX){
                biggestX = cord[0];

            }
        }
        return Shape.getNum_X()-1-biggestX;

    }

    public void rotate(){
        for (int i=0; i<Shape.numberOfBlocks; i++){

            movingShapeCords[i][0]+=ALLALL[categoryOfMovingShape][state][i][0];
            movingShapeCords[i][1]+=ALLALL[categoryOfMovingShape][state][i][1];
        }
        if(categoryOfMovingShape==0||categoryOfMovingShape==2||categoryOfMovingShape==5){
            if(state==1){
                state = 0;
            }else if(state==0){
                state = 1;
            }
        }else{
            if(state==3){
                state = 0;
            }else{
                state ++;
            }

        }

        while (true){
            byte check = 0;

            for (int[] movingShapeCord : movingShapeCords) {
                if (movingShapeCord[0] >= 0 && movingShapeCord[1] >= 0) {
                    check++;

                }
            }
            if(check==4){
                break;
            }


            for(int i=0;i<movingShapeCords.length;i++){
                if (movingShapeCords[i][0] < 0) {
                    movingShapeCords[i][0] ++;
                }
                if (movingShapeCords[i][1] < 0) {
                    movingShapeCords[i][1] ++;
                }
            }
        }
    }

    void moveUp(){
        mainLoop:
        while (true){
            for(int[] cords : movingShapeCords){
                if(cords[1]<=0){
                    break mainLoop;
                }
            }
            for(int i=0;i<movingShapeCords.length;i++){
                Bitmap[movingShapeCords[i][1]][movingShapeCords[i][0]] = 1;
                movingShapeCords[i][1] --;
                Bitmap[movingShapeCords[i][1]][movingShapeCords[i][0]] = 0;
            }

        }
    }

    double[][] getCords(double[][] cords){
        for(int i = 0;i<4;i++ ){
            cords[i][0] = movingShapeCords[i][0];
            cords[i][1] = movingShapeCords[i][1];
        }
        return cords;


    }







}
