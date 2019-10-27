package proc.sketches.Blocks;

public class Block {
    public double x;
    public double y;

    public int size;

    public int[] colour;

    public boolean stocked;


    public Block(int size, int[] colour, double x, double y){
        this.x = x;
        this.y = y;
        this.stocked = false;
        this.size = size;
        this.colour = colour;
    }

}
