package proc.sketches.Shapes;

import proc.sketches.Blocks.Block;
import java.util.ArrayList;
import java.util.List;

public class Shape {
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
    //public Shape copy() {
    //    try {
    //        ByteArrayOutputStream baos = new ByteArrayOutputStream();
    //        ObjectOutputStream oos = new ObjectOutputStream(baos);
    //        oos.writeObject(this);
    //        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    //        ObjectInputStream ois = new ObjectInputStream(bais);
    //        return (Shape) ois.readObject();
    //    }
    //    catch (Exception e) {
    //        e.printStackTrace();
    //        return null;
    //    }
    //}

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







}
