package com.example.noughtsandcrossesv1;

//  Game state holder
//  Holds references to the current game state.
public class GameStateHolder {

    //  If it is an ai game
    public boolean _aiGameIsOn;

    //  If it is a player vs player game
    public boolean _playerGameIsOn;

    //  Current turn
    //  1 = player 1, 2 = player 2, 0 = neutral
    public int _currentTurn;

    //  Constructor
    public GameStateHolder(){

        this._playerGameIsOn = false;
        this._aiGameIsOn = false;
        this._currentTurn = 0;

    }

    //  Starts an ai game
    //  AI will always go first
    public void StartAIGame(){

        this._aiGameIsOn = true;
        this._currentTurn = 2;

    }

    //  Starts a player vs player game
    public void StartPlayerGame(){

        this._playerGameIsOn = true;

        this.FirstSideTurn();

    }

    //  Ends the game.
    public void EndGame(){

        if(this._aiGameIsOn == true){
            this._aiGameIsOn = false;
        }

        if(this._playerGameIsOn == true){
            this._playerGameIsOn = false;
        }

        this._currentTurn = 0;

    }

    //  Changes the current turn number.
    public void ChangeTurn(int currentTurn){

        if(currentTurn == 1){
            this._currentTurn = 2;
        } else {
            this._currentTurn = 1;
        }

    }


    //  The first turn!
    public void FirstSideTurn(){

        int rng = (int)Math.floor(Math.random() * 100 + 1);

        if(rng > 50){
            this._currentTurn = 1;
        } else {
            this._currentTurn = 2;
        }

    }

}
