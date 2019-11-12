package proc.sketches.AI;

import proc.sketches.Blocks.Block;
import proc.sketches.Grid.Spot;
import proc.sketches.Shapes.Shape;

public class AI {
    private Spot[][] Grid;
    private int[][] bitMap;


    public AI(Spot[][] Grid, Shape movingSpot){

        this.bitMap = getGridInBits(movingSpot);
        this.Grid = Grid;

    }
    public AI(){
        this.Grid = null;
    }

    private int[][] getGridInBits(Shape movingSpot){
        int[][] bitMap = new int[Shape.getNum_Y()][Shape.getNum_X()];
        for(int y = 0; y<Shape.getNum_Y(); y++){
            for(int x = 0; x<Shape.getNum_X(); x++){
                if(Grid[y][x].isOccupied()){
                    bitMap[y][x] = 0;
                }else{
                    bitMap[y][x] = 1;
                }
            }
        }

        for(Block block: movingSpot.getAllblocks()){
            if((int) block.y / Shape.getSIZE()>0 && (int) block.y / Shape.getSIZE()<Shape.getNum_Y()) {
                bitMap[(int) block.y / Shape.getSIZE()][(int) block.x / Shape.getSIZE()] = 1;
            }

        }


        return bitMap;


    }
    private boolean isHole(int x, int y, int[][] bitMap){
        if(x!=Shape.getNum_X()-1){
            if(bitMap[y][x+1]==0){
                return true;
            }
        }if(x!=0){
            if(bitMap[y][x-1]==0){
                return true;
            }
        }if(y!=0){
            return bitMap[y - 1][x] == 0;
        }
        return false;

    }


    public int getNumberOfHoles(Shape movingBlock){

        bitMap = getGridInBits(movingBlock);
        int foundHoles = 0;

        for(int y = 0; y<Shape.getNum_Y(); y++){
            for(int x = 0; x<Shape.getNum_X(); x++){
                if(bitMap[y][x]==1) {
                   if(isHole(x,y,bitMap)){
                       foundHoles++;

                   }

                }
            }
        }


        return foundHoles;
    }
    public int getLineDifference(Shape moveShape){
        int[][] bitmap = getGridInBits(moveShape);

        int[] values = new int[Shape.getNum_X()];

        int index = 0;
        for(int x = 0; x<Shape.getNum_X();x++){
            int highest = 0;
            for(int y = 0; y<Shape.getNum_Y();y++){
                if(bitmap[y][x] == 0){
                    if(highest<20-y) {
                        highest = 20-y;
                    }
                }
            }
            values[index] = highest;
            index++;
        }
        int all = 0;
        for(int v = 0; v<Shape.getNum_X(); v++){
            System.out.println(values[v]);
            if(v+1<values.length) {
                int difference = values[v] - values[v + 1];
                if(difference < 0){
                    all+= -difference;
                }else {
                    all+= difference;
                }
            }
        }


        return all;
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
