package com.example.noughtsandcrossesv1;

//  This class checks for a victory on either side.
//  Requires an instance of GameGridManager.
//  It checks for a victory condition for columns, rows and diagonals
//  and also a draw.
public class VictoryChecker {

    //  Instance of game grid manager
    private GameGridManager gm;

    //  Win conditions
    public boolean rowWin;
    public boolean colWin;
    public boolean diagWin;

    //  Draw condition
    public boolean draw;

    public VictoryChecker(GameGridManager gm){
        this.gm = gm;
    }

    //  Checks for a draw
    public void CheckDraw(int currentTurn){

        int[] tiles = gm.tiles;

        for(int a = 0; a < tiles.length; a++){

            if(tiles[a] != 0 && a == tiles.length){

                this.draw = true;

            }

        }

    }

    //  Checks for a row win.
    public void CheckRowWin(int currentTurn){

        //  Get the rows.
        int[][] row = this.gm.GetRows();

        int winCounter = 0;

        for(int a = 0; a < row[0].length; a++){

            for(int b = 0; b < row[a].length; b++){

                if(row[a][b] == currentTurn){

                    winCounter++;

                } else {

                    break;

                }

            }

            if(winCounter == 3){

                this.rowWin = true;
                break;

            }

        }

    }

    //  Check for a column win.
    public void CheckColWin(int currentTurn){

        //  Get the rows.
        int[][] col = this.gm.GetColumns();

        int winCounter = 0;

        for(int a = 0; a < col[0].length; a++){

            for(int b = 0; b < col[a].length; b++){

                if(col[a][b] == currentTurn){

                    winCounter++;

                } else {

                    break;

                }

            }

            if(winCounter == 3){

                this.colWin = true;
                break;

            }

        }

    }

    //  Check for a diagonal win
    public void CheckDiagonalWin(int currentTurn){

        //  Get the rows.
        int[][] diag = this.gm.GetDiagonals();

        int winCounter = 0;

        for(int a = 0; a < diag[0].length; a++){

            for(int b = 0; b < diag[a].length; b++){

                if(diag[a][b] == currentTurn){

                    winCounter++;

                } else {

                    break;

                }

            }

            if(winCounter == 3){

                this.diagWin = true;
                break;

            }

        }

    }

}
