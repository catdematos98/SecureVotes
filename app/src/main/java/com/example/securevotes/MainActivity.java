package com.example.securevotes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ChainTesting";
    public static ArrayList<VoteBlock> voteChain = new ArrayList<VoteBlock>();
    public static ArrayList<Voter> voters;

    public static HashMap<String,TransactionOutput> UTXOs = new HashMap<String,TransactionOutput>();

    public static int difficulty = 3;
    public static float minimumTransaction = 0.1f;
    public static Transaction genesisTransaction;

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
                        Intent i = new Intent(MainActivity.this, CastingVoteTransaction.class);
                        i.putExtra("voter", voter);
                        startActivity(i);
                    }
                }
            });

        //add our blocks to the blockchain ArrayList:
        Security.addProvider(new BouncyCastleProvider()); //Setup Bouncey castle as a Security Provider


        //isChainValid();

    }
}
