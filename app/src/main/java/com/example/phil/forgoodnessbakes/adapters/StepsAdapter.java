package com.example.phil.forgoodnessbakes.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phil.forgoodnessbakes.DetailActivity;
import com.example.phil.forgoodnessbakes.FragmentInterface;
import com.example.phil.forgoodnessbakes.R;
import com.example.phil.forgoodnessbakes.models.RecipeModel;
import com.example.phil.forgoodnessbakes.models.Step;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;


public class StepsAdapter extends
        RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {
    private Context mContext;
    private ArrayList<Step> mSteps = new ArrayList<>();
    private RecipeModel mRecipe = new RecipeModel();
    private FragmentInterface listener;

    private static final java.lang.String TAG = DetailActivity.class.getSimpleName();



    public StepsAdapter(Context context, ArrayList<Step> steps, FragmentInterface listener, RecipeModel recipe) {
        this.mContext = context;
        this.mSteps = steps;
        this.listener = listener;
        this.mRecipe = recipe;
    }

    public class StepsViewHolder extends RecyclerView.ViewHolder {
        TextView shortDescriptionTextView;
        ImageView thumbnail;

        public StepsViewHolder(final View itemView) {
            super(itemView);

            shortDescriptionTextView = (TextView) itemView.findViewById(R.id.shortDescription_tv);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);


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
    public void onBindViewHolder(final StepsAdapter.StepsViewHolder holder, final int position) {

        final Step step = mSteps.get(position);
        final String videoUrl = step.getVideoURL();
        final String description = step.getDescription();
        final String thumbnailUrl = step.getThumbnailURL();



        // display short description of recipe
        holder.shortDescriptionTextView.setText(step.getShortDescription());
        //if thumbnail url is empty then do not load an image
        if (Objects.equals(thumbnailUrl, "")) {
            //do nothing
        } else {
            //load image from thumbnail url received from the server
            Picasso.with(mContext)
                    .load(thumbnailUrl)
                    .into(holder.thumbnail);
        }

        holder.shortDescriptionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.handleClick(step, videoUrl, description, holder, mSteps);
                Log.i(TAG, "onClick: " + holder.getAdapterPosition());
            }
        });
    }


    @Override
    public int getItemCount() {
        return mSteps.size();
    }

}
