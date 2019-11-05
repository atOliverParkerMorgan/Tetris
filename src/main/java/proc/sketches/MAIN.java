package proc.sketches;

import proc.sketches.Blocks.Block;
import proc.sketches.Grid.Spot;
import proc.sketches.Shapes.*;
import processing.core.PApplet;
import processing.core.PImage;

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

    private PImage LOGO;



    private Shape[] next_Shapes;


    public void settings(){

        next_Shapes = new Shape[3];
        for(int i=0;i<3;i++){
            next_Shapes[i] = pickRandomShape();
        }

        // the place where your images are saved

        // school dir
        //C:\Users\2019-e-morgan\IdeaProjects\Tetris\src\main\java\proc\sketches\sprites\
        String base_dir = "C:\\Users\\2019-e-morgan\\IdeaProjects\\Tetris\\src\\main\\java\\proc\\sketches\\sprites\\";

        //all blocks
        darkBlue_block = loadImage(base_dir+"darkBlue.png");
        lightBlue_block = loadImage(base_dir+"lightBlue.png");
        Green_block = loadImage(base_dir + "Green.png");
        Purple_block = loadImage(base_dir + "Purple.png");
        Yellow_block = loadImage(base_dir + "Yellow.png");
        Red_block = loadImage(base_dir+"Red.png");
        Orange_block = loadImage(base_dir+"Orange.png");
        LOGO = loadImage(base_dir+"Logo.png");



        // Canvas
        int UI_size = 250;
        size(Shape.max_X+UI_size, Shape.max_Y, JAVA2D);
        moving_shape = pickRandomShape();
        Shape.all_Shapes.add(moving_shape);
        // And From your main() method or any other method
        Timer timer = new Timer();
        timer.schedule(new Move(), 0, Spot.startTime);

    }public void draw(){
        Spot.resetGrid();
        //white background
        background(255,255,255);

        //UI
        image(LOGO,Shape.max_X+15,15);
        text("Score: "+Spot.score,Shape.max_X+15,140);
        text("Next: ",Shape.max_X+15,170);
        textSize(20);
        int add_x = 300;
        int add_y = 200;

        for(Shape shape: next_Shapes){
            for(Block block: shape.allblocks){
                int x = (int) block.x + add_x;
                int y = (int) block.y + add_y;

                if(shape.type==0) {
                    image(lightBlue_block, x-Shape.SIZE, y);
                }else if(shape.type==1){
                    image(darkBlue_block, x, y);
                }else if(shape.type==2){
                    image(Green_block, x-Shape.SIZE, y);
                }else if(shape.type==3){
                    image(Orange_block, x, y);
                }else if(shape.type==4){
                    image(Purple_block, x, y);
                }else if(shape.type==5){
                    image(Red_block,x-Shape.SIZE,y);
                }else if(shape.type==6){
                    image(Yellow_block, x-Shape.SIZE, y);
                }
            }
            add_y+=130;

        }

        // draw lines x
        float cord_line1_x = 0;
        float cord_line1_y = 0;
        float cord_line2_x = 0;
        float cord_line2_y = Shape.max_Y;

        for(int i=0;i<(Shape.max_X/Shape.SIZE)+1;i++){


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

        //show everything else
        for(Shape shape: Shape.all_Shapes) {
            for (Block block : shape.allblocks) {
                int x = (int) block.x;
                int y = (int) block.y;

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
        checkCollision();
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
            checkCollision();
        }
    }


    class Move extends TimerTask {
        public void run() {
            moving_shape.move_down();
        }

    }
    private Shape pickRandomShape(){

        int rand = (int)(Math.random()*7);

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
    private void Shift(){
        next_Shapes[0] = next_Shapes[1];
        next_Shapes[1] = next_Shapes[2];
        next_Shapes[2] = pickRandomShape();

    }

    private void checkCollision(){
        Spot.deleteBlocks();

        //collision logic
        main_loop:
        for(Block block: moving_shape.allblocks){
            // at the bottom of the screen
            if(block.y+Shape.SIZE==Shape.max_Y){
                moving_shape = next_Shapes[0];
                Shift();

                Shape.all_Shapes.add(moving_shape);
                break;
            }
            //checking for all collisions with all blocks
            else{
                for(int index = 0; index<Shape.all_Shapes.size()-1;index++){
                    for(Block b: Shape.all_Shapes.get(index).allblocks){
                        if(b.x==block.x && b.y==block.y+Shape.SIZE){
                            moving_shape = next_Shapes[0];
                            Shift();

                            Shape.all_Shapes.add(moving_shape);


                            break main_loop;
                        }
                    }
                }
            }
        }
    }





    public static void main(String... args) {
        PApplet.main("proc.sketches.MAIN");
    }

}
