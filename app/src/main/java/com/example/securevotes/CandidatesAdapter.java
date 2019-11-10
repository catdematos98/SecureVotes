package com.example.securevotes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CandidatesAdapter extends RecyclerView.Adapter<CandidatesAdapter.ViewHolder> {

    private static final String TAG = "CandidatesAdapter";
    public static ArrayList<String> selected;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public CheckBox rbView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            rbView = (CheckBox) itemView.findViewById(R.id.rbCandidate);
        }
    }

    private List<String> mCandidates;

    public CandidatesAdapter(ArrayList<String> candidates) {
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
        Log.i(TAG, String.valueOf(position));
        String candidate = mCandidates.get(position);
        Log.i(TAG, candidate);
        selected = new ArrayList<>();

        // Set item views based on your views and data model
        final CheckBox rbCandidate = viewHolder.rbView;
        rbCandidate.setText(candidate);
        rbCandidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbCandidate.isChecked()){
                    selected.add(rbCandidate.getText().toString());
                }
                else{
                    selected.remove(rbCandidate.getText().toString());
                }

            }
        });
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mCandidates.size();
    }

    public static ArrayList<String> getSelected(){
        return selected;
    }
}
