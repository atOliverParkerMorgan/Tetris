package proc.sketches.Shapes;

import proc.sketches.Blocks.Block;

import java.io.Serializable;

public class Blue_line extends Shape implements Serializable {

    public boolean freeze;
    private boolean flat;

    public Blue_line(int type){
        super(type);

        this.flat = true;
        int number_of_blocks = 4;


        int[] colour = new int[]{0, 0, 255};


        double structure_x = 0.0;


        for(int i = 0; i < number_of_blocks; i++){
            structure_x+=SIZE;
            Block b = new Block(SIZE, colour, structure_x, 0.0);
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
        }

    }

    private void rotate_flat(){
        // array with x and y
        double[] axis = new double[2];

        axis[0] = (int)(this.allblocks.get(0).x - this.allblocks.size()/2*SIZE);
        axis[1] = this.allblocks.get(2).y;

        int size = 0;
        boolean noMove = false;

        for(Block _: this.allblocks) {

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
                if (b.x <= SIZE) {
                    int s = 0;
                    for (Block b1 : this.allblocks) {
                        b1.x = s;
                        b1.y = axis[1];

                        s += SIZE;
                    }
                    break;
                } else if (b.x + SIZE >= Shape.max_X) {
                    int s = Shape.max_X - SIZE;
                    for (Block b1 : this.allblocks) {
                        b1.x = s;
                        b1.y = axis[1];

                        s -= SIZE;
                    }
                    break;
                }

                b.x = axis[0] + size;
                b.y = axis[1];

                size += SIZE;
            }
        }

    }
    public void rotate(){
        if(this.flat){
            this.rotate_notflat();
            this.flat = false;
        }else{
            this.rotate_flat();
            this.flat = true;
        }
    }


}
