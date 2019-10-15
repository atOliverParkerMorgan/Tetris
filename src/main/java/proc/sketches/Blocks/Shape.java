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
    public void move_left(){
        for(Block block: this.allblocks){
            block.x-=block.size;
        }
    }

    public void move_right(){
        for(Block block: this.allblocks){
            block.x+=block.size;
        }
    }
    public void rotate_ClockWise(){
        float avx = 0;
        float avy = 0;

        int div = 0;
        for(Block block: this.allblocks){
            avx += (float) block.x;
            avy +=block.y;
        }
        avx = avx/div;
        avy = avy/div;

        for(Block block: this.allblocks){
            div--;
            block.x = avx;
            block.y = avy-block.size*div;

        }

    }



}
