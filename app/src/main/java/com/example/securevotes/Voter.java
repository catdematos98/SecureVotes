package com.example.securevotes;

import android.util.Log;

import java.util.Random;

public class Voter {

    private final String TAG = "Voter";
    private String voterID;
    public String licenseID;
    public String state;
    public boolean ballotCreated;

    public Voter( String voterID, String licenseID, String state) {
        if(validVoter()){
            this.voterID = voterID;
            this.licenseID = licenseID;
            this.state = state;
            this.ballotCreated = false;
        }
        else{
            Log.i(TAG, "Voter not valid");
        }
    }


    public boolean validVoter(){
        /*randomly decides if user is valid*/
        Random rand = new Random();
        return rand.nextBoolean();
    }


}
