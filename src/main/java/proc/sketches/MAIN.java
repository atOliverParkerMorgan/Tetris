package proc.sketches;

import com.sun.jna.platform.win32.WinUser;
import proc.sketches.Blocks.Block;
import proc.sketches.Grid.Spot;
import proc.sketches.Shapes.*;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MAIN extends PApplet {



    private Shape moving_shape;

    private PImage darkBlue_block;
    private PImage lightBlue_block;
    private PImage Green_block;
    private PImage Purple_block;
    private PImage Yellow_block;
    private PImage Orange_block;
    private PImage Red_block;

    private Spot[][] Grid = new Spot[Shape.num_Y][Shape.num_X];



    public void settings(){

        // the place where your images are saved
        int index_x = 0;
        int index_y = 0;

        for(int y=0; y<Shape.max_Y; y+=Shape.SIZE){
            index_x = 0;
            for(int x=0; x<Shape.max_X; x+=Shape.SIZE){
                Grid[index_y][index_x] = new Spot(x,y);
                index_x++;
            }
            index_y++;
        }

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
        moving_shape = pickRandomShape();
        Shape.all_Shapes.add(moving_shape);
        // And From your main() method or any other method
        Timer timer = new Timer();
        timer.schedule(new Move(), 0, 1000);

    }public void draw(){
        resetGrid();

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

        //show moving
        for(Block b :moving_shape.allblocks){
            int x = (int) b.x;
            int y = (int) b.y;

            if(moving_shape.type==0) {
                image(lightBlue_block, x, y);
            }else if(moving_shape.type==1){
                image(darkBlue_block, x, y);
            }
        }

        //show everything else
        for(Shape shape: Shape.all_Shapes) {
            for (Block block : shape.allblocks) {
                int x = (int) block.x;
                int y = (int) block.y;
                int fix_x = 0;
                int fix_y = 0;
                if(x == Shape.SIZE){
                    fix_x = 1;
                }if(y == 0){
                    fix_y = 1;
                }



                Grid[(y / Shape.SIZE) - fix_x][(x / Shape.SIZE) - fix_y].occupied = true;


                if(shape.type==0) {
                    image(lightBlue_block, x, y);
                }else if(shape.type==1){
                    image(darkBlue_block, x, y);
                }else if(shape.type==2){
                    image(Green_block, x, y);
                }else if(shape.type==3){
                    image(Orange_block, x, y);
                }else if(shape.type==4){
                    image(Purple_block, x, y);
                }else if(shape.type==5){
                    image(Red_block,x,y);
                }else if(shape.type==6){
                    image(Yellow_block, x, y);
                }

            }
        }

        Spot.deleteBlocks(Grid);

        //collision logic
        main_loop:
        for(Block block: moving_shape.allblocks){
            // at the bottom of the screen
            if(block.y+Shape.SIZE==Shape.max_Y){
                moving_shape = pickRandomShape();
                Shape.all_Shapes.add(moving_shape);
                break;
            }
            //checking for all collisions with all blocks
            else{
                for(int index = 0; index<Shape.all_Shapes.size()-1;index++){
                    for(Block b: Shape.all_Shapes.get(index).allblocks){
                        if(b.x==block.x && b.y==block.y+Shape.SIZE){
                            moving_shape = pickRandomShape();
                            Shape.all_Shapes.add(moving_shape);


                            break main_loop;
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
            if(moving_shape.type==0) {
                Blue_line line = (Blue_line) moving_shape;
                line.rotate_All();
            }else if(moving_shape.type==1){
                DarkBlue_L L = (DarkBlue_L) moving_shape;
                L.rotate_All();
            }else if(moving_shape.type==2){
                Green_S S = (Green_S) moving_shape;
                S.rotate_All();
            }else if(moving_shape.type==3) {
                Orange_L L = (Orange_L) moving_shape;
                L.rotate_All();
            }else if(moving_shape.type==4){
                Purple_T T = (Purple_T) moving_shape;
                T.rotate_All();
            }else if(moving_shape.type==5){
                Red_Z Z = (Red_Z) moving_shape;
                Z.rotate_All();
            }

        }else if(keyCode == DOWN) {
            moving_shape.move_down();
        }
    }


    class Move extends TimerTask {
        public void run() {
            moving_shape.move_down();
        }

    }
    private Shape pickRandomShape(){
        int rand = (int)(Math.random() * 7);
        System.out.println(rand);

        if(rand==0){
            return new Blue_line();
        }else if(rand==1){
            return new DarkBlue_L();
        }else if(rand==2){
            return new Green_S();
        }else if(rand==3){
            return new Orange_L();
        }else if(rand==4){
            return new Purple_T();
        }else if(rand==5){
            return new Red_Z();
        }else{
            return new Yellow_square();
        }

    }
    private void resetGrid(){
        for(int y=0; y<Shape.num_Y; y++){
            for(int x=0; x<Shape.num_X; x++) {
                Grid[y][x].occupied = false;
            }
        }
    }





    public static void main(String... args) {
        PApplet.main("proc.sketches.MAIN");
    }

}
