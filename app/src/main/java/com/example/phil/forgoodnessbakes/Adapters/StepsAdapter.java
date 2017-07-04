package com.example.phil.forgoodnessbakes.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phil.forgoodnessbakes.Models.Step;
import com.example.phil.forgoodnessbakes.R;

import java.util.ArrayList;
import java.util.List;


public class StepsAdapter extends
        RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {

    private Context mContext;
    private List<Step> mSteps = new ArrayList<>();


    public StepsAdapter(Context context, List<Step> steps) {
        this.mContext = context;
        this.mSteps = steps;
    }

    public class StepsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView shortDescriptionTextView;


        public StepsViewHolder(View itemView) {
            super(itemView);

            shortDescriptionTextView = (TextView) itemView.findViewById(R.id.shortDescription_tv);
            shortDescriptionTextView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            // TODO: add intents to open the video and description view of the recipe step

        }
    }

    public StepsAdapter.StepsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        this.mContext = viewGroup.getContext();
        int layoutIdSteps = R.layout.steps_list_item;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View view = inflater.inflate(layoutIdSteps, viewGroup, false);
        StepsViewHolder viewHolder = new StepsViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StepsAdapter.StepsViewHolder holder, int position) {

        final Step step = mSteps.get(position);
        // display short description of recipe in
        holder.shortDescriptionTextView.setText(step.getShortDescription());

    }

    @Override
    public int getItemCount() {

        return mSteps.size();
    }
}