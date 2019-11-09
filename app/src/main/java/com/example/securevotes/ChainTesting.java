package com.example.securevotes;

import android.util.Log;

public class ChainTesting {
    private static final String TAG = "ChainTesting";

    public static void main(String[] args){
        VoteBlock genesisBlock = new VoteBlock("Chris", "0");
        VoteBlock vote2 = new VoteBlock("Chris", genesisBlock.hash);
        VoteBlock vote3 = new VoteBlock("Chris", vote2.hash);

        System.out.println(genesisBlock.hash);
        Log.i(TAG, genesisBlock.hash);
        Log.i(TAG, vote2.hash);
        Log.i(TAG, vote3.hash);
    }

}
