package proc.sketches.Blocks;

import java.io.Serializable;

public class Block implements Serializable {
    public double x;
    public double y;

    public int size;

    public int[] colour;

    public boolean stocked;


    public Block(int size,double x, double y){
        this.x = x;
        this.y = y;
        this.stocked = false;
        this.size = size;
    }

}
