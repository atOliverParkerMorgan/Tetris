package proc.sketches.Blocks;

import proc.sketches.Blocks.Shapes.*;

import java.util.ArrayList;


public class Shape {
    public ArrayList<Block> allblocks;
    public int number_of_squars;

    public Shape(){
        this.allblocks = new ArrayList<>();
        this.number_of_squars = 4;

    }
    public void move_down(){
        for(Block block: this.allblocks){
            block.y+=block.size;
        }
    }


}
