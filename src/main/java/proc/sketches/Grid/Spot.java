package proc.sketches.Grid;

import proc.sketches.Blocks.Block;
import proc.sketches.Shapes.Shape;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Spot {
    public int x;
    public int y;
    public boolean occupied;

    public Spot(int x, int y){
        this.x = x;
        this.y = y;
        this.occupied = false;


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
                    for(Shape blocks: Shape.all_Shapes){
                        for(Iterator<Block> iterator =  blocks.allblocks.iterator(); iterator.hasNext(); ){
                            Block block = iterator.next();
                            if(block.x==x && block.y==y*Shape.SIZE){
                              iterator.remove();
                            }
                        }
                    }
                }
            }
        }

    }





}
