package com.example.securevotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ChainTesting";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        VoteBlock genesisBlock = new VoteBlock("Chris", "0");
        VoteBlock vote2 = new VoteBlock("Chris", genesisBlock.hash);
        VoteBlock vote3 = new VoteBlock("Chris", vote2.hash);

        System.out.println(genesisBlock.hash);
        Log.i(TAG, genesisBlock.hash);
        Log.i(TAG, vote2.hash);
        Log.i(TAG, vote3.hash);
    }
}
