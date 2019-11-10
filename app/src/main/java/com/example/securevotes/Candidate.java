package com.example.securevotes;

import java.util.ArrayList;

public class Candidate {
    public static String name;
    public static Wallet wallet;


    public Candidate(String name) {
        this.name = name;
        this.wallet = new Wallet();
    }

    public String getName(){
        return name;
    }
}
