package com.example.noughtsandcrossesv1;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

//  The app's main activity.
public class MainActivity extends AppCompatActivity {

    private GameStateHolder gameStateHolder;
    private GameGridManager gameGridManager;
    private PseudoAI pseudoAI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
            ===============================
         */



        //  New instance of Game State Holder.
        this.gameStateHolder = new GameStateHolder();

        //  New instance of Game Grid manager
        this.gameGridManager = new GameGridManager();

        //  Gets the vacant spots.
        this.pseudoAI = new PseudoAI(this.gameGridManager.GetVacancies());

        this.ChangeTextLabelSide();

        /*
            ===============================
         */

    }


    public void ChangeButtonStyle(View view){

        Button btn = (Button)findViewById(view.getId());

        //  Side 1 is red,
        //  side 2 is blue
        if(this.gameStateHolder._currentTurn == 1){
            //  THIS WILL BE RED
            btn.setText(R.string.cross);
            btn.setTextColor(getResources().getColor(R.color.white));
            btn.setBackgroundColor(getResources().getColor(R.color.redButton));

        }

        if(this.gameStateHolder._currentTurn == 2){
            //  This will be blue
            btn.setText(R.string.nought);
            btn.setTextColor(getResources().getColor(R.color.white));
            btn.setBackgroundColor(getResources().getColor(R.color.blueButton));
        }

    }

    //  Starts an AI game.
    public void StartAIGame(View view){

        this.ResetGame();

        this.gameStateHolder.StartAIGame();

        TextView status = (TextView)findViewById(R.id.lblGameStatus);
        status.setText(R.string.aiState);

        this.gameStateHolder._currentTurn = 2;

        this.AIMove();

        this.ChangeTextLabelSide();

    }

    //  Starts a player vs player game.
    public void StartPlayervsPlayerGame(View view){

        this.ResetGame();
        this.gameStateHolder.StartPlayerGame();

        TextView status = (TextView)findViewById(R.id.lblGameStatus);
        status.setText(R.string.playerState);

        this.ChangeTextLabelSide();

    }

    //  Gets the button name.
    public String GetButtonNameAsString(View view){

        //  Because the getID returns an integer, get the string
        //  by using get resources.
        return getResources().getResourceEntryName(view.getId());

    }


    //  The one method for all of the buttons.
    public void ButtonLogic(View view, String btnName){

        Button btn = findViewById(view.getId());

        if(this.gameStateHolder._aiGameIsOn == true){

            //  Victory checker
            VictoryChecker vc = new VictoryChecker(this.gameGridManager);

            //  Checks a draw
            vc.CheckDraw(this.gameStateHolder._currentTurn);

            boolean changedSuccesfully = this.gameGridManager.AssignTileOnGrid(btnName, this.gameStateHolder._currentTurn);

            if(changedSuccesfully == true && vc.draw == false){

                //  Check victory conditions
                vc.CheckColWin(this.gameStateHolder._currentTurn);
                vc.CheckRowWin(this.gameStateHolder._currentTurn);
                vc.CheckDiagonalWin(this.gameStateHolder._currentTurn);

                //  If any kind of victory
                if(vc.colWin == false && vc.rowWin == false && vc.diagWin == false){

                    this.ChangeButtonStyle(btn);
                    this.gameStateHolder.ChangeTurn(this.gameStateHolder._currentTurn);
                    this.ChangeTextLabelSide();


                } else {

                    this.ChangeButtonStyle(btn);

                    TextView txt = findViewById(R.id.lblGameStatus);

                    if(this.gameStateHolder._currentTurn == 1){
                        txt.setText(R.string.p1Win);
                    }

                    if(this.gameStateHolder._currentTurn == 2){
                        txt.setText(R.string.p2Win);
                    }

                }

            } else {

                System.out.println("Sorry this has been taken");

            }

            this.AIMove();

        } else {

            //  Victory checker
            VictoryChecker vc = new VictoryChecker(this.gameGridManager);

            //  Checks a draw
            vc.CheckDraw(this.gameStateHolder._currentTurn);

            boolean changedSuccesfully = this.gameGridManager.AssignTileOnGrid(btnName, this.gameStateHolder._currentTurn);

            if(changedSuccesfully == true && vc.draw == false){

                //  Check victory conditions
                vc.CheckColWin(this.gameStateHolder._currentTurn);
                vc.CheckRowWin(this.gameStateHolder._currentTurn);
                vc.CheckDiagonalWin(this.gameStateHolder._currentTurn);

                //  If any kind of victory
                if(vc.colWin == false && vc.rowWin == false && vc.diagWin == false){

                    this.ChangeButtonStyle(btn);
                    this.gameStateHolder.ChangeTurn(this.gameStateHolder._currentTurn);
                    this.ChangeTextLabelSide();

                } else {

                    this.ChangeButtonStyle(btn);

                    TextView txt = findViewById(R.id.lblGameStatus);

                    if(this.gameStateHolder._currentTurn == 1){
                        txt.setText(R.string.p1Win);
                    }

                    if(this.gameStateHolder._currentTurn == 2){
                        txt.setText(R.string.p2Win);
                    }

                    this.gameStateHolder.EndGame();
                    this.DisableGridButtons();

                }

            } else {

                System.out.println("Sorry this has been taken");

            }

        }

    }

    //  The "AI" move.
    public void AIMove(){

        if(this.gameStateHolder._currentTurn == 2){

            //  A random number.
            int rng = (int)Math.floor(Math.random() * this.pseudoAI.vacantMoves.size());

            String nameOfButton = this.pseudoAI.GetTileNameByTileNumber(rng);

            boolean hasChanged = this.gameGridManager.AssignTileOnGrid(nameOfButton, this.gameStateHolder._currentTurn);

            if(hasChanged == true){

                Utils u = new Utils();
                this.ChangeButtonStyle(findViewById(u.GetButtonIDByStringName(nameOfButton)));

                //  Check for wins
                //  Victory checker
                VictoryChecker vc = new VictoryChecker(this.gameGridManager);

                //  Check victory conditions
                vc.CheckColWin(this.gameStateHolder._currentTurn);
                vc.CheckRowWin(this.gameStateHolder._currentTurn);
                vc.CheckDiagonalWin(this.gameStateHolder._currentTurn);

                //  If any kind of victory
                if(vc.colWin == false && vc.rowWin == false && vc.diagWin == false){

                    this.ChangeButtonStyle(findViewById(u.GetButtonIDByStringName(nameOfButton)));
                    this.gameStateHolder.ChangeTurn(this.gameStateHolder._currentTurn);
                    this.ChangeTextLabelSide();

                } else {

                    this.ChangeButtonStyle(findViewById(u.GetButtonIDByStringName(nameOfButton)));

                    TextView txt = findViewById(R.id.lblGameStatus);

                    if(this.gameStateHolder._currentTurn == 1){
                        txt.setText(R.string.p1Win);
                    }

                    if(this.gameStateHolder._currentTurn == 2){
                        txt.setText(R.string.p2Win);
                    }

                    this.gameStateHolder.EndGame();
                    this.DisableGridButtons();

                }

            } else {

                this.AIMove();

            }

        }

    }

    //  Disables the grid buttons
    public void DisableGridButtons(){

        Button btn1 = (Button)findViewById(R.id.btnR1C1);
        Button btn2 = (Button)findViewById(R.id.btnR1C2);
        Button btn3 = (Button)findViewById(R.id.btnR1C3);

        Button btn4 = (Button)findViewById(R.id.btnR2C1);
        Button btn5 = (Button)findViewById(R.id.btnR2C2);
        Button btn6 = (Button)findViewById(R.id.btnR2C3);

        Button btn7 = (Button)findViewById(R.id.btnR3C1);
        Button btn8 = (Button)findViewById(R.id.btnR3C2);
        Button btn9 = (Button)findViewById(R.id.btnR3C3);

        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
        btn5.setEnabled(false);
        btn6.setEnabled(false);
        btn7.setEnabled(false);
        btn8.setEnabled(false);
        btn9.setEnabled(false);

    }

    //  Resets the grid buttons
    public void ResetGridButtons(){

        Button btn1 = (Button)findViewById(R.id.btnR1C1);
        Button btn2 = (Button)findViewById(R.id.btnR1C2);
        Button btn3 = (Button)findViewById(R.id.btnR1C3);
        Button btn4 = (Button)findViewById(R.id.btnR2C1);
        Button btn5 = (Button)findViewById(R.id.btnR2C2);
        Button btn6 = (Button)findViewById(R.id.btnR2C3);
        Button btn7 = (Button)findViewById(R.id.btnR3C1);
        Button btn8 = (Button)findViewById(R.id.btnR3C2);
        Button btn9 = (Button)findViewById(R.id.btnR3C3);

        //  Reset the enabling
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        btn5.setEnabled(true);
        btn6.setEnabled(true);
        btn7.setEnabled(true);
        btn8.setEnabled(true);
        btn9.setEnabled(true);

        //  Reset the style
        btn1.setBackgroundColor(getResources().getColor(R.color.neutralButton));
        btn2.setBackgroundColor(getResources().getColor(R.color.neutralButton));
        btn3.setBackgroundColor(getResources().getColor(R.color.neutralButton));
        btn4.setBackgroundColor(getResources().getColor(R.color.neutralButton));
        btn5.setBackgroundColor(getResources().getColor(R.color.neutralButton));
        btn6.setBackgroundColor(getResources().getColor(R.color.neutralButton));
        btn7.setBackgroundColor(getResources().getColor(R.color.neutralButton));
        btn8.setBackgroundColor(getResources().getColor(R.color.neutralButton));
        btn9.setBackgroundColor(getResources().getColor(R.color.neutralButton));

        //  Set the text colour
        btn1.setTextColor(getResources().getColor(R.color.black));
        btn2.setTextColor(getResources().getColor(R.color.black));
        btn3.setTextColor(getResources().getColor(R.color.black));
        btn4.setTextColor(getResources().getColor(R.color.black));
        btn5.setTextColor(getResources().getColor(R.color.black));
        btn6.setTextColor(getResources().getColor(R.color.black));
        btn7.setTextColor(getResources().getColor(R.color.black));
        btn8.setTextColor(getResources().getColor(R.color.black));
        btn9.setTextColor(getResources().getColor(R.color.black));

        //  Set the text.
        btn1.setText(R.string.neutral);
        btn2.setText(R.string.neutral);
        btn3.setText(R.string.neutral);
        btn4.setText(R.string.neutral);
        btn5.setText(R.string.neutral);
        btn6.setText(R.string.neutral);
        btn7.setText(R.string.neutral);
        btn8.setText(R.string.neutral);
        btn9.setText(R.string.neutral);


    }

    //  Shows the player whose turn is it anyways
    public void ChangeTextLabelSide(){

        TextView txt = (TextView)findViewById(R.id.lblGameTurn);

        if(this.gameStateHolder._currentTurn == 1){
            txt.setText(R.string.turnP1);
        }

        if(this.gameStateHolder._currentTurn == 2){
            txt.setText(R.string.turnP2);
        }

    }

    //  ======================
    //  Game Buttons
    public void btnR1C1(View view){
        this.ButtonLogic(view, "btnR1C1");
    }

    public void btnR1C2(View view){
        this.ButtonLogic(view, "btnR1C2");
    }

    public void btnR1C3(View view){
        this.ButtonLogic(view, "btnR1C3");
    }

    public void btnR2C1(View view){
        this.ButtonLogic(view, "btnR2C1");
    }

    public void btnR2C2(View view){
        this.ButtonLogic(view, "btnR2C2");
    }

    public void btnR2C3(View view){
        this.ButtonLogic(view, "btnR2C3");
    }

    public void btnR3C1(View view){
        this.ButtonLogic(view, "btnR3C1");
    }

    public void btnR3C2(View view){
        this.ButtonLogic(view, "btnR3C2");
    }

    public void btnR3C3(View view){
        this.ButtonLogic(view, "btnR3C3");
    }

    //  =======================

    //  Resets the entire game.
    public void ResetGame(){

        //  Ends the game
        this.gameStateHolder.EndGame();

        //  New instances of gamestateholder and gamegridmanager
        this.gameStateHolder = new GameStateHolder();
        this.gameGridManager = new GameGridManager();

        //  Reset the appearance of the game grid buttons
        this.ResetGridButtons();

    }







        /*
            ===============================
         */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
