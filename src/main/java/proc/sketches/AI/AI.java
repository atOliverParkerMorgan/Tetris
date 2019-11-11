package proc.sketches.AI;

import proc.sketches.Grid.Spot;
import proc.sketches.Shapes.Shape;

public class AI {
    private Spot[][] Grid;

    private int numberOfHoles;
    private int lineClearing;
    private int bumps;


    AI(Spot[][] Grid){

        this.Grid = Grid;
        this.lineClearing = getNumberOfHoles();
    }
    AI(){
        this.Grid = null;
        this.numberOfHoles = 0;
        this.lineClearing = 0;
        this.bumps = 0;
    }

    private int getNumberOfHoles(){
        int numberOfHoles = 0;

        int index_x;
        int index_y = Shape.num_Y;
        for (int y = 0; y < Shape.max_Y; y += Shape.SIZE) {
            index_x = Shape.num_X;

            for (int x = 0; x < Shape.max_X; x += Shape.SIZE) {
                if(!Grid[index_x][index_y].occupied){
                    continue;

                }
                index_x--;
            }
            index_y--;
        }
        return numberOfHoles;

    }



}
