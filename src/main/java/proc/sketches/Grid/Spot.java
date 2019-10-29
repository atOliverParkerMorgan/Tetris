package proc.sketches.Grid;

import proc.sketches.Shapes.Shape;

public class Spot {
    public int x;
    public int y;
    public boolean occupied;

    public static Spot[][] Grid = new Spot[Shape.num_X][Shape.num_Y];
    public Spot(int x, int y){
        this.x = x;
        this.y = y;
        this.occupied = false;

    }
    public static Spot getSpot(int x, int y){
        return Spot.Grid[x][y];

    }


}
