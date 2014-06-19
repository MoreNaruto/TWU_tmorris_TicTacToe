package com.thoughtWorks;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner in = new Scanner(System.in);
        int input;
        System.out.println("Do you want to play the computer or a player? ");
        System.out.println("Type '0' for computer or '1' for another player: ");
        input = Integer.parseInt(in.nextLine());
        TicTacToeInitialize start = new TicTacToeInitialize(System.out);
        if(input == 0) {
            start.makeBoard();
            start.computerTurn();
        } else {
            start.makeBoard();
            start.playerTurn();
        }
    }


}
