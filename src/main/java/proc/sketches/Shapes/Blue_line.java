package proc.sketches.Shapes;

import proc.sketches.Blocks.Block;

public class Blue_line extends Shape{

    private boolean flat;

    // rotate cords
    private final int[][] rotate1 = new int[][]{{-SIZE,SIZE},{0,0},{SIZE,-SIZE},{SIZE*2,-2*SIZE}};
    private final int[][] rotate2 = new int[][]{{SIZE,-SIZE},{0,0},{-SIZE,SIZE},{-SIZE*2,2*SIZE}};

    public Blue_line(){
        super(0, (byte) 2);

        this.flat = true;
        int number_of_blocks = 4;

        // half of the screen
        int start_X = max_X/2-SIZE;
        int start_Y = 0;

        // starting structure
        final int[][] structure = new int[][]{{-SIZE,0},{0,0},{SIZE,0},{SIZE*2,0}};

        for(int index = 0; index < number_of_blocks; index++){
            Block b = new Block(SIZE, start_X + structure[index][0], start_Y + structure[index][1]);
            this.allblocks.add(b);
        }

    }



    public void rotate_All(){
        flat = rotateTwoStates(flat, rotate1,rotate2);
    }


}
