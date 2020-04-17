package com.example.noughtsandcrossesv1;

import java.util.ArrayList;

//  This is a fake AI for the game
//  Basically it chooses a random number
//  The random number is 1-9, and this represents
//  the square on the grid. If it is not taken, then
//  take it.
public class PseudoAI {

    public ArrayList<Integer> vacantMoves;

    //  Constructor
    public PseudoAI(ArrayList<Integer> vm){
        this.vacantMoves = new ArrayList<Integer>();
        this.vacantMoves = vm;
    }


    public String GetTileNameByTileNumber(int num){

        String name = "ERROR";

        switch(num){

            case 0:
                name = "btnR1C1";
                break;

            case 1:
                name = "btnR1C2";
                break;

            case 2:
                name = "btnR1C3";
                break;

            case 3:
                name = "btnR2C1";
                break;

            case 4:
                name = "btnR2C2";
                break;

            case 5:
                name = "btnR2C3";
                break;

            case 6:
                name = "btnR3C1";
                break;

            case 7:
                name = "btnR3C2";
                break;

            case 8:
                name = "btnR3C3";
                break;

        }

        return name;

    }

}
