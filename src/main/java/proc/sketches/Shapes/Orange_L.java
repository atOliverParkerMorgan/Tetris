package proc.sketches.Shapes;

import proc.sketches.Blocks.Block;


public class Orange_L extends Shape{
    private byte flat;
    private final int start_X;
    // rotate positions
    private final int[][] rotate1 = new int[][]{{2*SIZE,0},{SIZE,-SIZE},{0,0},{-SIZE,SIZE}};

    private final int[][] rotate2 = new int[][]{{0,2*SIZE},{SIZE,SIZE},{0,0},{-SIZE,-SIZE}};

    private final int[][] rotate3 = new int[][]{{-2*SIZE,0},{-SIZE,SIZE},{0,0},{SIZE,-SIZE}};

    private final int[][] rotate4 = new int[][]{{0,-2*SIZE},{-SIZE,-SIZE},{0,0},{SIZE,SIZE}};

    private final int[][] structure = new int[][]{{-SIZE,-SIZE}, {-SIZE, 0}, {0, 0}, {SIZE, 0}};

    public Orange_L() {
        super(3, (byte) 4);
        this.start_X = max_X/2-2*SIZE;
        this.flat = 1;

        for(int index = 0; index < numberOfBlocks; index++){

            Block b = new Block(SIZE, start_X + structure[index][0], SIZE + structure[index][1]);
            this.allblocks.add(b);
        }
    }





    public void rotate_All(){
        flat = rotateFourStates(flat,rotate1,rotate2,rotate3,rotate4);
    }


}
