package proc.sketches.Shapes;

import proc.sketches.Blocks.Block;

import java.util.ArrayList;

public class Green_S extends Shape implements Cloneable{
    private boolean flat;
    public final int start_X;
    // rotate positions

    private final int[][] rotate1 = new int[][]{{2*SIZE,0},{SIZE,-SIZE},{0,0},{-SIZE,-SIZE}};

    private final int[][] rotate2 = new int[][]{{-2*SIZE,0},{-SIZE,SIZE},{0,0},{SIZE,SIZE}};

    private final int[][] structure = new int[][]{{0,0}, {0, SIZE}, {SIZE,SIZE}, {SIZE, 2*SIZE}};

    public Green_S(int start_X) {
        super(2, (byte) 2);

        this.flat = false;
        this.start_X = start_X;
        int start_Y = 0;

        for(int index = 0; index < numberOfBlocks; index++){
            Block b = new Block(SIZE, start_X + structure[index][0], start_Y + structure[index][1]);
            this.allblocks.add(b);
        }
    }





    public void rotate_All(){
        if(!this.flat){
            int[][] all = new int[][]{rotate1[0],rotate1[1],rotate1[2],rotate1[3]};
            if(this.rotate(all, SIZE)) {
                this.flat = true;
            }
        }else{
            int[][] all = new int[][]{rotate2[0],rotate2[1],rotate2[2],rotate2[3]};
            if(this.rotate(all, SIZE)) {
                this.flat = false;
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
