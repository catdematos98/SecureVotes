package com.example.securevotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ChainTesting";
    public static ArrayList<VoteBlock> voteChain = new ArrayList<VoteBlock>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        voteChain.add(new VoteBlock("Chris", "0"));
        voteChain.add(new VoteBlock("Chris", voteChain.get(voteChain.size()-1).hash));
        voteChain.add(new VoteBlock("Chris", voteChain.get(voteChain.size()-1).hash));

        //Todo - Gson library
        for(VoteBlock vote : voteChain){
            Log.i(TAG, vote.hash);
        }
    }
}
