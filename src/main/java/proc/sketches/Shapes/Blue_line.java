package proc.sketches.Shapes;

import proc.sketches.Blocks.Block;

import java.io.Serializable;

public class Blue_line extends Shape implements Serializable {

    public boolean freeze;
    private boolean flat;

    public Blue_line(){
        super(0);

        this.flat = true;
        int number_of_blocks = 4;

        int start_X = max_X/2-2*SIZE;
        int start_Y = 0;
        final int[][] structure = new int[][]{{0,0},{SIZE,0},{SIZE*2,0},{SIZE*3,0}};

        for(int index = 0; index < number_of_blocks; index++){
            Block b = new Block(SIZE, start_X + structure[index][0], start_Y + structure[index][1]);
            this.allblocks.add(b);
        }

    }


    private void rotate_notflat(){
        double[] axis = new double[2];

        axis[0] = this.allblocks.get(2).x;
        axis[1] = (int) (this.allblocks.get(0).y - this.allblocks.size()/2*SIZE);

        boolean noMove = false;


        int size = 0;
        for(Block b: this.allblocks) {

            if (overlap(axis[0], axis[1] + size)) {
                noMove = true;
                break;
            }

            size+=SIZE;
        }

        if(!noMove) {
            size = 0;
            for (Block b : this.allblocks) {

                b.x = axis[0];
                b.y = axis[1] + size;


                size += SIZE;
            }
            this.flat = false;
        }

    }

    private void rotate_flat(){
        // array with x and y
        double[] axis = new double[2];

        axis[0] = (int)(this.allblocks.get(0).x - this.allblocks.size()/2*SIZE);
        axis[1] = this.allblocks.get(2).y;

        int size = 0;
        boolean noMove = false;

        for(Block b: this.allblocks) {

            if (overlap(axis[0] + size, axis[1])) {
                noMove = true;

                break;
            }

            size+=SIZE;
        }

        size = 0;
        if(!noMove) {

            for (Block b : this.allblocks) {
                //bound check
                if(bound_check(axis,this.allblocks,b)){
                    break;
                }

                b.x = axis[0] + size;
                b.y = axis[1];

                size += SIZE;
            }
            this.flat = true;
        }

    }



    public void rotate(){
        if(this.flat){
            this.rotate_notflat();
        }else{
            this.rotate_flat();
        }
    }


}
