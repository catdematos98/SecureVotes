package com.example.securevotes;

import android.util.Log;

import java.io.Serializable;
import java.util.Random;

import static com.example.securevotes.MainActivity.voteChain;

public class Voter implements Serializable {
    private static final long serialVersionUID = 5177222050535318633L;

    private final String TAG = "Voter";
    public String voterID;
    public String licenseID;
    public String state;
    public boolean ballotCreated;
    public Wallet wallet;
    Wallet coinbase = new Wallet();
    public static Transaction genesisTransaction;

    public Voter( String voterID, String licenseID, String state) {
        if(validVoter()){
            this.voterID = voterID;
            this.licenseID = licenseID;
            this.state = state;
            this.ballotCreated = false;
            this.wallet = new Wallet();

            genesisTransaction = new Transaction(coinbase.publicKey, this.wallet.publicKey, 1f, null);
            genesisTransaction.generateSignature(coinbase.privateKey);	 //manually sign the genesis transaction
            genesisTransaction.transactionId = "0"; //manually set the transaction id
            genesisTransaction.outputs.add(new TransactionOutput(genesisTransaction.reciepient, genesisTransaction.value, genesisTransaction.transactionId)); //manually add the Transactions Output
            MainActivity.UTXOs.put(genesisTransaction.outputs.get(0).id, genesisTransaction.outputs.get(0)); //its important to store our first transaction in the UTXOs list.
            System.out.println("Creating and Mining Genesis block... ");
            VoteBlock genesis = new VoteBlock(null, "0");
            genesis.addTransaction(genesisTransaction);
            voteChain.add(genesis);
            System.out.println("\nThis balance is: " + this.wallet.getBalance());
        }
        else{
            Log.i(TAG, "Voter not valid");
        }
    }


    public boolean validVoter(){
        /*randomly decides if user is valid*/
        Random rand = new Random();
        boolean valid =  rand.nextBoolean();
       // return valid && !MainActivity.voters.contains(voterID);
        return true;
    }

    public void vote(Candidate c){
        VoteBlock block1 = new VoteBlock(c, voteChain.get(voteChain.size()-1).hash);
        System.out.println("\nThis balance is: " + this.wallet.getBalance());
        System.out.println("\nThis voter is Attempting to vote for ..." + c.getName());
        block1.addTransaction(this.wallet.castVote(c.wallet.publicKey, 1f));
        voteChain.add(block1);
    }



}
