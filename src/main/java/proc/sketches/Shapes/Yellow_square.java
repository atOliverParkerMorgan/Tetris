package proc.sketches.Shapes;

import proc.sketches.Blocks.Block;

import java.util.ArrayList;

public class Yellow_square extends Shape implements Cloneable{
    public final int start_X;
    private final int[][] structure = new int[][]{{0,0}, {SIZE, 0}, {0,SIZE}, {SIZE, SIZE}};
    public Yellow_square(int start_X) {
        super(6, (byte) 1);
        this.start_X = start_X;
        int start_Y = 0;

        for(int index = 0; index < numberOfBlocks; index++){
            Block b = new Block(SIZE, start_X + structure[index][0], start_Y + structure[index][1]);
            this.allblocks.add(b);
        }
    }
    // a square does not rotate so no rotate implementation


    public void ChangeYto0(){
        this.allblocks = new ArrayList<>();
        for(int index = 0; index < numberOfBlocks; index++){
            Block b = new Block(SIZE, start_X + structure[index][0], structure[index][1]);
            this.allblocks.add(b);
        }
    }
}
