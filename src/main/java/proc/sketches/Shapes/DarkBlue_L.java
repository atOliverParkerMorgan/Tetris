package proc.sketches.Shapes;

import proc.sketches.Blocks.Block;

import java.util.ArrayList;


public class DarkBlue_L extends Shape{
    private byte flat;
    public final int start_X;
    // rotate positions

    private final int[][] rotate1 = new int[][]{{0,-2*SIZE},{SIZE,-SIZE},{0,0},{-SIZE,SIZE}};

    private final int[][] rotate2 = new int[][]{{2*SIZE,0},{SIZE,SIZE},{0,0},{-SIZE,-SIZE}};

    private final int[][] rotate3 = new int[][]{{0,2*SIZE},{-SIZE,SIZE},{0,0},{SIZE,-SIZE}};

    private final int[][] rotate4 = new int[][]{{-2*SIZE,0},{-SIZE,-SIZE},{0,0},{SIZE,SIZE}};

    private final int[][] structure = new int[][]{{-SIZE, SIZE}, {-SIZE, 0}, {0, 0}, {SIZE, 0}};

    public DarkBlue_L() {
        super(1,(byte) 4);

        this.flat = 1;
        this.start_X = max_X/2-2*SIZE;
        int start_Y = 0;

        // starting structure

        for(int index = 0; index < numberOfBlocks; index++){

            Block b = new Block(SIZE, start_X + structure[index][0], start_Y + structure[index][1]);
            this.allblocks.add(b);
        }
    }





    public void rotate_All(){
        flat = rotateFourStates(flat,rotate1,rotate2,rotate3,rotate4);
    }

    public void ChangeYto0(){
        this.allblocks = new ArrayList<>();
        for(int index = 0; index < numberOfBlocks; index++){
            Block b = new Block(SIZE, start_X + structure[index][0], structure[index][1]);
            this.allblocks.add(b);
        }
    }
}
