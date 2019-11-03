package proc.sketches.Grid;

import proc.sketches.Blocks.Block;
import proc.sketches.Shapes.Shape;
import java.util.Iterator;

public class Spot {
    public boolean occupied;
    public static int score = 0;
    private int x;
    private int y;

    public Spot(int x,int y){
        this.occupied = false;
        this.x = x;
        this.y = y;
    }

    public static Spot getSpot(Spot[][] Grid,int x, int y){
        for(Spot[] spots: Grid){
            for(Spot spot: spots){
                if(spot.x==x && spot.y==x){
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
                    for(Iterator<Shape> iterator =  Shape.all_Shapes.iterator(); iterator.hasNext(); ){
                        Shape blocks = iterator.next();
                        for(Iterator<Block> iterator2 =  blocks.allblocks.iterator(); iterator.hasNext(); ){
                            Block block = iterator2.next();
                            if(block.x==x && block.y==y*Shape.SIZE){
                              iterator2.remove();
                            }
                        }
                        if(blocks.allblocks.size()==0){
                            iterator.remove();
                        }
                    }
                }
                Spot.score+=Shape.num_X;
            }
        }

    }





}
