package com.example.phil.forgoodnessbakes;

import android.support.v7.widget.RecyclerView;

import com.example.phil.forgoodnessbakes.models.Step;

import java.util.ArrayList;


public interface FragmentInterface {
    void handleClick(Step stepModel, String videoUrl, String description,
                     RecyclerView.ViewHolder viewHolder, ArrayList<Step> steps);

}
