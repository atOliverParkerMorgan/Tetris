package proc.sketches.AI;

import proc.sketches.Blocks.Block;
import proc.sketches.Grid.Spot;
import proc.sketches.Shapes.Shape;

public class AI {
    private Spot[][] Grid;

    private int numberOfHoles;
    private int lineClearing;
    private int bumps;
    private int[][] bitMap;


    public AI(Spot[][] Grid, Shape movingSpot){

        this.bitMap = getGridInBits(movingSpot);
        this.Grid = Grid;
        //this.numberOfHoles = getNumberOfHoles();
        this.lineClearing = 0;
        this.bumps = 0;
    }
    public AI(){
        this.Grid = null;
        this.numberOfHoles = 0;
        this.lineClearing = 0;
        this.bumps = 0;
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
        }if(y!=Shape.getNum_Y()-1){
            return bitMap[y + 1][x] == 0;
        }
        return false;

    }


    public int[][] getNumberOfHoles(Shape movingBlock){

        int[][] bitMap = getGridInBits(movingBlock);
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


        return  bitMap;




    }

    public void setGrid(Spot[][] grid) {
        Grid = grid;
    }

    public Spot[][] getGrid() {
        return Grid;
    }

    public int getNumberOfHoles() {
        return numberOfHoles;
    }

    public int getLineClearing() {
        return lineClearing;
    }

    public int getBumps() {
        return bumps;
    }

    public int[][] getBitMap() {
        return bitMap;
    }
}
