package proc.sketches.Shapes;

import proc.sketches.Blocks.Block;


public class DarkBlue_L extends Shape{
    private byte flat;

    // rotate positions

    private final int[][] rotate1 = new int[][]{{0,-2*SIZE},{SIZE,-SIZE},{0,0},{-SIZE,SIZE}};

    private final int[][] rotate2 = new int[][]{{2*SIZE,0},{SIZE,SIZE},{0,0},{-SIZE,-SIZE}};

    private final int[][] rotate3 = new int[][]{{0,2*SIZE},{-SIZE,SIZE},{0,0},{SIZE,-SIZE}};

    private final int[][] rotate4 = new int[][]{{-2*SIZE,0},{-SIZE,-SIZE},{0,0},{SIZE,SIZE}};

    public DarkBlue_L() {
        super(1,(byte) 4);

        this.flat = 1;
        int number_of_blocks = 4;

        int start_X = max_X/2-2*SIZE;
        int start_Y = 0;

        // starting structure
        int[][] structure = new int[][]{{-SIZE, SIZE}, {-SIZE, 0}, {0, 0}, {SIZE, 0}};

        for(int index = 0; index < number_of_blocks; index++){

            Block b = new Block(SIZE, start_X + structure[index][0], start_Y + structure[index][1]);
            this.allblocks.add(b);
        }
    }





    public void rotate_All(){
        flat = rotateFourStates(flat,rotate1,rotate2,rotate3,rotate4);
    }
}
