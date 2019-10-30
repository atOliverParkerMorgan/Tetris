package proc.sketches.Shapes;

import proc.sketches.Blocks.Block;

import java.io.Serializable;

public class DarkBlue_L extends Shape implements Serializable {
    public byte flat;
    public final int number_of_blocks;

    public DarkBlue_L() {
        super(1);

        this.flat = 1;
        number_of_blocks = 4;

        int start_X = max_X/2-2*SIZE;
        int start_Y = 0;
        final int[][] structure = new int[][]{{SIZE,SIZE},{SIZE,0},{2*SIZE,0},{3*SIZE,0}};

        for(int index = 0; index < number_of_blocks; index++){
            Block b = new Block(SIZE, start_X + structure[index][0], start_Y + structure[index][1]);
            this.allblocks.add(b);
        }
    }
    private void rotate_1(){
        double[] axis = new double[2];

        axis[0] = this.allblocks.get(2).x;
        axis[1] = this.allblocks.get(2).y;

        boolean noMove = false;


        int size = 0;
        for(int i = 0; i<4;i++) {

            if (overlap(axis[0], axis[1] + size)) {
                noMove = true;
                break;
            }

            size+=SIZE;
        }

        if(!noMove) {
            size = 0;
            for (Block b : this.allblocks) {
                if(size!=0) {
                    b.x = axis[0];
                    b.y = axis[1] + size;
                }

                size += SIZE;
            }
            this.flat = 2;
        }

    }

    private void rotate_2(){
        // array with x and y
        double[] axis = new double[2];

        axis[0] = this.allblocks.get(1).x -SIZE;
        axis[1] = this.allblocks.get(1).y;

        int size = 0;
        boolean noMove = false;

        for(int i = 0; i<4;i++) {

            if (overlap(axis[0] + size, axis[1])) {
                noMove = true;

                break;
            }

            size+=SIZE;
        }

        size = 0;
        if(!noMove) {
            boolean one = true;
            for (Block b : this.allblocks) {
                //bound check
                if(bound_check(axis,this.allblocks,b)){
                    break;
                }

                if(one) {
                    this.allblocks.get(0).x = axis[0]+2*SIZE;
                    this.allblocks.get(0).y = axis[1]-SIZE;
                    one = false;
                } else {
                    b.x = axis[0] + size;
                    b.y = axis[1];
                    size += SIZE;

                }



            }
            this.flat = 3;
        }

    }
    private void rotate_3(){
        double[] axis = new double[2];

        axis[0] = this.allblocks.get(2).x;
        axis[1] = this.allblocks.get(2).y;

        boolean noMove = false;


        int size = 0;
        for(int i = 0; i<4;i++) {

            if (overlap(axis[0], axis[1] + size)) {
                noMove = true;
                break;
            }

            size+=SIZE;
        }

        if(!noMove) {
            size = 0;
            for (Block b : this.allblocks) {
                if(size!=0) {
                    b.x = axis[0];
                    b.y = axis[1] + size;
                }

                size += SIZE;
            }
            this.flat = 1;
        }

    }




    public void rotate(){
        if(this.flat==1){
            this.rotate_1();
        }else if(this.flat==2){
            this.rotate_2();
        }else if(this.flat==3){
            this.rotate_3();
        }
    }
}
