package com.example.noughtsandcrossesv1;

//  A class for random but helpful stuff.
public class Utils {

    //  Get button ID by string name
    public int GetButtonIDByStringName(String btnName){

        int buttonID = 0;

        switch (btnName){

            case "btnR1C1":
                buttonID = R.id.btnR1C1;
                break;

            case "btnR1C2":
                buttonID = R.id.btnR1C2;
                break;

            case "btnR1C3":
                buttonID = R.id.btnR1C3;
                break;

            case "btnR2C1":
                buttonID = R.id.btnR2C1;
                break;

            case "btnR2C2":
                buttonID = R.id.btnR2C2;
                break;

            case "btnR2C3":
                buttonID = R.id.btnR2C3;
                break;

            case "btnR3C1":
                buttonID = R.id.btnR3C1;
                break;

            case "btnR3C2":
                buttonID = R.id.btnR3C2;
                break;

            case "btnR3C3":
                buttonID = R.id.btnR3C3;
                break;
        }

        return buttonID;

    }

}
