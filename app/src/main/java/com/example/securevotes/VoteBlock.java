package com.example.securevotes;

import java.util.Date;

public class VoteBlock {

    public String hash;
    public String prevHash;
    private String data;
    private long timeStamp;

    public VoteBlock(String data, String prevHash) {
        this.data = data;
        this.prevHash = prevHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash(){
        String calculatedHash = StringHelper.applySHA256( prevHash + data + Long.toString(timeStamp));
        return calculatedHash;
    }
}
