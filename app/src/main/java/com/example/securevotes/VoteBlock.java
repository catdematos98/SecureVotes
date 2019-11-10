package com.example.securevotes;

import java.util.ArrayList;
import java.util.Date;

public class VoteBlock {

    public String hash;
    public String prevHash;
    private Candidate candidate;
    private long timeStamp;
    public ArrayList<Transaction> transactions = new ArrayList<Transaction>(); //our data will be a simple message
    public int nonce;


    public VoteBlock(Candidate c, String prevHash) {
        this.candidate = c;
        this.prevHash = prevHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash(){
        String calculatedHash = StringHelper.applySHA256( prevHash + candidate + Long.toString(timeStamp));
        return calculatedHash;
    }


    //Add transactions to this block
    public boolean addTransaction(Transaction transaction) {
        //process transaction and check if valid, unless block is genesis block then ignore.
        if(transaction == null) return false;
        if((prevHash != "0")) {
            if((transaction.processTransaction() != true)) {
                System.out.println("Transaction failed to process. Discarded.");
                return false;
            }
        }
        transactions.add(transaction);
        System.out.println("Transaction Successfully added to Block");
        return true;
    }
}
