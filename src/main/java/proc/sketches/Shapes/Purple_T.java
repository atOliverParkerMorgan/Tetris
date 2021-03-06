package proc.sketches.Shapes;

import proc.sketches.Blocks.Block;

public class Purple_T extends Shape {
    private byte flat;

    // rotate positions
    private final int[][] rotate1 = new int[][]{{-SIZE,-SIZE},{SIZE,-SIZE},{0,0},{-SIZE,SIZE}};

    private final int[][] rotate2 = new int[][]{{SIZE,-SIZE},{SIZE,SIZE},{0,0},{-SIZE,-SIZE}};

    private final int[][] rotate3 = new int[][]{{SIZE,SIZE},{-SIZE,SIZE},{0,0},{SIZE,-SIZE}};

    private final int[][] rotate4 = new int[][]{{-SIZE,SIZE},{-SIZE,-SIZE},{0,0},{SIZE,SIZE}};

    public Purple_T() {
        super(4, (byte) 4);

        this.flat = 1;
        int number_of_blocks = 4;

        int start_X = max_X/2-2*SIZE;

        for(int index = 0; index < number_of_blocks; index++){
            int[][] structure = new int[][]{{0, SIZE}, {-SIZE, 0}, {0, 0}, {SIZE, 0}};
            Block b = new Block(SIZE, start_X + structure[index][0], SIZE + structure[index][1]);
            this.allblocks.add(b);
        }
    }





    public void rotate_All(){
        flat = rotateFourStates(flat,rotate1,rotate2,rotate3,rotate4);
    }
}
