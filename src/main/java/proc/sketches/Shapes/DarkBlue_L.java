package proc.sketches.Shapes;

import proc.sketches.Blocks.Block;

import java.util.ArrayList;


public class DarkBlue_L extends Shape implements Cloneable{
    private byte flat;
    public final int start_X;
    // rotate positions

    private final int[][] rotate1 = new int[][]{{0,-2*SIZE},{SIZE,-SIZE},{0,0},{-SIZE,SIZE}};

    private final int[][] rotate2 = new int[][]{{2*SIZE,0},{SIZE,SIZE},{0,0},{-SIZE,-SIZE}};

    private final int[][] rotate3 = new int[][]{{0,2*SIZE},{-SIZE,SIZE},{0,0},{SIZE,-SIZE}};

    private final int[][] rotate4 = new int[][]{{-2*SIZE,0},{-SIZE,-SIZE},{0,0},{SIZE,SIZE}};

    private final int[][] structure = new int[][]{{-SIZE, SIZE}, {-SIZE, 0}, {0, 0}, {SIZE, 0}};

    public DarkBlue_L(int start_X) {
        super(1,(byte) 4);

        this.flat = 1;
        this.start_X = start_X;
        int start_Y = 0;

        // starting structure

        for(int index = 0; index < numberOfBlocks; index++){

            Block b = new Block(SIZE, start_X + structure[index][0], start_Y + structure[index][1]);
            this.allblocks.add(b);
        }
    }





    public void rotate_All(){
        if(this.flat==1){
            int[][] all = new int[][]{rotate1[0],rotate1[1],rotate1[2],rotate1[3]};
            if(this.rotate(all, SIZE)) {
                this.flat = 2;
            }
        }else if(this.flat==2){
            int[][] all = new int[][]{rotate2[0],rotate2[1],rotate2[2],rotate2[3]};
            if(this.rotate(all, SIZE)) {
                this.flat = 3;
            }
        }else if(this.flat==3){
            int[][] all = new int[][]{rotate3[0],rotate3[1],rotate3[2],rotate3[3]};
            if(this.rotate(all, SIZE)) {
                this.flat = 4;
            }
        }else if(this.flat == 4){
            int[][] all = new int[][]{rotate4[0],rotate4[1],rotate4[2],rotate4[3]};
            if(this.rotate(all, SIZE)) {
                this.flat = 1;
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
