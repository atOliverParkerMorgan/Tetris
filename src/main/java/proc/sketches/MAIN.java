package proc.sketches;

import proc.sketches.Blocks.Block;
import proc.sketches.Blocks.Shape;
import proc.sketches.Blocks.Shapes.Blue_line;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class MAIN extends PApplet {

    private Blue_line Test;

    private PImage darkBlue_block;
    private PImage lightBlue_block;
    private PImage Green_block;
    private PImage Purple_block;
    private PImage Yellow_block;
    private PImage Orange_block;
    private PImage Red_block;

    public void settings(){


        // the place where your images are saved
        String base_dir = "C:\\Users\\2019-e-morgan\\IdeaProjects\\Tetris\\src\\main\\java\\proc\\sketches\\sprites\\";

        darkBlue_block = loadImage(base_dir+"darkBlue.png");
        lightBlue_block = loadImage(base_dir+"lightBlue.png");
        Green_block = loadImage(base_dir + "Green.png");
        Purple_block = loadImage(base_dir + "Purple.png");
        Yellow_block = loadImage(base_dir + "Yellow.png");
        Red_block = loadImage(base_dir+"Red.png");
        Orange_block = loadImage(base_dir+"Orange.png");


        // Canvas
        size(Shape.max_X, Shape.max_Y, JAVA2D);
        Test = new Blue_line();
        // And From your main() method or any other method
        Timer timer = new Timer();
        timer.schedule(new Move(), 0, 1000);




    }public void draw(){


        background(255,255,255);


        for(Block block: Test.allblocks){
            int x = (int) block.x;
            int y = (int) block.y;

            image(darkBlue_block, x,y);

        }

    }
    public void keyPressed() {
        if (key == 'a') {
            Test.move_left();
        }else if (key == 'd') {
            Test.move_right();
        }else if(key == ' '){
            Test.rotate();
        }
    }


    class Move extends TimerTask {
        public void run() {
            Test.move_down();
        }
    }






    public static void main(String... args) {
        PApplet.main("proc.sketches.MAIN");
    }

}
