package proc.sketches.Grid;

import proc.sketches.Blocks.Block;
import proc.sketches.Shapes.Shape;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Spot {
    public boolean occupied;

    public static int score = 0;
    public static int startTime = 1000;
    private final static int getFaster = 10;

    private int x;
    private int y;

    public Spot(int x,int y){
        this.occupied = false;
        this.x = x;
        this.y = y;
    }

    public static Spot getSpot(Spot[][] Grid, int x, int y){
        for(Spot[] spots: Grid){
            for(Spot spot: spots){
                if(spot.x==x && spot.y==y){
                    return spot;

                }
            }
        }
        return null;
    }

    public static void deleteBlocks(Spot[][] Grid){
        int num;
        for(int y=0; y<Shape.num_Y; y++){
            num = 0;
            for(int x=0; x<Shape.num_X; x++) {
                if(Grid[y][x].occupied){
                    num++;
                }

            }
            if(num==Shape.num_X){
                for(int x=0; x<Shape.max_X; x+=Shape.SIZE) {
                    for(Iterator<Shape> shape_iterator =  Shape.all_Shapes.iterator(); shape_iterator.hasNext(); ){
                        Shape blocks = shape_iterator.next();
                        for(Iterator<Block> block_iterable =  blocks.allblocks.iterator();block_iterable.hasNext(); ){
                            Block block = block_iterable.next();
                            if(block.x==x && block.y==y*Shape.SIZE){
                                block_iterable.remove();
                            }
                        }
                        if(blocks.allblocks.size()==0){
                            shape_iterator.remove();
                        }
                    }
                }
                Spot.score+=Shape.num_X;
                Spot.startTime-=Spot.getFaster;
                for (Shape shape: Shape.all_Shapes){
                    for(Block block: shape.allblocks){
                        if(block.y>y){
                            block.y+=Shape.SIZE;
                        }
                    }
                }

            }
        }

    }





}
