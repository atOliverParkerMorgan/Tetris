package proc.sketches.Shapes;

import proc.sketches.Blocks.Block;

import java.io.Serializable;

public class DarkBlue_L extends Shape implements Serializable {
    public DarkBlue_L() {
        super(1);
        int number_of_blocks = 4;

        int start_X = max_X/2-SIZE;
        int start_Y = 0;
        final int[][] structure = new int[][]{{SIZE,SIZE},{SIZE,0},{2*SIZE,0},{3*SIZE,0}};

        for(int index = 0; index < number_of_blocks; index++){
            Block b = new Block(SIZE, start_X + structure[index][0], start_Y + structure[index][1]);
            this.allblocks.add(b);
        }
    }
}
