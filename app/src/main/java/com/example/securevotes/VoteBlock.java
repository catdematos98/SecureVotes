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
    }
}
