package proc.sketches.Blocks;

import proc.sketches.Blocks.Shapes.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.SimpleTimeZone;


public class Shape {
    public ArrayList<Block> allblocks;
    public int number_of_squars;
    public static final int SIZE = 30;
    public boolean flat;
    public static final int max_X = 300;
    public static final int max_Y = 600;

    public Shape(){
        this.allblocks = new ArrayList<>();
        this.number_of_squars = 4;
        this.flat = true;
    }
    public Shape copy() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Shape) ois.readObject();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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

        axis[0] = this.allblocks.get(2).x;
        axis[1] = (int) (this.allblocks.get(0).y - this.allblocks.size()/2*SIZE);


        int size = 0;
        for(Block b: this.allblocks){

            b.x = axis[0];
            b.y = axis[1]+size;

            size+=SIZE;
        }

    } private void rotate_flat(){
        // array with x and y
        double[] axis = new double[2];

        axis[0] = (int)(this.allblocks.get(0).x - this.allblocks.size()/2*SIZE);
        axis[1] = this.allblocks.get(2).y;


        int size = 0;
        for(Block b: this.allblocks){
            //bound check
            if(b.x<=SIZE){
                int s = 0;
                for(Block b1: this.allblocks) {
                    b1.x = s;
                    b1.y = axis[1];

                    s+=SIZE;
                }
                break;
            }else if(b.x+SIZE>=Shape.max_X){
                int s = Shape.max_X-SIZE;
                for(Block b1: this.allblocks) {
                    b1.x = s;
                    b1.y = axis[1];

                    s-=SIZE;
                }
                break;
            }

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
