package proc.sketches.Grid;

import proc.sketches.Blocks.Block;
import proc.sketches.Shapes.Shape;
import java.util.Iterator;
import java.util.Objects;

public class Spot {
    public boolean occupied;


    public static double score = 0;
    public static double level = 1.0;
    public static int startTime = 1000; // start Time one second can get as low as 0,3 seconds
    private final static int getFaster = 100; // rate of speeding up

    private int x;
    private int y;

    private static Spot[][] Grid = new Spot[Shape.num_Y][Shape.num_X];

    // init gird with new Spots
    static {
        int index_x;
        int index_y = 0;
        for (int y = 0; y < Shape.max_Y; y += Shape.SIZE) {
            index_x = 0;

            for (int x = 0; x < Shape.max_X; x += Shape.SIZE) {
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

    private static Spot getSpot(int x, int y) {
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

        for (int y = 0; y < Shape.num_Y; y++) {
            for (int x = 0; x < Shape.num_X; x++) {
                Grid[y][x].occupied = false;
            }
        }
        for (Shape shape : Shape.all_Shapes) {
            for (Block block : shape.allblocks) {
                int x = (int) block.x;
                int y = (int) block.y;

                if (Spot.getSpot(x, y) != null) {
                    Objects.requireNonNull(Spot.getSpot(x, y)).occupied = true;

                }
            }
        }

    }
    public static void deleteBlocks() {
        // delete a row of block if you find a full row of block
        resetGrid();

        int num;
        int rowsFound = 0;

        for (int y = 0; y < Shape.num_Y; y++) {
            num = 0;

            for (int x = 0; x < Shape.num_X; x++) {
                if (Grid[y][x].occupied) {
                    num++;
                }

            }
            if (num == Shape.num_X) {
                rowsFound++;
                for (int x = 0; x < Shape.max_X; x += Shape.SIZE) {
                    for (Iterator<Shape> shape_iterator = Shape.all_Shapes.iterator(); shape_iterator.hasNext(); ) {
                        Shape blocks = shape_iterator.next();
                        for (Iterator<Block> block_iterable = blocks.allblocks.iterator(); block_iterable.hasNext(); ) {
                            Block block = block_iterable.next();
                            if (block.x == x && block.y == y * Shape.SIZE) {



                                block_iterable.remove();
                            }
                        }
                        if (blocks.allblocks.size() == 0) {

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
                for (int index = 0; index < Shape.all_Shapes.size(); index++) {
                    for (Block block : Shape.all_Shapes.get(index).allblocks) {
                        if(block.y<y*Shape.SIZE) {
                            block.y += Shape.SIZE;
                        }

                    }

                }

            }
        }

    }
}



