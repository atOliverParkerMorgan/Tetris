package proc.sketches.Shapes;

import proc.sketches.Blocks.Block;

public class Purple_T extends Shape{
    private byte flat;

    // rotate positions
    private final int[][] rotate1 = new int[][]{{-SIZE,-SIZE},{SIZE,-SIZE},{0,0},{-SIZE,SIZE}};

    private final int[][] rotate2 = new int[][]{{SIZE,-SIZE},{SIZE,SIZE},{0,0},{-SIZE,-SIZE}};

    private final int[][] rotate3 = new int[][]{{SIZE,SIZE},{-SIZE,SIZE},{0,0},{SIZE,-SIZE}};

    private final int[][] rotate4 = new int[][]{{-SIZE,SIZE},{-SIZE,-SIZE},{0,0},{SIZE,SIZE}};

    public Purple_T() {
        super(4);

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
        if(this.flat==1){
            int[][] all = new int[][]{rotate1[0],rotate1[1],rotate1[2],rotate1[3]};
            this.rotate(all, SIZE);
            this.flat = 2;
        }else if(this.flat==2){
            int[][] all = new int[][]{rotate2[0],rotate2[1],rotate2[2],rotate2[3]};
            this.rotate(all, SIZE);
            this.flat = 3;
        }else if(this.flat==3){
            int[][] all = new int[][]{rotate3[0],rotate3[1],rotate3[2],rotate3[3]};
            this.rotate(all, SIZE);
            this.flat = 4;
        }else if(this.flat == 4){
            int[][] all = new int[][]{rotate4[0],rotate4[1],rotate4[2],rotate4[3]};
            this.rotate(all, SIZE);
            this.flat = 1;

        }
    }
}
