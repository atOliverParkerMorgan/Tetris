package proc.sketches.Shapes;

import proc.sketches.Blocks.Block;

public class Green_S extends Shape{
    private boolean flat;

    // rotate positions

    private final int[][] rotate1 = new int[][]{{2*SIZE,0},{SIZE,-SIZE},{0,0},{-SIZE,-SIZE}};

    private final int[][] rotate2 = new int[][]{{-2*SIZE,0},{-SIZE,SIZE},{0,0},{SIZE,SIZE}};

    public Green_S() {
        super(2, (byte) 2);

        this.flat = false;
        int number_of_blocks = 4;

        int start_X = max_X/2-2*SIZE;
        int start_Y = 0;

        for(int index = 0; index < number_of_blocks; index++){
            int[][] structure = new int[][]{{0,0}, {0, SIZE}, {SIZE,SIZE}, {SIZE, 2*SIZE}};
            Block b = new Block(SIZE, start_X + structure[index][0], start_Y + structure[index][1]);
            this.allblocks.add(b);
        }
    }





    public void rotate_All(){
        flat = rotateTwoStates(flat, rotate1,rotate2);

    }
}
