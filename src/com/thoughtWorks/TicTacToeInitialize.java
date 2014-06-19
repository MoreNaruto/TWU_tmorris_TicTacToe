package com.thoughtWorks;

import java.io.Console;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static javax.management.Query.and;

/**
 * Created by thomasmorris on 6/18/14.
 */
public class TicTacToeInitialize{
    public String topLeft = " ";
    public String topMid = " ";
    public String topRight = " ";
    public String midLeft = " ";
    public String midMid = " ";
    public String midRight = " ";
    public String botLeft = " ";
    public String botMid = " ";
    public String botRight = " ";
    public String player1Piece = "T";
    public String player2Piece = "M";
    public List<Integer> usedPositions;
    public int computerInput;
    public boolean player1Turn = true;
    public boolean gameOver = false;
    private PrintStream out;

    public TicTacToeInitialize(PrintStream out) throws InterruptedException, IOException {

        this.out = out;

    }

    public void makeBoard() throws InterruptedException, IOException {
        out.println(topLeft + " |" + topMid + " |" + topRight);
        out.println("--------");
        out.println(midLeft + " |" + midMid + " |" + midRight);
        out.println("--------");
        out.println(botLeft + " |" + botMid + " |" + botRight);


    }

    public boolean winOrDraw(){
        String player1Win = player1Piece.concat(player1Piece).concat(player1Piece);
        String player2Win = player2Piece.concat(player2Piece).concat(player2Piece);
        int whoWon;
        String topRowWin = topLeft.concat(topMid).concat(topRight);
        String midRowWin = midLeft.concat(midMid).concat(midRight);
        String botRowWin = botLeft.concat(botMid).concat(botRight);
        String leftColumnWin = topLeft.concat(midLeft).concat(botLeft);
        String midColumnWin = topMid.concat(midMid).concat(botMid);
        String rightColumnWin = topRight.concat(midRight).concat(botRight);
        String rightDiagonalWin = botLeft.concat(midMid).concat(topRight);
        String leftDiagonalWin = topLeft.concat(midMid).concat(botRight);

        if(topRowWin.equals(player1Win) || midRowWin.equals(player1Win) ||
                botRowWin.equals(player1Win) || leftColumnWin.equals(player1Win) ||
                midColumnWin.equals(player1Win) || rightColumnWin.equals(player1Win) ||
                rightDiagonalWin.equals(player1Win) || leftDiagonalWin.equals(player1Win)){
            out.println("Player 1 has won!");
            return true;
        } else if (topRowWin.equals(player2Win) || midRowWin.equals(player2Win) ||
                botRowWin.equals(player2Win) || leftColumnWin.equals(player2Win) ||
                midColumnWin.equals(player2Win) || rightColumnWin.equals(player2Win) ||
                rightDiagonalWin.equals(player2Win) || leftDiagonalWin.equals(player2Win)){
            out.println("Player 2 has won!");
            return true;

        } else if ((!topRight.equals(" ")) && (!topMid.equals(" ")) &&
                (!topLeft.equals(" ")) && (!midLeft.equals(" ")) &&
                (!midMid.equals(" ")) && (!midRight.equals(" ")) &&
                (!botLeft.equals(" ")) && (!botMid.equals(" ")) && (!botRight.equals(" "))) {
            out.println("It's a draw!");
            return true;
        } else {
            return false;
        }
    }

    public void computerTurn() throws IOException, InterruptedException {
        String player = "Player, please enter a location (1-9)";
        Scanner playerInput = new Scanner(System.in);
        String location = " ";
        if(player1Turn){
            System.out.println(player);
            location = playerInput.nextLine();
            setLocation(Integer.parseInt(location), player1Piece);
        } else {
            if (checkForPotentialWin()){
                setLocation(this.computerInput, player2Piece);
            } else {
               findAPosition();
               setLocation(this.computerInput, player2Piece);
            }
        }
        out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        makeBoard();
        gameOver = winOrDraw();
        if(gameOver){
            out.println("Game Over");
        } else {
            computerTurn();
        }
    }

    public void findAPosition() {
        Random r = new Random();
            int i = r.nextInt(9-1) + 1;
            switch (i){
                case 1:
                    if (!topLeft.equals(" ")) {
                        findAPosition();
                    } else {
                        this.computerInput = 1;
                    }
                    break;
                case 2:
                    if (!topMid.equals(" ")){
                        findAPosition();
                    } else {
                        this.computerInput = 2;
                    }
                    break;
                case 3:
                    if (!topRight.equals(" ")) {
                        findAPosition();
                    } else {
                        this.computerInput = 3;
                    }
                    break;
                case 4:
                    if (!midLeft.equals(" ")) {
                        findAPosition();
                    } else {
                        this.computerInput = 4;
                    }
                    break;
                case 5:
                    if (!midMid.equals(" ")) {
                        findAPosition();
                    } else {
                        this.computerInput = 5;
                    }
                    break;
                case 6:
                    if (!midRight.equals(" ")) {
                        findAPosition();
                    } else {
                        this.computerInput = 6;
                    }
                    break;
                case 7:
                    if (!botLeft.equals(" ")) {
                        findAPosition();
                    } else {
                        this.computerInput = 7;
                    }
                    break;
                case 8:
                    if (!botMid.equals(" ")) {
                        findAPosition();
                    } else {
                        this.computerInput = 8;
                    }
                    break;
                case 9:
                    if (!botRight.equals(" ")) {
                        findAPosition();
                    } else {
                        this.computerInput = 9;
                    }
                    break;

            }


    }

    public boolean checkForPotentialWin() {
        String player1MayWin = player1Piece.concat(player1Piece);
        String computerMayWin = player2Piece.concat(player2Piece);
        String p1position1CanWin = botLeft.concat(midLeft);
        String p2position1CanWin = topMid.concat(topRight);
        String p3position1CanWin = midMid.concat(botRight);
        String p1position2CanWin = topLeft.concat(topRight);
        String p2position2CanWin = midMid.concat(botMid);
        String p1position3CanWin = topLeft.concat(topMid);
        String p2position3CanWin = midRight.concat(botRight);
        String p3position3CanWin = botLeft.concat(midMid);
        String p1position4CanWin = topLeft.concat(botLeft);
        String p2position4CanWin = midMid.concat(midRight);
        String p1position5CanWin = topMid.concat(botMid);
        String p2position5CanWin = midRight.concat(midLeft);
        String p3position5CanWin = topLeft.concat(botRight);
        String p4position5CanWin = botLeft.concat(topRight);
        String p1position6CanWin = topRight.concat(botRight);
        String p2position6CanWin = midLeft.concat(midMid);
        String p1position7CanWin = topLeft.concat(midLeft);
        String p2position7CanWin = botMid.concat(botRight);
        String p3position7CanWin = midMid.concat(topRight);
        String p1position8CanWin = botRight.concat(botLeft);
        String p2position8CanWin = topMid.concat(midMid);
        String p1position9CanWin = topRight.concat(midRight);
        String p2position9CanWin = botLeft.concat(botRight);
        String p3position9CanWin = topLeft.concat(midMid);

        if((p1position1CanWin.equals(player1MayWin) || p2position1CanWin.equals(player1MayWin)
                || p3position1CanWin.equals(player1MayWin) ||  p1position1CanWin.equals(computerMayWin) ||
                p2position1CanWin.equals(computerMayWin)
                || p3position1CanWin.equals(computerMayWin)) && topLeft.equals(" ")){
            this.computerInput = 1;
            return true;
        } else if ((p1position2CanWin.equals(player1MayWin) || p2position2CanWin.equals(player1MayWin) ||
                p1position2CanWin.equals(computerMayWin) || p2position2CanWin.equals(computerMayWin))
                && topMid.equals(" ")){
            this.computerInput = 2;
            return true;
        } else if ((p1position3CanWin.equals(player1MayWin) || p2position3CanWin.equals(player1MayWin)
                || p3position3CanWin.equals(player1MayWin) || p1position3CanWin.equals(computerMayWin) ||
                p2position3CanWin.equals(computerMayWin)
                || p3position3CanWin.equals(computerMayWin)) && topRight.equals(" ")){
            this.computerInput = 3;
            return true;
        } else if ((p1position4CanWin.equals(player1MayWin) || p2position4CanWin.equals(player1MayWin) ||
            p1position4CanWin.equals(computerMayWin) || p2position4CanWin.equals(computerMayWin))
                && midLeft.equals(" ")){
            this.computerInput = 4;
            return true;
        } else if ((p1position5CanWin.equals(player1MayWin) || p2position5CanWin.equals(player1MayWin)
                || p3position5CanWin.equals(player1MayWin) || p4position5CanWin.equals(player1MayWin) ||
                p1position5CanWin.equals(computerMayWin) || p2position5CanWin.equals(computerMayWin)
                        || p3position5CanWin.equals(computerMayWin) || p4position5CanWin.equals(computerMayWin))
                && midMid.equals(" ")){
            this.computerInput = 5;
            return true;
        } else if ((p1position6CanWin.equals(player1MayWin) || p2position6CanWin.equals(player1MayWin) ||
        p1position6CanWin.equals(computerMayWin) || p2position6CanWin.equals(computerMayWin))
                && midRight.equals(" ")){
            this.computerInput = 6;
            return true;
        } else if ((p1position7CanWin.equals(player1MayWin) || p2position7CanWin.equals(player1MayWin)
                || p3position7CanWin.equals(player1MayWin) ||
                p1position7CanWin.equals(computerMayWin) || p2position7CanWin.equals(computerMayWin)
                        || p3position7CanWin.equals(computerMayWin)) && botLeft.equals(" ")){
            this.computerInput = 7;
            return true;
        } else if ((p1position8CanWin.equals(player1MayWin) || p2position8CanWin.equals(player1MayWin) ||
                p1position8CanWin.equals(computerMayWin) || p2position8CanWin.equals(computerMayWin))
                && botMid.equals(" ")){
            this.computerInput = 8;
            return true;
        } else if ((p1position9CanWin.equals(player1MayWin) || p2position9CanWin.equals(player1MayWin)
                || p3position9CanWin.equals(player1MayWin) ||
                p1position9CanWin.equals(computerMayWin) || p2position9CanWin.equals(computerMayWin)
                        || p3position9CanWin.equals(computerMayWin)) && botRight.equals(" ")){
            this.computerInput = 9;
            return true;
        } else {
            return false;
        }
    }

    public void playerTurn() throws IOException, InterruptedException {
        String player1 = "Player 1, enter a location (1-9)";
        String player2 = "Player 2, enter a location (1-9)";
        String location = " ";
        Scanner playerInput = new Scanner(System.in);
        if(player1Turn){
            System.out.println(player1);
            location = playerInput.nextLine();
            setLocation(Integer.parseInt(location), player1Piece);
        } else {
            System.out.println(player2);
            location = playerInput.nextLine();
            setLocation(Integer.parseInt(location), player2Piece);
        }
        out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        makeBoard();
        gameOver = winOrDraw();
        if(gameOver){
            out.println("Game Over");
        } else {
            playerTurn();
        }
    }

    public void setLocation(int i, String placeObject) throws IOException, InterruptedException {
        String placeError = "Place " + i + " is already occupied";
        if(i > 9 || i < 1){
            out.println("Please submit a number between 1-9");
            playerTurn();
        } else switch (i) {
            case 1:
                if (!topLeft.equals(" ")) {
                    out.println(placeError);
                    playerTurn();
                } else {
                    topLeft = placeObject;
                }
                break;
            case 2:
                if (!topMid.equals(" ")){
                    out.println(placeError);
                    playerTurn();
                } else {
                    topMid = placeObject;
                }
                break;
            case 3:
                if (!topRight.equals(" ")) {
                    out.println(placeError);
                    playerTurn();
                } else {
                    topRight = placeObject;
                }
                break;
            case 4:
                if (!midLeft.equals(" ")) {
                    out.println(placeError);
                    playerTurn();
                } else {
                    midLeft = placeObject;
                }
                break;
            case 5:
                if (!midMid.equals(" ")) {
                    out.println(placeError);
                    playerTurn();
                } else {
                    midMid = placeObject;
                }
                break;
            case 6:
                if (!midRight.equals(" ")) {
                    out.println(placeError);
                    playerTurn();
                } else {
                    midRight = placeObject;
                }
                break;
            case 7:
                if (!botLeft.equals(" ")) {
                    out.println(placeError);
                    playerTurn();
                } else {
                    botLeft = placeObject;
                }
                break;
            case 8:
                if (!botMid.equals(" ")) {
                    out.println(placeError);
                    playerTurn();
                } else {
                    botMid = placeObject;
                }
                break;
            case 9:
                if (!botRight.equals(" ")) {
                    out.println(placeError);
                    playerTurn();
                } else {
                    botRight = placeObject;
                }
                break;

        }
        if (placeObject.equals(player1Piece)){
            player1Turn = false;
        } else {
            player1Turn = true;
        }

    }
}
