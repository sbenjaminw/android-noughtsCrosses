package com.example.noughtsandcrossesv1;

import java.util.ArrayList;

//  Holds what player has clicked on what square
public class GameGridManager {

    //  Tiles
    public int[] tiles;

    //  Vacancies
    public ArrayList<Integer> vacancies;

    //  Rows
    public int[] row1, row2, row3;

    //  Columns
    public int[] cols1, cols2, cols3;

    //  Diagonals
    public int[] diag1, diag2;

    //  Constructor.
    public GameGridManager(){

        this.tiles = new int[9];
        this.vacancies = new ArrayList<Integer>();

    }

    //  Get vacant move list.
    public ArrayList<Integer> GetVacancies(){

        for(int a = 0; a < this.tiles.length; a++){

            if(this.tiles[a] == 0){
                this.vacancies.add(this.tiles[a]);
            }

        }

        return this.vacancies;

    }

    //  Get the rows.
    public int[][] GetRows(){

        int[][] rows = new int[3][3];

        rows[0][0] = tiles[0];
        rows[0][1] = tiles[1];
        rows[0][2] = tiles[2];

        rows[1][0] = tiles[3];
        rows[1][1] = tiles[4];
        rows[1][2] = tiles[5];

        rows[2][0] = tiles[6];
        rows[2][1] = tiles[7];
        rows[2][2] = tiles[8];

        return rows;

    }

    //  Get the columns.
    public int[][] GetColumns(){

        int[][] cols = new int[3][3];

        cols[0][0] = tiles[0];
        cols[0][1] = tiles[3];
        cols[0][2] = tiles[6];

        cols[1][0] = tiles[1];
        cols[1][1] = tiles[4];
        cols[1][2] = tiles[7];

        cols[2][0] = tiles[2];
        cols[2][1] = tiles[5];
        cols[2][2] = tiles[8];

        return cols;

    }

    //  Gets diagonsals
    public int[][] GetDiagonals(){

        int[][] diags = new int[3][3];

        diags[0][0] = tiles[0];
        diags[0][1] = tiles[4];
        diags[0][2] = tiles[8];

        diags[1][0] = tiles[2];
        diags[1][1] = tiles[4];
        diags[1][2] = tiles[6];

        return diags;

    }


    //  Assigns a tile to the virtual grid.
    public boolean AssignTileOnGrid(String btnName, int _currentTurn){

        boolean hasChanged = false;

        switch(btnName){

            case "btnR1C1":

                if(this.tiles[0] == 0){
                    this.tiles[0] = _currentTurn;
                    hasChanged = true;
                }

                break;

            case "btnR1C2":


                if(this.tiles[1] == 0){
                    this.tiles[1] = _currentTurn;
                    hasChanged = true;
                }

                break;

            case "btnR1C3":

                if(this.tiles[2] == 0){
                    this.tiles[2] = _currentTurn;
                    hasChanged = true;
                }

                break;

            case "btnR2C1":

                if(this.tiles[3] == 0){
                    this.tiles[3] = _currentTurn;
                    hasChanged = true;
                }

                break;

            case "btnR2C2":

                if(this.tiles[4] == 0){
                    this.tiles[4] = _currentTurn;
                    hasChanged = true;
                }

                break;

            case "btnR2C3":

                if(this.tiles[5] == 0){
                    this.tiles[5] = _currentTurn;
                    hasChanged = true;
                }

                break;

            case "btnR3C1":

                if(this.tiles[6] == 0){
                    this.tiles[6] = _currentTurn;
                    hasChanged = true;
                }

                break;

            case "btnR3C2":

                if(this.tiles[7] == 0){
                    this.tiles[7] = _currentTurn;
                    hasChanged = true;
                }

                break;

            case "btnR3C3":

                if(this.tiles[8] == 0){
                    this.tiles[8] = _currentTurn;
                    hasChanged = true;
                }

                break;
        }

        return hasChanged;

    }


}
