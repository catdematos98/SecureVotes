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
/*
    public static Boolean isChainValid() {
        VoteBlock currentBlock;
        VoteBlock previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');
        HashMap<String,TransactionOutput> tempUTXOs = new HashMap<String,TransactionOutput>(); //a temporary working list of unspent transactions at a given block state.
        tempUTXOs.put(genesisTransaction.outputs.get(0).id, genesisTransaction.outputs.get(0));

        //loop through blockchain to check hashes:
        for(int i=1; i < blockchain.size(); i++) {

            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            //compare registered hash and calculated hash:
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
               Log.i("Main", "#Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if(!previousBlock.hash.equals(currentBlock.prevHash) ) {
               Log.i("Main", "#Previous Hashes not equal");
                return false;
            }
            //check if hash is solved
            if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
               Log.i("Main", "#This block hasn't been mined");
                return false;
            }

            //loop thru blockchains transactions:
            TransactionOutput tempOutput;
            for(int t=0; t <currentBlock.transactions.size(); t++) {
                Transaction currentTransaction = currentBlock.transactions.get(t);

                if(!currentTransaction.verifiySignature()) {
                   Log.i("Main", "#Signature on Transaction(" + t + ") is Invalid");
                    return false;
                }
                if(currentTransaction.getInputsValue() != currentTransaction.getOutputsValue()) {
                   Log.i("Main", "#Inputs are note equal to outputs on Transaction(" + t + ")");
                    return false;
                }

                for(TransactionInput input: currentTransaction.inputs) {
                    tempOutput = tempUTXOs.get(input.transactionOutputId);

                    if(tempOutput == null) {
                       Log.i("Main", "#Referenced input on Transaction(" + t + ") is Missing");
                        return false;
                    }

                    if(input.UTXO.value != tempOutput.value) {
                       Log.i("Main", "#Referenced input Transaction(" + t + ") value is Invalid");
                        return false;
                    }

                    tempUTXOs.remove(input.transactionOutputId);
                }

                for(TransactionOutput output: currentTransaction.outputs) {
                    tempUTXOs.put(output.id, output);
                }

                if( currentTransaction.outputs.get(0).reciepient != currentTransaction.reciepient) {
                   Log.i("Main", "#Transaction(" + t + ") output reciepient is not who it should be");
                    return false;
                }
                if( currentTransaction.outputs.get(1).reciepient != currentTransaction.sender) {
                   Log.i("Main", "#Transaction(" + t + ") output 'change' is not sender.");
                    return false;
                }

            }

        }
       Log.i("Main", "Blockchain is valid");
        return true;
    }
*/
}
