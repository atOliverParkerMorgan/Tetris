package proc.sketches.Grid;

import proc.sketches.Blocks.Block;
import proc.sketches.Shapes.Shape;

import java.util.Iterator;
import java.util.Objects;

public class Spot {
    private boolean occupied;

    private static double score = 0;
    private static double level = 1.0;
    private static int startTime = 1000; // start Time one second can get as low as 0,3 seconds
    private final static int getFaster = 100; // rate of speeding up

    public int x;
    public int y;

    private static Spot[][] Grid = new Spot[Shape.getNum_Y()][Shape.getNum_X()];

    // init gird with new Spots
    static {
        int index_x;
        int index_y = 0;
        for (int y = 0; y < Shape.getMax_Y(); y += Shape.getSIZE()) {
            index_x = 0;

            for (int x = 0; x < Shape.getMax_X(); x += Shape.getSIZE()) {
                Grid[index_y][index_x] = new Spot(x, y);
                index_x++;
            }
            index_y++;
        }
    }

    private Spot(int x, int y) {
        this.occupied = false;
        this.x = x;
        this.y = y;
    }

    private static Spot getSpot(int x, int y, Spot[][] Grid) {
        // look throw the grid => if Spot is found then return else return null

        for (Spot[] spots : Grid) {
            for (Spot spot : spots) {
                if (spot.x == x && spot.y == y) {
                    return spot;

                }
            }
        }
        //System.out.println("Error X=> "+x);
        //System.out.println("Error Y=> "+y);
        return null;
    }

    public static void resetGrid() {
        // change all Spots to unoccupied the update all spots depending on the placement of all of the blocks

        for (int y = 0; y < Shape.getNum_Y(); y++) {
            for (int x = 0; x < Shape.getNum_X(); x++) {
                Grid[y][x].occupied = false;
            }
        }
        for (Shape shape : Shape.getAll_Shapes()) {
            for (Block block : shape.getAllblocks()) {
                int x = (int) block.x;
                int y = (int) block.y;

                if (Spot.getSpot(x, y, Grid) != null) {
                    Objects.requireNonNull(Spot.getSpot(x, y, Grid)).occupied = true;

                }
            }
        }

    }
    public static Spot[][] addPieceToGrid(Shape addShape, Spot[][] Grid){
        resetGrid();

        for (Block block : addShape.getAllblocks()) {
            int x = (int) block.x;
            int y = (int) block.y;



            if (Spot.getSpot(x, y, Grid) != null) {
                Objects.requireNonNull(Spot.getSpot(x, y, Grid)).occupied = true;
                System.out.println("X: "+ x +" Y: "+y);

            }else {
                System.out.println("ERROR");
            }
        }
        return Grid;

    }

    public static void deleteBlocks() {
        // delete a row of block if you find a full row of block
        resetGrid();

        int num;
        int rowsFound = 0;

        for (int y = 0; y < Shape.getNum_Y(); y++) {
            num = 0;

            for (int x = 0; x < Shape.getNum_X(); x++) {
                if (Grid[y][x].occupied) {
                    num++;
                }

            }
            if (num == Shape.getNum_X()) {
                rowsFound++;
                for (int x = 0; x < Shape.getMax_X(); x += Shape.getSIZE()) {
                    for (Iterator<Shape> shape_iterator = Shape.getAll_Shapes().iterator(); shape_iterator.hasNext(); ) {
                        Shape blocks = shape_iterator.next();
                        for (Iterator<Block> block_iterable = blocks.getAllblocks().iterator(); block_iterable.hasNext(); ) {
                            Block block = block_iterable.next();
                            if (block.x == x && block.y == y * Shape.getSIZE()) {



                                block_iterable.remove();
                            }
                        }
                        if (blocks.getAllblocks().size() == 0) {

                            shape_iterator.remove();
                        }
                    }
                }
                level+=0.1;
                // make default time faster
                if(rowsFound==1) {
                    Spot.score += 40;
                }else if(rowsFound==2){
                    Spot.score += 60;
                }else if(rowsFound==3){
                    Spot.score += 200;
                }else if(rowsFound==4){
                    Spot.score += 900;
                }

                if(level==2.0||level==3.0||level==4.0||level==5.0||level==6.0||level==7.0||level==8.0||level==9.0) {
                    Spot.startTime -= Spot.getFaster;
                }

                // move all blocks down
                for (int index = 0; index < Shape.getAll_Shapes().size(); index++) {
                    for (Block block : Shape.getAll_Shapes().get(index).getAllblocks()) {
                        if(block.y<y*Shape.getSIZE()) {
                            block.y += Shape.getSIZE();
                        }

                    }

                }

            }
        }

    }

    public boolean isOccupied() {
        return occupied;
    }

    public static double getScore() {
        return score;
    }

    public static double getLevel() {
        return level;
    }

    public static int getStartTime() {
        return startTime;
    }

    public static int getGetFaster() {
        return getFaster;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Spot[][] getGrid() {
        return Grid;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}



