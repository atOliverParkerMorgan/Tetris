package proc.sketches.Blocks;

public class Block {
    public double x;
    public double y;

    public int size;

    public int[] colour;


    public Block(int size, int[] colour, int x, int y){
        this.x = x;
        this.y = y;

        this.size = size;
        this.colour = colour;
    }

}
