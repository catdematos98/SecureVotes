package com.example.securevotes;

import android.content.Intent;
import android.content.SyncStatusObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CastingVoteTransaction extends AppCompatActivity {

    ArrayList<String> candidateList;
    Candidate chosenCandidate;
    private final String TAG = "CastingVoteTransaction";
    public static Transaction genesisTransaction;
    Voter voter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.casting_vote_activity);

        voter = (Voter) getIntent().getSerializableExtra("voter");

        final RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvCandidates);
        Button submitVote = (Button) findViewById(R.id.btVote);

        candidateList = new ArrayList<String>(Arrays.<String>asList("Cat","Tom", "Alex"));
//        Candidate c1 = new Candidate("Alex");
//        Candidate c2 = new Candidate("Tom");
//        Candidate c3 = new Candidate("Cat");
//
//        candidateList.add(c1);
//        candidateList.add(c2);
//        candidateList.add(c3);

        // Create adapter passing in the sample user data
        CandidatesAdapter adapter = new CandidatesAdapter(candidateList);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        // That's all!

        submitVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CandidatesAdapter.getSelected().size() > 1){
                    Log.i(TAG, "More than one candidate selected");
                    return;
                }
                else{
                    chosenCandidate = new Candidate(CandidatesAdapter.getSelected().get(0));

                    Log.i(TAG, chosenCandidate.getName());
                    voter.vote(chosenCandidate);
                    //Todo - verify transaction
                    Intent i = new Intent(CastingVoteTransaction.this, CompletedVote.class);
                    startActivity(i);
                }
            }
        });
    }
}
