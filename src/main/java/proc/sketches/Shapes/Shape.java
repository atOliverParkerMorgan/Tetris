package proc.sketches.Shapes;

import proc.sketches.Blocks.Block;

import java.util.ArrayList;
import java.util.List;

public class Shape{

    ArrayList<Block> allblocks;

    static final int SIZE = 30;
    static final int max_X = 300;
    private static final int max_Y = 600;
    private static final int num_X = max_X/SIZE;
    private static final int num_Y = max_Y/SIZE;

    private static List<Shape> all_Shapes = new ArrayList<>();

    private int type;

    public final byte states;

    public static final int numberOfBlocks = 4;

    public Shape(int type, byte states){
        this.allblocks = new ArrayList<>();
        this.type = type;
        this.states = states;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    private boolean overlap(double x, double y){
        for(int index = 0; index<Shape.all_Shapes.size()-1;index++){
            for(Block block: Shape.all_Shapes.get(index).allblocks){
                if(x == block.x && y == block.y){
                    return true;
                }
            }
        }
        return false;

    }

    public void move_up(){
        for(Block block: this.allblocks){
            block.y-=block.size;
        }
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

    private boolean bound_check(int[][] all, int addToBoundRight){
        // checks if shape is in bound after rotation if not it fixes the shape

        int index = 0;
        for(Block b: this.allblocks) {
                int[] axis = all[index];
                //bound check
                if (b.x + axis[0] < 0) {
                    index = 0;
                    for(Block b1: this.allblocks) {
                        axis = all[index];
                        b1.x += axis[0]+ Shape.SIZE;
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
    private boolean rotate(int[][] all, int addToBoundRight){
        // rotates the shape if other shapes interferer in the rotation it fixes the rotation according to
        // the other shapes
        if (bound_check(all, addToBoundRight)) {
            return true;
        }

        for(Block b: this.allblocks) {
            for (int[] axis : all) {
                if (overlap(axis[0]+b.x, axis[1]+b.y)) {
                    if(!(overlap(axis[0] + b.x + Shape.SIZE, axis[1] + b.y) && b.x + SIZE <= max_X)){
                       break;
                    }else if(!overlap(axis[0]+b.x-addToBoundRight, axis[1]+b.y) && b.x - addToBoundRight >= 0){
                        break;
                    }else{
                        return false;
                    }

                }
            }
        }

        byte index = 0;
        for (Block b : this.allblocks) {
            int[] axis = all[index];
            //bound check

            b.x += axis[0];
            b.y += axis[1];

            index++;


        }
        return true;


    }



    public ArrayList<Block> getAllblocks() {
        return allblocks;
    }

    boolean rotateTwoStates(boolean flat, int[][] rotate1, int[][] rotate2){
        if(flat){
            int[][] all = new int[][]{rotate2[0],rotate2[1],rotate2[2],rotate2[3]};
            if(this.rotate(all, 2*SIZE)) {
                return false;
            }
        }else{
            int[][] all = new int[][]{rotate1[0],rotate1[1],rotate1[2],rotate1[3]};
            if(this.rotate(all, 2*SIZE)) {
                return true;
            }
        }
        return flat;

    }
    byte rotateFourStates(byte flat, int[][] rotate1, int[][] rotate2, int[][] rotate3, int[][] rotate4){
        if(flat==1){
            int[][] all = new int[][]{rotate1[0],rotate1[1],rotate1[2],rotate1[3]};
            if(this.rotate(all, SIZE)) {
                return 2;
            }
        }else if(flat==2){
            int[][] all = new int[][]{rotate2[0],rotate2[1],rotate2[2],rotate2[3]};
            if(this.rotate(all, SIZE)) {
                return 3;
            }
        }else if(flat==3){
            int[][] all = new int[][]{rotate3[0],rotate3[1],rotate3[2],rotate3[3]};
            if(this.rotate(all, SIZE)) {
                return 4;
            }
        }else if(flat == 4){
            int[][] all = new int[][]{rotate4[0],rotate4[1],rotate4[2],rotate4[3]};
            if(this.rotate(all, SIZE)) {
                return 1;
            }
        }
        return flat;
    }

    public static int getSIZE() {
        return SIZE;
    }

    public static int getMax_X() {
        return max_X;
    }

    public static int getMax_Y() {
        return max_Y;
    }

    public static int getNum_X() {
        return num_X;
    }

    public static int getNum_Y() {
        return num_Y;
    }

    public static List<Shape> getAll_Shapes() {
        return all_Shapes;
    }

    public int getType() {
        return type;
    }

    public byte getStates() {
        return states;
    }
}








