package proc.sketches.Shapes;

import proc.sketches.Blocks.Block;

import java.io.Serializable;

public class Blue_line extends Shape implements Serializable {

    private boolean flat;

    private final int[][] rotate1 = new int[][]{{-SIZE,SIZE},{0,0},{SIZE,-SIZE},{SIZE*2,-2*SIZE}};
    private final int[][] rotate2 = new int[][]{{SIZE,-SIZE},{0,0},{-SIZE,SIZE},{-SIZE*2,2*SIZE}};

    public Blue_line(){
        super(0);

        this.flat = true;
        int number_of_blocks = 4;

        int start_X = max_X/2-SIZE;
        int start_Y = 0;
        final int[][] structure = new int[][]{{-SIZE,0},{0,0},{SIZE,0},{SIZE*2,0}};

        for(int index = 0; index < number_of_blocks; index++){
            Block b = new Block(SIZE, start_X + structure[index][0], start_Y + structure[index][1]);
            this.allblocks.add(b);
        }

    }



    public void rotate_All(){
        if(this.flat){
            int[][] all = new int[][]{rotate2[0],rotate2[1],rotate2[2],rotate2[3]};
            this.rotate(all, 2*SIZE);
            this.flat = false;
        }else{
            int[][] all = new int[][]{rotate1[0],rotate1[1],rotate1[2],rotate1[3]};
            this.rotate(all, 2*SIZE);
            this.flat = true;
        }
    }


}
