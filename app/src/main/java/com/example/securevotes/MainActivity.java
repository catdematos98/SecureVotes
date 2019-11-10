package com.example.securevotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ChainTesting";
    public static ArrayList<VoteBlock> voteChain = new ArrayList<VoteBlock>();
    private ArrayList<Voter> voters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        voters = new ArrayList<>();

        EditText licenseET = (EditText)findViewById(R.id.etLicense);
        EditText stateET = (EditText)findViewById(R.id.etState);
        EditText voterIdEt = (EditText)findViewById(R.id.etVoterId);
        Button registerBT = (Button)findViewById(R.id.btRegister);

        final String license = licenseET.getText().toString();
        final String state = stateET.getText().toString();
        final String voterID = voterIdEt.getText().toString();

        registerBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Voter voter = new Voter(voterID, license, state);
                if(voters.contains(voter)){
                    Log.i(TAG, "User already voted");
                    return;
                }
                else{
                    voters.add(voter);
                }
                Intent i = new Intent(MainActivity.this, CastingVoteTransaction.class);
                startActivity(i);
            }
        });




        voteChain.add(new VoteBlock("Chris", "0"));
        voteChain.add(new VoteBlock("Chris", voteChain.get(voteChain.size()-1).hash));
        voteChain.add(new VoteBlock("Chris", voteChain.get(voteChain.size()-1).hash));

        //Todo - Gson library
        for(VoteBlock vote : voteChain){
            Log.i(TAG, vote.hash);
        }
    }
}
