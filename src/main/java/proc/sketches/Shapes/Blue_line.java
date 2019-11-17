package proc.sketches.Shapes;

import proc.sketches.Blocks.Block;

import java.util.ArrayList;

public class Blue_line extends Shape implements Cloneable {

    private boolean flat;

    public final int start_X;

    // rotate cords
    private final int[][] rotate1 = new int[][]{{-SIZE,SIZE},{0,0},{SIZE,-SIZE},{SIZE*2,-2*SIZE}};
    private final int[][] rotate2 = new int[][]{{SIZE,-SIZE},{0,0},{-SIZE,SIZE},{-SIZE*2,2*SIZE}};
    private final int[][] structure = new int[][]{{-SIZE,0},{0,0},{SIZE,0},{SIZE*2,0}};

    public Blue_line(int start_X){
        super(0, (byte) 2);
        this.start_X = start_X;
        this.flat = true;

        // half of the screen
        int start_Y = 0;

        // starting structure

        for(int index = 0; index < numberOfBlocks; index++){
            Block b = new Block(SIZE, start_X + structure[index][0], start_Y + structure[index][1]);
            this.allblocks.add(b);
        }

    }



    public void rotate_All(){
        if(this.flat){
            int[][] all = new int[][]{rotate2[0],rotate2[1],rotate2[2],rotate2[3]};
            if(this.rotate(all, 2*SIZE)) {
                this.flat = false;
            }
        }else{
            int[][] all = new int[][]{rotate1[0],rotate1[1],rotate1[2],rotate1[3]};
            if(this.rotate(all, 2*SIZE)) {
                this.flat = true;
            }
        }
    }

    public void ChangeYto0(){
        this.allblocks = new ArrayList<>();
        for(int index = 0; index < numberOfBlocks; index++){
            Block b = new Block(SIZE, start_X + structure[index][0], structure[index][1]);
            this.allblocks.add(b);
        }
    }
}
