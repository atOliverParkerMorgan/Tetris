package proc.sketches.Shapes;

import proc.sketches.Blocks.Block;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Shape implements Serializable {
    public ArrayList<Block> allblocks;
    public static final int SIZE = 30;
    public static final int max_X = 300;
    public static final int max_Y = 600;

    public static final int num_X = max_X/SIZE;
    public static final int num_Y = max_Y/SIZE;

    public static List<Shape> all_Shapes = new ArrayList<>();

    public int type;

    public Shape(int type){
        this.allblocks = new ArrayList<>();
        this.type = type;
    }
   // public Shape copy() {
   //     try {
   //         ByteArrayOutputStream baos = new ByteArrayOutputStream();
   //         ObjectOutputStream oos = new ObjectOutputStream(baos);
   //         oos.writeObject(this);
   //         ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
   //         ObjectInputStream ois = new ObjectInputStream(bais);
   //         return (Shape) ois.readObject();
   //     }
   //     catch (Exception e) {
   //         e.printStackTrace();
   //         return null;
   //     }
   // }
    public boolean overlap(double x, double y){
        for(int index = 0; index<Shape.all_Shapes.size()-1;index++){
            for(Block block: Shape.all_Shapes.get(index).allblocks){
                if(x == block.x && y == block.y){
                    return true;
                }
            }
        }
        return false;

    }


    public void move_down(){
        for(Block block: this.allblocks){
            block.y+=block.size;
        }
    }
    public void move_left(){
        boolean move = true;

        // checking if the object is at the edge
        for(Block b: this.allblocks){
            if (b.x - SIZE< 0) {
                move = false;
                break;
            }else{
                // checking if shape isn't overlapping with another shape
                for(int index = 0; index<Shape.all_Shapes.size()-1;index++){
                    for(Block block: Shape.all_Shapes.get(index).allblocks){
                        if(b.x-SIZE == block.x && b.y == block.y){
                            move = false;
                            break;
                        }
                    }

                }

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

        // checking if the object is at the edge
        for(Block b: this.allblocks){
            if (b.x+SIZE*2 > max_X) {
                move = false;
                break;
            }else{
                // checking if shape isn't overlapping with another shape
                for(int index = 0; index<Shape.all_Shapes.size()-1;index++){
                    for(Block block: Shape.all_Shapes.get(index).allblocks){
                        if(b.x+SIZE == block.x && b.y == block.y){
                            move = false;
                            break;
                        }
                    }

                }
            }

        }if(move){

            for (Block block : this.allblocks) {
                block.x += block.size;
            }
        }
    }

    private boolean bound_check(int[][] all,int addToBoundLeft,int addToBoundRight){

        int index = 0;
        for(Block b: this.allblocks) {
                int[] axis = all[index];
                //bound check
                if (b.x + axis[0] < 0) {
                    index = 0;
                    for(Block b1: this.allblocks) {
                        axis = all[index];
                        b1.x += axis[0]+addToBoundLeft;
                        b1.y += axis[1];

                        index++;
                    }

                    return true;
                } else if (b.x + axis[0] + SIZE > Shape.max_X) {
                    index = 0;
                    for(Block b1: this.allblocks) {
                        axis = all[index];
                        b1.x += axis[0]-addToBoundRight;
                        b1.y += axis[1];

                        index++;
                    }

                    return true;
                }
                index++;
        }
        return false;
    }
    void rotate(int[][] all,int addToBoundLeft, int addToBoundRight){



        boolean noMove = false;

        main_loop:
        for(Block b: this.allblocks) {
            for (int[] axis : all) {

                if (overlap(axis[0]+b.x, axis[1]+b.y)) {
                    noMove = true;

                    break main_loop;
                }


            }
        }
        if (bound_check(all,addToBoundLeft,addToBoundRight)) {
            noMove = true;
        }

        if(!noMove) {
            byte index = 0;
            for (Block b : this.allblocks) {
                int[] axis = all[index];
                //bound check

                b.x += axis[0];
                b.y += axis[1];

                index++;


            }
        }

    }

}








