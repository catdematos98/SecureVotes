package com.example.securevotes;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CastingVoteTransaction extends AppCompatActivity {

    ArrayList<Candidate> candidateList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.casting_vote_activity);

        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvCandidates);

        // Initialize contacts
        candidateList = new ArrayList<Candidate>();
        candidateList.add(new Candidate("Alex"));
        candidateList.add(new Candidate("Purvi"));
        candidateList.add(new Candidate("Shani"));
        // Create adapter passing in the sample user data
        CandidatesAdapter adapter = new CandidatesAdapter(candidateList);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        // That's all!
    }
}
