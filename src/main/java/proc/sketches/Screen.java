package proc.sketches;

import proc.sketches.Board.Move;
import proc.sketches.Board.Spot;
import proc.sketches.Game.AI.MiniMax;
import proc.sketches.Game.AI.MoveStrategy;
import proc.sketches.Game.Game;
import proc.sketches.pieces.*;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class Screen extends PApplet {
    private Game mGame;
    private List<Game> Game_history;

    private PImage King_img;
    private PImage King_black_img;

    private PImage Queen_img;
    private PImage Queen_black_img;

    private PImage Bishop_img;
    private PImage Bishop_black_img;

    private PImage Knight_img;
    private PImage Knight_black_img;

    private PImage Rook_img;
    private PImage Rook_black_img;

    private PImage Pawn_img;
    private PImage Pawn_black_img;

    private boolean show_hint = false;

    //Menu
    private boolean AIvsAI = false;
    private boolean PLAYERvsAI = false;
    private boolean PLAYERvsPLAYER = true;
    private boolean MENU = true;
    private final boolean AI_player_white = false;

    // for ai so the canvas has a couple frames to update before the ai starts thinking
    private int draw;




    public void settings() {
        draw = 0;

        // Canvas
        size(800, 900);
        // noLoop();

        // add the base dir of the images
        String base_dir = "C:\\Users\\2019-e-morgan\\IdeaProjects\\Chess_AI\\src\\main\\java\\proc\\sketches\\sprites\\";

        King_img = loadImage(base_dir+"King.png");
        King_black_img  = loadImage(base_dir+"King_black.png");

        Queen_img = loadImage(base_dir+"Queen.png");
        Queen_black_img = loadImage(base_dir+"Queen_black.png");

        Bishop_img = loadImage(base_dir+"Bishop.png");
        Bishop_black_img = loadImage(base_dir+"Bishop_black.png");

        Knight_img = loadImage(base_dir+"Knight.png");
        Knight_black_img = loadImage(base_dir+"Knight_black.png");

        Rook_img = loadImage(base_dir+"Rook.png");
        Rook_black_img = loadImage(base_dir+"Rook_black.png");

        Pawn_img = loadImage(base_dir+"Pawn.png");
        Pawn_black_img = loadImage(base_dir+"Pawn_black.png");
        // add pieces to for black player
        mGame  = new Game();
        Game_history = new ArrayList<>();
        Game.Possible_moves_white(mGame.board,mGame.white,mGame.black);


    }
    private int[] mouse_pos(){
        int X = 0;
        int Y = 0;
        if(mouseX>=100 && mouseX <200){X = 1;}
        if(mouseY>=100 && mouseY <200){Y = 1;}
        if(mouseX>=200 && mouseX <300){X = 2;}
        if(mouseY>=200 && mouseY <300){Y = 2;}
        if(mouseX>=300 && mouseX <400){X = 3;}
        if(mouseY>=300 && mouseY <400){Y = 3;}
        if(mouseX>=400 && mouseX <500){X = 4;}
        if(mouseY>=400 && mouseY <500){Y = 4;}
        if(mouseX>=500 && mouseX <600){X = 5;}
        if(mouseY>=500 && mouseY <600){Y = 5;}
        if(mouseX>=600 && mouseX <700){X = 6;}
        if(mouseY>=600 && mouseY <700){Y = 6;}
        if(mouseX>=700 && mouseX <800){X = 7;}
        if(mouseY>=700 && mouseY <800){Y = 7;}
        return new int[]{X, Y};
    }

    // function onclick for button
    private boolean overCircle(int x, int diameter) {
        float disX = x - mouseX;
        float disY = 850 - mouseY;
        return sqrt(sq(disX) + sq(disY)) < diameter / 2;
    }

    public void draw() {

        if (MENU) {
            fill(255,153,153);
            rect(0,0,800,900);
            textSize(64);
            fill(0, 51, 51);
            text("CHESS", 300, 150);
            textSize(20);
            text("Oliver Morgan", 350, 880);

            if(mouseX>=250 && mouseX<600 && mouseY>=350 && mouseY<430){
                PLAYERvsPLAYER = true;
                PLAYERvsAI = false;
                AIvsAI = false;
            }
            else if(mouseX>=280 && mouseX<520 && mouseY>=450 && mouseY<530){
                PLAYERvsPLAYER = false;
                PLAYERvsAI = true;
                AIvsAI = false;
            }
            else if(mouseX>=320 && mouseX<470 && mouseY>=550 && mouseY<630){
                PLAYERvsPLAYER = false;
                PLAYERvsAI = false;
                AIvsAI = true;
            }else{
                PLAYERvsPLAYER = false;
                PLAYERvsAI = false;
                AIvsAI = false;
            }




            textSize(32);
            if(PLAYERvsPLAYER){
                fill(153,153,0);
                rect(250,350,350,80);
            }
            fill(102,0,51);
            text("PLAYER vs PLAYER", 270,400);

            if (PLAYERvsAI){
                fill(153,153,0);
                rect(280,450,240,80);

            }

            fill(102,0,51);
            text("PLAYER vs AI", 300,500);


            if (AIvsAI){
                fill(153,153,0);
                rect(320,550,150,80);

            }
            fill(102,0,51);
            text("AI vs AI", 340,600);





        } else {


            // create board
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((i + j + 1) % 2 == 0) {
                        fill(255, 255, 255); // white

                        if (show_hint) {
                            for (Move m : mGame.Piece_moving.all_possible_moves) {
                                if (m.spot.x == i && m.spot.y == j) {
                                    fill(0, 204, 102);

                                }
                            }
                        }
                    } else {
                        fill(225, 225, 102); // black

                        if (show_hint) {
                            for (Move m : mGame.Piece_moving.all_possible_moves) {
                                if (m.spot.x == i && m.spot.y == j) {
                                    fill(0, 204, 102);
                                }
                            }
                        }

                    }
                    int WIDTH = 800;
                    int BLOCK_X = WIDTH / 8;
                    int HEIGHT = 800;
                    int BLOCK_Y = HEIGHT / 8;
                    rect(i * BLOCK_X, j * BLOCK_Y, (i + 1) * BLOCK_X, (j + 1) * BLOCK_Y);
                }
            }
            // getting the board ready
            // add menu
            fill(220, 220, 220);
            rect(0, 800, 800, 800);

            int Y_cord = 800;

            if (mGame.white_menu) {

                //show pieces
                image(Queen_img, 600, Y_cord);

                image(Rook_img, 450, Y_cord);

                image(Bishop_img, 300, Y_cord);

                image(Knight_img, 150, Y_cord);


            } else if (mGame.black_menu) {


                //show pieces
                image(Queen_black_img, 600, Y_cord);

                image(Rook_black_img, 450, Y_cord);

                image(Bishop_black_img, 300, Y_cord);

                image(Knight_black_img, 150, Y_cord);


            } else {


                String text;
                if (mGame.turn % 2 == 0) {
                    text = "BLACK";
                } else {
                    text = "WHITE";
                }
                textSize(32);
                fill(135, 87, 87);
                text(text, 350, Y_cord + 50);
                text(mGame.turn, 10, Y_cord + 50);


            }

            for (Spot[] spots : mGame.board.spots) {
                for (Spot spot : spots) {
                    if (spot.isOccupied() || spot.toShow()) {
                        switch (spot.get_Piece_category()) {
                            case "King_white":
                                image(King_img, spot.x * 100, spot.y * 100);
                                break;
                            case "Queen_white":
                                image(Queen_img, spot.x * 100, spot.y * 100);
                                break;
                            case "Bishop_white":
                                image(Bishop_img, spot.x * 100, spot.y * 100);
                                break;
                            case "Knight_white":
                                image(Knight_img, spot.x * 100, spot.y * 100);
                                break;
                            case "Rook_white":
                                image(Rook_img, spot.x * 100, spot.y * 100);
                                break;
                            case "Pawns_white":
                                image(Pawn_img, spot.x * 100, spot.y * 100);
                                break;
                            case "King_black":
                                image(King_black_img, spot.x * 100, spot.y * 100);
                                break;
                            case "Queen_black":
                                image(Queen_black_img, spot.x * 100, spot.y * 100);
                                break;
                            case "Bishop_black":
                                image(Bishop_black_img, spot.x * 100, spot.y * 100);
                                break;
                            case "Knight_black":
                                image(Knight_black_img, spot.x * 100, spot.y * 100);
                                break;
                            case "Rook_black":
                                image(Rook_black_img, spot.x * 100, spot.y * 100);
                                break;
                            case "Pawns_black":
                                image(Pawn_black_img, spot.x * 100, spot.y * 100);
                                break;
                        }
                    }

                }
            }
            if (mGame.moving) {
                int X;
                int Y;
                // the x and y from 0 - 800 to 0 - 7
                X = mouse_pos()[0];
                Y = mouse_pos()[1];
                // default
                for (Spot[] spots : mGame.board.spots) {
                    for (Spot spot : spots) {
                        spot.mouse_on = false;
                        spot.show_piece = null;
                    }
                }
                // change the look of the spot were the mouse is located
                Spot spot = mGame.board.getSpot(X, Y);
                spot.mouse_on = true;
                spot.show_on_Spot(mGame.Piece_moving);
            }

            if(mGame.board.currentPlayer.white==AI_player_white && !PLAYERvsPLAYER && draw >= 1|| AIvsAI && !PLAYERvsPLAYER && draw >= 1) {

                final MoveStrategy AI_logic = new MiniMax(2);
                Game_history.add(mGame.copy());
                Move move_AI = AI_logic.execute(mGame.copy());
                mGame.MOVE(move_AI,true);
                draw = 0;





            }
            if(mGame.board.currentPlayer.white==AI_player_white && !PLAYERvsPLAYER || AIvsAI && !PLAYERvsPLAYER ) {
                // draw has ten frames to update
                draw++;
            }



        }
    }
    public void keyPressed(){
        if (key == 'z' && Game_history.size() > 0) {
            mGame = Game_history.get(Game_history.size() - 1);
            Game_history.remove(Game_history.size() - 1);
            System.out.println("!!! TAKE-BACK !!!");
            System.out.println();
        }



    }

    public void mousePressed() {
        if(MENU){
            if(PLAYERvsPLAYER||PLAYERvsAI||AIvsAI){
                MENU = false;
            }

        }else {

            if (PLAYERvsPLAYER || PLAYERvsAI && mGame.turn % 2 != 0) {
                // the x and y from 0 - 800 to 0 - 7
                int X_pos = mouse_pos()[0];
                int Y_pos = mouse_pos()[1];

                // take back move board history bigger than one
                // button pos
                int circle_Size = 80;
                if (overCircle(650, circle_Size) && mGame.white_menu) {

                    mGame.board.getSpot(mGame.change_pawn.x, mGame.change_pawn.y).piece = new Queen(mGame.change_pawn.x, mGame.change_pawn.y, "Queen_white", 900);
                    mGame.white.pieces.add(mGame.board.getSpot(mGame.change_pawn.x, mGame.change_pawn.y).piece);
                    mGame.white.pieces.remove(mGame.change_pawn);
                    mGame.change_pawn = null;
                    mGame.white_menu = false;


                } else if (overCircle(500, circle_Size) && mGame.white_menu) {
                    mGame.board.getSpot(mGame.change_pawn.x, mGame.change_pawn.y).piece = new Rook(mGame.change_pawn.x, mGame.change_pawn.y, "Rook_white", 500);
                    mGame.white.pieces.add(mGame.board.getSpot(mGame.change_pawn.x, mGame.change_pawn.y).piece);
                    mGame.white.pieces.remove(mGame.change_pawn);
                    mGame.change_pawn = null;
                    mGame.white_menu = false;


                } else if (overCircle(350, circle_Size) && mGame.white_menu) {

                    mGame.board.getSpot(mGame.change_pawn.x, mGame.change_pawn.y).piece = new Bishop(mGame.change_pawn.x, mGame.change_pawn.y, "Bishop_white", 325);
                    mGame.white.pieces.add(mGame.board.getSpot(mGame.change_pawn.x, mGame.change_pawn.y).piece);
                    mGame.white.pieces.remove(mGame.change_pawn);
                    mGame.change_pawn = null;
                    mGame.white_menu = false;


                } else if (overCircle(200, circle_Size) && mGame.white_menu) {

                    mGame.board.getSpot(mGame.change_pawn.x, mGame.change_pawn.y).piece = new Knight(mGame.change_pawn.x, mGame.change_pawn.y, "Knight_white", 300);
                    mGame.white.pieces.add(mGame.board.getSpot(mGame.change_pawn.x, mGame.change_pawn.y).piece);
                    mGame.white.pieces.remove(mGame.change_pawn);
                    mGame.change_pawn = null;
                    mGame.white_menu = false;


                } else if (overCircle(650, circle_Size) && mGame.black_menu) {
                    mGame.board.getSpot(mGame.change_pawn.x, mGame.change_pawn.y).piece = new Queen(mGame.change_pawn.x, mGame.change_pawn.y, "Queen_black", 900);
                    mGame.black.pieces.add(mGame.board.getSpot(mGame.change_pawn.x, mGame.change_pawn.y).piece);
                    mGame.black.pieces.remove(mGame.change_pawn);
                    mGame.change_pawn = null;
                    mGame.black_menu = false;


                } else if (overCircle(500, circle_Size) && mGame.black_menu) {
                    mGame.board.getSpot(mGame.change_pawn.x, mGame.change_pawn.y).piece = new Rook(mGame.change_pawn.x, mGame.change_pawn.y, "Rook_black", 500);
                    mGame.black.pieces.add(mGame.board.getSpot(mGame.change_pawn.x, mGame.change_pawn.y).piece);
                    mGame.black.pieces.remove(mGame.change_pawn);
                    mGame.change_pawn = null;
                    mGame.black_menu = false;


                } else if (overCircle(350, circle_Size) && mGame.black_menu) {
                    mGame.board.getSpot(mGame.change_pawn.x, mGame.change_pawn.y).piece = new Bishop(mGame.change_pawn.x, mGame.change_pawn.y, "Bishop_black",-325);
                    mGame.black.pieces.add(mGame.board.getSpot(mGame.change_pawn.x, mGame.change_pawn.y).piece);
                    mGame.black.pieces.remove(mGame.change_pawn);
                    mGame.change_pawn = null;
                    mGame.black_menu = false;


                } else if (overCircle(200, circle_Size) && mGame.black_menu) {
                    mGame.board.getSpot(mGame.change_pawn.x, mGame.change_pawn.y).piece = new Knight(mGame.change_pawn.x, mGame.change_pawn.y, "Knight_black", -300);
                    mGame.black.pieces.add(mGame.board.getSpot(mGame.change_pawn.x, mGame.change_pawn.y).piece);
                    mGame.black.pieces.remove(mGame.change_pawn);
                    mGame.change_pawn = null;
                    mGame.black_menu = false;


                }

                if (mGame.board.getSpot(X_pos, Y_pos).isOccupied()) {
                    // start the moving process
                    mGame.moving = true;
                    show_hint = true;

                    // the piece that we want to move
                    mGame.Piece_moving = mGame.board.getSpot(X_pos, Y_pos).piece;
                }
            }
        }


    }


    public void mouseReleased(){
        if(PLAYERvsPLAYER || PLAYERvsAI && mGame.board.currentPlayer.white!=AI_player_white) {

            // reset board
            assert mGame.board != null;
            for (Spot[] spots : mGame.board.spots) {
                for (Spot s : spots) {

                    s.mouse_on = false;
                    s.show_piece = null;
                }

            }


            if (mGame.moving) {
                // the x and y from 0 - 800 to 0 - 7

                int X_pos = mouse_pos()[0];
                int Y_pos = mouse_pos()[1];

                // the piece that we want to move

                // the spot that we want to move it to
                Spot spot = mGame.board.getSpot(X_pos, Y_pos);
                // the spot were it was located before we started moving it
                Spot old_spot;
                boolean Error = false;
                try {
                    old_spot = mGame.board.getSpot(mGame.Piece_moving.x, mGame.Piece_moving.y);
                } catch (Exception e) {
                    print(e);
                    print('\n');
                    Error = true;
                    old_spot = null;
                }
                // check if an error hasn't accrued this error can happen if you overload the program with clicks
                if (!Error) {

                    Move new_move = new Move(spot, old_spot);
                    // check if piece is in the possible moves + if its the players turn
                    show_hint = false;
                    mGame.moving = false;

                    for (Move m : mGame.Piece_moving.all_possible_moves) {
                        if (m.spot == new_move.spot && m.old_spot == new_move.old_spot) {
                            Game_history.add(mGame.copy());
                            mGame.MOVE(new_move, true);
                        }
                    }

                }
            }
        }
    }


    public static void main(String... args) {
        PApplet.main("proc.sketches.Screen");
    }

}
