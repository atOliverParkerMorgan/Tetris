package proc.sketches.AI;

import proc.sketches.Grid.Spot;
import proc.sketches.Shapes.Shape;

public class Bitmap {

    private int[][] Bitmap;
    private int[][] movingShapeCords;
    Bitmap(int[][] movingShapeCords){
        this.Bitmap = new int[Shape.getNum_Y()][Shape.getNum_X()];

        this.resetBitmap(movingShapeCords);


    }

    private void resetBitmap(int[][] movingShapeCords){
        for(int[] line: Bitmap){
            for(int bit: line){
                bit = 1;
            }
        }
        this.movingShapeCords = movingShapeCords;

    }

    private void syncBitmapWithGrid(Spot[][] Grid){
        for (int y = 0; y < Shape.getNum_Y(); y++) {
            for (int x = 0; x < Shape.getNum_X(); x++) {
                if (Grid[y][x].isOccupied()) {
                    Bitmap[y][x] = 0;
                } else {
                    Bitmap[y][x] = 1;
                }
            }
        }

    }
    private boolean leftBound(){
        for(int[] cords : movingShapeCords){
            if(cords[0]-1<0){
                return false;
            }
        }
        return true;
    }
    private boolean rightBound(){
        for(int[] cords : movingShapeCords){
            if(cords[0]+1>Shape.getNum_X()-1){
                return false;
            }
        }
        return true;
    }

    private void moveMovingShapeDown(){
        for(int[] cords : movingShapeCords){
            cords[1] ++;
        }

    }
    private void moveMovingShapeLeft(){
        for(int[] cords : movingShapeCords){
            cords[0] --;
        }

    }
    private void moveMovingShapeRight(){
        for(int[] cords : movingShapeCords){
            cords[0] ++;
        }

    }
    private boolean checkCollisions(){
        for(int[] cords : movingShapeCords){
            if(getBit(cords[0],cords[1]+1)==0){
                return false;
            }
        }
        return true;

    }
    private int getBit(int x, int y){
        return Bitmap[y][x];

    }




}
