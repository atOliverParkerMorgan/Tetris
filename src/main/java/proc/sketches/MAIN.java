package proc.sketches;

import proc.sketches.Blocks.Block;
import proc.sketches.Blocks.Shape;
import proc.sketches.Blocks.Shapes.Blue_line;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MAIN extends PApplet {

    private List<Shape> all_Shapes = new ArrayList<Shape>(){};

    private Shape moving_shape = new Blue_line();

    private PImage darkBlue_block;
    private PImage lightBlue_block;
    private PImage Green_block;
    private PImage Purple_block;
    private PImage Yellow_block;
    private PImage Orange_block;
    private PImage Red_block;

    public void settings(){


        // the place where your images are saved

        // school dir
        //C:\Users\2019-e-morgan\IdeaProjects\Tetris\src\main\java\proc\sketches\sprites\
        String base_dir = "C:\\Users\\olive\\IdeaProjects\\tetris\\src\\main\\java\\proc\\sketches\\sprites\\";

        //all blocks
        darkBlue_block = loadImage(base_dir+"darkBlue.png");
        lightBlue_block = loadImage(base_dir+"lightBlue.png");
        Green_block = loadImage(base_dir + "Green.png");
        Purple_block = loadImage(base_dir + "Purple.png");
        Yellow_block = loadImage(base_dir + "Yellow.png");
        Red_block = loadImage(base_dir+"Red.png");
        Orange_block = loadImage(base_dir+"Orange.png");


        // Canvas
        size(Shape.max_X, Shape.max_Y, JAVA2D);
        moving_shape = new Blue_line();
        all_Shapes.add(moving_shape);
        // And From your main() method or any other method
        Timer timer = new Timer();
        timer.schedule(new Move(), 0, 1000);




    }public void draw(){
        //white background
        background(255,255,255);

        // draw lines x
        float cord_line1_x = 0;
        float cord_line1_y = 0;
        float cord_line2_x = 0;
        float cord_line2_y = Shape.max_Y;

        for(int i=0;i<Shape.max_X/Shape.SIZE;i++){


            line(cord_line1_x,cord_line1_y,cord_line2_x,cord_line2_y);
            fill(0,0,0);
            cord_line1_x += Shape.SIZE;
            cord_line2_x += Shape.SIZE;

        }

        // draw lines y
        cord_line1_x = 0;
        cord_line1_y = 0;
        cord_line2_x = Shape.max_X;
        cord_line2_y = 0;

        for(int i=0;i<Shape.max_Y/Shape.SIZE;i++){


            line(cord_line1_x,cord_line1_y,cord_line2_x,cord_line2_y);
            fill(0,0,0);
            cord_line1_y += Shape.SIZE;
            cord_line2_y += Shape.SIZE;
        }


        //draw
        boolean new_shape = false;
        for(Shape shape: all_Shapes) {
            for (Block block : shape.allblocks) {
                int x = (int) block.x;
                int y = (int) block.y;

                image(darkBlue_block, x, y);

            }
        }

        //collision logic
        for(Block block: moving_shape.allblocks){
            // at the bottom of the screen
            if(block.y+Shape.SIZE==Shape.max_Y){
                moving_shape = new Blue_line();
                all_Shapes.add(moving_shape);
                break;
            }
            //checking for all collisions with all blocks
            else{
                for(int index = 0; index<all_Shapes.size()-1;index++){
                    for(Block b: all_Shapes.get(index).allblocks){
                        if(b.x==block.x && b.y==block.y+Shape.SIZE){
                            moving_shape = new Blue_line();
                            all_Shapes.add(moving_shape);
                            break;
                        }
                    }
                }
            }
        }





    }
    public void keyPressed() {
        if (keyCode == LEFT) {
            moving_shape.move_left();
        }else if (keyCode == RIGHT) {
            moving_shape.move_right();
        }else if(keyCode == UP){
            moving_shape.rotate();
        }else if(keyCode == DOWN){
            moving_shape.move_down();
        }
    }


    class Move extends TimerTask {
        public void run() {
            moving_shape.move_down();
        }
    }






    public static void main(String... args) {
        PApplet.main("proc.sketches.MAIN");
    }

}
