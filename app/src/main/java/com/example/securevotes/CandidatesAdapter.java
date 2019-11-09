package com.example.securevotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CandidatesAdapter extends RecyclerView.Adapter<CandidatesAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public RadioButton rbView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            rbView = (RadioButton) itemView.findViewById(R.id.rbCandidate);
        }
    }

    private List<Candidate> mCandidates;

    public CandidatesAdapter(ArrayList<Candidate> candidates) {
        mCandidates = candidates;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public CandidatesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.ballot_element, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(CandidatesAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Candidate candidate = mCandidates.get(position);

        // Set item views based on your views and data model
        RadioButton rbCandidate = viewHolder.rbView;
        rbCandidate.setText(candidate.getName());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mCandidates.size();
    }
}
