package com.example.phil.forgoodnessbakes.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phil.forgoodnessbakes.Models.Step;
import com.example.phil.forgoodnessbakes.NetworkUtils.JSONKeys;
import com.example.phil.forgoodnessbakes.R;
import com.example.phil.forgoodnessbakes.StepDetailActivity;

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

    public class StepsViewHolder extends RecyclerView.ViewHolder  {
        TextView shortDescriptionTextView;


        public StepsViewHolder(final View itemView) {
            super(itemView);

            shortDescriptionTextView = (TextView) itemView.findViewById(R.id.shortDescription_tv);
            shortDescriptionTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Step stepPosition = mSteps.get(getAdapterPosition());
                    Context context = itemView.getContext();


                        Intent userClick = new Intent(context, StepDetailActivity.class);
                        userClick.putExtra(JSONKeys.KEY_DESCRIPTION, stepPosition.getDescription());
                        userClick.putExtra(JSONKeys.KEY_VIDEO_URL, stepPosition.getVideoURL());
                        userClick.putExtra(JSONKeys.KEY_THUMBNAIL_URL, stepPosition.getThumbnailURL());
                        context.startActivity(userClick);

                }
            });

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
    /*
   Logic to determine if the app is running on a tablet.
    */
    private boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources()
                .getConfiguration()
                .screenLayout & Configuration
                .SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE);
        boolean large = ((context.getResources()
                .getConfiguration()
                .screenLayout & Configuration
                .SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }
}
