package com.example.securevotes;

import android.util.Log;

import java.util.List;

public class Ballot {

    List<String> candidates;
    Election election;
    Voter voter;
    Boolean ballotCast;

    public static final String TAG = "Ballot";
    public Ballot(List<String> candidates, Voter voter, Election election) {
        if ( validBallot(voter)){
            this.candidates = candidates;
            this.election = election;
            this.voter = voter;
            this.ballotCast = false;
        }

    }

    private boolean validBallot(Voter voter) {
        if(!voter.ballotCreated){
            Log.i(TAG, "Ballot not already created");
            return true;
        }
        else{
            Log.i(TAG, "Ballot already created, no longer valid");
            return false;
        }
    }
}
