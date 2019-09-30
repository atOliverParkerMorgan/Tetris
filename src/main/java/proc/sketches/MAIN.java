package proc.sketches;

import proc.sketches.Blocks.Block;
import proc.sketches.Blocks.Shapes.Blue_line;
import processing.core.PApplet;

public class MAIN extends PApplet {

    private int Timer;
    private int next_game_update = Timer+3;
    private Blue_line Test;

    public void settings(){
        // Canvas
        size(800, 900);
        Test = new Blue_line();

    }public void draw(){
        Timer = second();

        if(Timer == second()){
            next_game_update++;
            Test.move_down();
            System.out.println("here");
        }
        for(Block block: Test.allblocks){
            fill(Test.colour[0],Test.colour[1],Test.colour[2]);
            rect(block.x,block.y,block.x+block.size,block.y+block.size);
            fill(255,255,255);


        }



    }



    public static void main(String... args) {
        PApplet.main("proc.sketches.MAIN");
    }

}
