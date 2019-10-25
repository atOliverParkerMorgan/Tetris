package proc.sketches.Blocks;

import proc.sketches.Blocks.Shapes.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.SimpleTimeZone;


public class Shape {
    public ArrayList<Block> allblocks;
    public int number_of_squars;
    public static final int SIZE = 30;
    public boolean freeze;
    public boolean flat;
    public static final int max_X = 300;
    public static final int max_Y = 600;

    public Shape(){
        this.allblocks = new ArrayList<>();
        this.number_of_squars = 4;
        this.freeze = false;
        this.flat = true;

    }
    public void move_down(){
        for(Block block: this.allblocks){
            block.y+=block.size;
        }
    }
    public void move_left(){
        boolean move = true;

        for(Block b: this.allblocks){
            if (b.x - SIZE< 0) {
                move = false;
                break;
            }
        }
        if (move){
            for (Block block : this.allblocks) {
                block.x -= block.size;
            }
        }
    }

    public void move_right(){
        boolean move = true;

        for(Block b: this.allblocks){
            if (b.x+SIZE*2 > max_X) {
                move = false;
                break;
            }
        }if(move){

            for (Block block : this.allblocks) {
                block.x += block.size;
            }
        }
    }
    private void rotate_notflat(){
        double[] axis = new double[2];

        double allX = 0 ;
        for(Block b: this.allblocks){
            allX+=b.x;
        }



        axis[0] = allX/this.allblocks.size();
        axis[1] = (this.allblocks.get(0).y - this.allblocks.size()/2.0*SIZE);


        int size = 0;
        for(Block b: this.allblocks){
            b.x = axis[0];
            b.y = axis[1]+size;

            size+=SIZE;
        }

    } private void rotate_flat(){
        double[] axis = new double[2];
        double allY = 0 ;
        for(Block b: this.allblocks){
            allY+=b.y;
        }

        axis[0] = (this.allblocks.get(0).x - this.allblocks.size()/2.0*SIZE);
        axis[1] = allY/this.allblocks.size();


        int size = 0;
        for(Block b: this.allblocks){
            b.x = axis[0]+size;
            b.y = axis[1];

            size+=SIZE;
        }

    }public void rotate(){
        if(this.flat){
            this.rotate_notflat();
            this.flat = false;
        }else{
            this.rotate_flat();
            this.flat = true;
        }
    }




}
