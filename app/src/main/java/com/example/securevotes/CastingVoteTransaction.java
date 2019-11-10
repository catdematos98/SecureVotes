package com.example.securevotes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CastingVoteTransaction extends AppCompatActivity {

    ArrayList<Candidate> candidateList;
    Candidate chosenCandidate;
    private final String TAG = "CastingVoteTransaction";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.casting_vote_activity);

        final RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvCandidates);
        Button submitVote = (Button) findViewById(R.id.btVote);

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
                    //Todo - verify transaction
                    Intent i = new Intent(CastingVoteTransaction.this, CompletedVote.class);
                    startActivity(i);
                }
            }
        });
    }
}
