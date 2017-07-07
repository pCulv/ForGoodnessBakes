package com.example.phil.forgoodnessbakes.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phil.forgoodnessbakes.Adapters.StepsAdapter;
import com.example.phil.forgoodnessbakes.Models.Step;
import com.example.phil.forgoodnessbakes.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NutellaFragment extends Fragment {
    StepsAdapter stepsAdapter;
    @BindView(R.id.nutella_steps_rv)
    RecyclerView stepsRecyclerView;
    private ArrayList<Step> mSteps = new ArrayList<>();

    onStepClickedListener mOnStepClickedListener;

    public interface onStepClickedListener {
        void onStepSelected();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnStepClickedListener = (onStepClickedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
            + " must implement OnImageClickListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nutella, container, false);
        ButterKnife.bind(this, view);


        return view;

    }

}
