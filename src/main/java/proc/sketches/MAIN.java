package proc.sketches;

import proc.sketches.AI.AI;
import proc.sketches.Blocks.Block;
import proc.sketches.Grid.Spot;
import proc.sketches.Shapes.*;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.Timer;
import java.util.TimerTask;



public class MAIN extends PApplet {

    private boolean dead;

    private Shape moving_shape;

    private PImage darkBlue_block;
    private PImage lightBlue_block;
    private PImage Green_block;
    private PImage Purple_block;
    private PImage Yellow_block;
    private PImage Orange_block;
    private PImage Red_block;

    private AI ai;

    private PImage LOGO;



    private Shape[] next_Shapes;




    public void settings(){
         ai = new AI();
        dead = false;
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
        size(Shape.getMax_X()+UI_size, Shape.getMax_Y(), JAVA2D);
        moving_shape = pickRandomShape();
        Shape.getAll_Shapes().add(moving_shape);
        // And From your main() method or any other method
        Timer timer = new Timer();
        timer.schedule(new Move(), 0, Spot.getStartTime());

    }public void draw(){

        // reset & update the positions of blocks on the grid
        Spot.resetGrid();

        // white background
        background(255,255,255);

        // UI => Score
        image(LOGO,Shape.getMax_X()+15,15);
        text("Level: "+(int) Spot.getLevel(),Shape.getMax_X()+15,110);
        text("Score: "+(int) Spot.getScore(),Shape.getMax_X()+15,140);
        text("Next: ",Shape.getMax_X()+15,170);
        textSize(20);
        int add_x = 300;
        int add_y = 200;

        //draw "NEXT" UI
        for(Shape shape: next_Shapes){
            for(Block block: shape.getAllblocks()){
                int x = (int) block.x + add_x;
                int y = (int) block.y + add_y;

                if(shape.getType()==0) {
                    image(lightBlue_block, x-Shape.getSIZE(), y);
                }else if(shape.getType()==1){
                    image(darkBlue_block, x, y);
                }else if(shape.getType()==2){
                    image(Green_block, x-Shape.getSIZE(), y);
                }else if(shape.getType()==3){
                    image(Orange_block, x, y);
                }else if(shape.getType()==4){
                    image(Purple_block, x, y);
                }else if(shape.getType()==5){
                    image(Red_block,x-Shape.getSIZE(),y);
                }else if(shape.getType()==6){
                    image(Yellow_block, x-Shape.getSIZE(), y);
                }
            }
            add_y+=130;

        }

        // draw all lines x
        float cord_line1_x = 0;
        float cord_line1_y = 0;
        float cord_line2_x = 0;
        float cord_line2_y = Shape.getMax_Y();

        for(int i=0;i<(Shape.getMax_X()/Shape.getSIZE())+1;i++){


            line(cord_line1_x,cord_line1_y,cord_line2_x,cord_line2_y);
            fill(0,0,0);
            cord_line1_x += Shape.getSIZE();
            cord_line2_x += Shape.getSIZE();

        }

        //draw all lines y
        cord_line1_x = 0;
        cord_line1_y = 0;
        cord_line2_x = Shape.getMax_X();
        cord_line2_y = 0;

        for(int i=0;i<Shape.getMax_Y()/Shape.getSIZE();i++){


            line(cord_line1_x,cord_line1_y,cord_line2_x,cord_line2_y);
            fill(0,0,0);
            cord_line1_y += Shape.getSIZE();
            cord_line2_y += Shape.getSIZE();
        }

        //draw all blocks
        for(Shape shape: Shape.getAll_Shapes()) {
            for (Block block : shape.getAllblocks()) {
                int x = (int) block.x;
                int y = (int) block.y;

                if(shape.getType()==0) {
                    image(lightBlue_block, x, y);
                }else if(shape.getType()==1){
                    image(darkBlue_block, x, y);
                }else if(shape.getType()==2){
                    image(Green_block, x, y);
                }else if(shape.getType()==3){
                    image(Orange_block, x, y);
                }else if(shape.getType()==4){
                    image(Purple_block, x, y);
                }else if(shape.getType()==5){
                    image(Red_block,x,y);
                }else if(shape.getType()==6){
                    image(Yellow_block, x, y);
                }




            }
        }
        // check for collision with the bottom of the screen and with other objects
        if(!dead) {
            if(checkCollision(moving_shape)){
                actOnCollision();
            }

        }
    }
    public void keyPressed() {
        if(!dead) {
            if(key=='a'){
                try {
                    ai.bestMove(moving_shape);
                        //System.out.println("X: "+cord[0]+"Y: "+cord[1]);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }

            if (keyCode == LEFT) {
                // move on block left
                moving_shape.move_left();
            } else if (keyCode == RIGHT) {
                // move on block right
                moving_shape.move_right();
            } else if (keyCode == UP) {
                // rotate clockwise for all shape types

                if (moving_shape.getType() == 0) {
                    Blue_line line = (Blue_line) moving_shape;
                    line.rotate_All();
                } else if (moving_shape.getType() == 1) {
                    DarkBlue_L L = (DarkBlue_L) moving_shape;
                    L.rotate_All();
                } else if (moving_shape.getType() == 2) {
                    Green_S S = (Green_S) moving_shape;
                    S.rotate_All();
                } else if (moving_shape.getType() == 3) {
                    Orange_L L = (Orange_L) moving_shape;
                    L.rotate_All();
                } else if (moving_shape.getType() == 4) {
                    Purple_T T = (Purple_T) moving_shape;
                    T.rotate_All();
                } else if (moving_shape.getType() == 5) {
                    Red_Z Z = (Red_Z) moving_shape;
                    Z.rotate_All();
                }


            } else if (keyCode == DOWN) {
                // move on block down
                moving_shape.move_down();
                if(checkCollision(moving_shape)){
                    actOnCollision();
                }
            }
        }
    }


    class Move extends TimerTask {
        // move down every second => gets faster
        public void run() {
            if(!dead) {
                moving_shape.move_down();
            }
        }

    }
    private Shape pickRandomShape(){
        // generate a random shape
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
        // shift next_Shape list => next_Shape[0] will become moving_shape

        next_Shapes[0] = next_Shapes[1];
        next_Shapes[1] = next_Shapes[2];
        next_Shapes[2] = pickRandomShape();

    }
    private void actOnCollision(){
        //_.._

        // adds an extra second for last second moves
        moving_shape.move_up();


        Spot.deleteBlocks();
        moving_shape = next_Shapes[0];
        Shift();

        Shape.getAll_Shapes().add(moving_shape);
    }


    private boolean checkCollision(Shape moving_shape){

        //collision logic
        for(Block block: moving_shape.getAllblocks()){
            // at the bottom of the screen
            if(block.y==Shape.getMax_Y()){
               return true;
            }
            //checking for all collisions with all blocks
            else{
                for(int index = 0; index<Shape.getAll_Shapes().size()-1;index++){
                    for(Block b: Shape.getAll_Shapes().get(index).getAllblocks()){
                        if(b.y==0){
                           dead = true;
                           System.out.println("you have died :((");
                        }

                        if(b.x==block.x && b.y==block.y){

                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    protected boolean checkCollisionForAI(Shape moving_shape){

        //collision logic
        for(Block block: moving_shape.getAllblocks()){
            // at the bottom of the screen
            if(block.y+Shape.getSIZE()==Shape.getMax_Y()){
                return true;
            }
            //checking for all collisions with all blocks
            else{
                for(int index = 0; index<Shape.getAll_Shapes().size()-1;index++){
                    for(Block b: Shape.getAll_Shapes().get(index).getAllblocks()){
                        if(b.y==0){
                            dead = true;
                            System.out.println("you have died :((");
                        }

                        if(b.x==block.x && b.y==block.y+Shape.getSIZE()){

                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }





    public static void main(String... args) {
        PApplet.main("proc.sketches.MAIN");
    }

}
