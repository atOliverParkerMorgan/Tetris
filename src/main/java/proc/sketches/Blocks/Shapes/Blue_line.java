package proc.sketches.Blocks.Shapes;

import proc.sketches.Blocks.Block;
import proc.sketches.Blocks.Shape;

import java.util.ArrayList;
import java.util.List;

public class Blue_line extends Shape {

    public final int[] colour = new int[]{0,255,0};
    public int x;
    public int y;


    public boolean freeze;
    public Blue_line(){
        super();
        this.freeze = false;
        int[] colour = new int[]{0, 0, 255};


        double structure_x = 0.0;
        int size = 30;

        for(int i = 0; i < this.number_of_squars; i++){
            structure_x+=size;
            Block b = new Block(size, colour, structure_x, 0.0);
            this.allblocks.add(b);

        }

    }


}
