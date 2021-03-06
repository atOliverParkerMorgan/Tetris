package proc.sketches.Shapes;

import proc.sketches.Blocks.Block;

public class Yellow_square extends Shape{
    public Yellow_square() {
        super(6, (byte) 1);

        int number_of_blocks = 4;

        int start_X = max_X/2-2*SIZE;
        int start_Y = 0;

        for(int index = 0; index < number_of_blocks; index++){
            int[][] structure = new int[][]{{0,0}, {SIZE, 0}, {0,SIZE}, {SIZE, SIZE}};
            Block b = new Block(SIZE, start_X + structure[index][0], start_Y + structure[index][1]);
            this.allblocks.add(b);
        }
    }
    // a square does not rotate so no rotate implementation

}
