package com.example.phil.forgoodnessbakes.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.phil.forgoodnessbakes.DetailActivity;
import com.example.phil.forgoodnessbakes.R;
import com.example.phil.forgoodnessbakes.models.Step;
import com.example.phil.forgoodnessbakes.networkUtils.JSONKeys;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailFragment extends Fragment implements ExoPlayer.EventListener {
    private static final java.lang.String TAG = DetailActivity.class.getSimpleName();
    @BindView(R.id.stepDescription)
    TextView stepDescription;
    @BindView(R.id.playerView)
    SimpleExoPlayerView mPlayerView;
    @BindView(R.id.next_button)
    Button nextButton;
    @BindView(R.id.prev_button)
    Button prevButton;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    private SimpleExoPlayer mExoPlayer;
    private PlaybackStateCompat.Builder mStateBuilder;
    private int position;
    private String mVideoUrl;
    public Step mStepModal;
    private String PLAYER_STATE = "playerState";
    private boolean tabletSize;
    protected long currentPosition;
    private ArrayList<Step> mSteps;
    private static MediaSessionCompat mMediaSession;


    public DetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_step_detail, container, false);
        ButterKnife.bind(this, view);

        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getLong(PLAYER_STATE, 0);

        }
        // if bundle is coming from fragment for two pane layout
        if (getArguments() != null) {

            String description = getArguments().getString(JSONKeys.KEY_DESCRIPTION);
            mVideoUrl = getArguments().getString(JSONKeys.KEY_VIDEO_URL);
            mStepModal = getArguments().getParcelable(JSONKeys.KEY_STEPS);
            stepDescription.setText(description);
            mSteps = getArguments().getParcelableArrayList("steps");
            position = getArguments().getInt("position");

        } else {
            // if bundle is coming from single pane layout
            Intent userClick = getActivity().getIntent();
            Bundle bundle = userClick.getExtras();
            mVideoUrl = bundle.getString(JSONKeys.KEY_VIDEO_URL);
            String description = bundle.getString(JSONKeys.KEY_DESCRIPTION);
            stepDescription.setText(description);
            position = bundle.getInt("position");
            mSteps = bundle.getParcelableArrayList("steps");


        }
        //if step does not have a url attached
        if (Objects.equals(mVideoUrl, "")) {
//            Toast.makeText(this.getActivity(), "No Video For This Step", Toast.LENGTH_SHORT).show();
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, "No Video For This Step", Snackbar.LENGTH_SHORT);
            snackbar.show();
        }


        SimpleExoPlayerView simpleExoPlayerView = new SimpleExoPlayerView(this.getActivity());

        simpleExoPlayerView.setPlayer(mExoPlayer);

        if (position == mSteps.size() - 1) {
            nextButton.setVisibility(View.GONE);
        }
        if (position == 0) {
            prevButton.setVisibility(View.INVISIBLE);
        }

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabletSize = getResources().getBoolean(R.bool.isTablet);
                if (!tabletSize) {
                    /*
                    If app opens on phone, get the next position in the ArrayList<Step>. Intent
                    extras are then passed onto next activity which opens a new DetailActivity
                    populated by the data for the next step in the list.
                     */
                    if (mSteps != null) {
                        position += 1;
                        Step nextStep = mSteps.get(position);

                        Intent nextClick = new Intent(getActivity(), DetailActivity.class);
                        nextClick.putExtra(JSONKeys.KEY_DESCRIPTION, nextStep.getDescription());
                        nextClick.putExtra(JSONKeys.KEY_VIDEO_URL, nextStep.getVideoURL());
                        nextClick.putExtra("steps", mSteps);
                        nextClick.putExtra("next", nextStep);
                        nextClick.putExtra("position", position);

                        startActivity(nextClick);

                    }
                } else {
                          /*
                    If app opens on phone, get the next position in the ArrayList<Step>. Intent
                    extras are then passed onto next activity which opens a new DetailActivity
                    populated by the data for the next step in the list.
                     */
                    if (mSteps != null) {
                        position += 1;
                        Step nextStep = mSteps.get(position);

                        Bundle args = new Bundle();
                        args.putString(JSONKeys.KEY_VIDEO_URL, nextStep.getVideoURL());
                        args.putString(JSONKeys.KEY_DESCRIPTION, nextStep.getDescription());
                        args.putInt("position", position);
                        args.putParcelableArrayList("steps", mSteps);
                        DetailFragment detailActivityFragment = new DetailFragment();
                        detailActivityFragment.setArguments(args);
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.detail_container, detailActivityFragment).commit();

                    }
                }
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabletSize = getResources().getBoolean(R.bool.isTablet);
                if (!tabletSize) {
                    if (mSteps != null) {
                        position -= 1;
                        Step prevStep = mSteps.get(position);

                        Intent nextClick = new Intent(getActivity(), DetailActivity.class);
                        nextClick.putExtra(JSONKeys.KEY_DESCRIPTION, prevStep.getDescription());
                        nextClick.putExtra(JSONKeys.KEY_VIDEO_URL, prevStep.getVideoURL());
                        nextClick.putExtra("steps", mSteps);
                        nextClick.putExtra("previous", prevStep);
                        nextClick.putExtra("position", position);

                        startActivity(nextClick);
                    }
                } else {
                          /*
                    If app opens on phone, get the previous position in the ArrayList<Step>. Intent
                    extras are then passed onto previous activity which opens a new DetailActivity
                    populated by the data for the next step in the list.
                     */
                    if (mSteps != null) {
                        position -= 1;
                        Step nextStep = mSteps.get(position);

                        Bundle args = new Bundle();
                        args.putString(JSONKeys.KEY_VIDEO_URL, nextStep.getVideoURL());
                        args.putString(JSONKeys.KEY_DESCRIPTION, nextStep.getDescription());
                        args.putInt("position", position);
                        args.putParcelableArrayList("steps", mSteps);
                        DetailFragment detailActivityFragment = new DetailFragment();
                        detailActivityFragment.setArguments(args);
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.detail_container, detailActivityFragment).commit();

                    }
                }
            }
        });

        Uri mMediaUri = Uri.parse(mVideoUrl);
        initializeMediaSession();
        initializePlayer(mMediaUri);

        return view;

    }


    private void initializeMediaSession() {

        // Create a MediaSessionCompat.
        mMediaSession = new MediaSessionCompat(this.getActivity(), TAG);

        // Enable callbacks from MediaButtons and TransportControls.
        mMediaSession.setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);

        // Do not let MediaButtons restart the player when the app is not visible.
        mMediaSession.setMediaButtonReceiver(null);

        // Set an initial PlaybackState with ACTION_PLAY, so media buttons can start the player.
        mStateBuilder = new PlaybackStateCompat.Builder()
                .setActions(
                        PlaybackStateCompat.ACTION_PLAY |
                                PlaybackStateCompat.ACTION_PAUSE |
                                PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS |
                                PlaybackStateCompat.ACTION_PLAY_PAUSE);

        mMediaSession.setPlaybackState(mStateBuilder.build());


        // MySessionCallback has methods that handle callbacks from a media controller.
        mMediaSession.setCallback(new MySessionCallback());

        // Start the Media Session since the activity is active.
        mMediaSession.setActive(true);

    }

    private void initializePlayer(Uri mediaUri) {
        if (mExoPlayer == null) {
            // Create an instance of the ExoPlayer.
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory
                    .newSimpleInstance(this.getActivity(), trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);

            mPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);

            // Set the ExoPlayer.EventListener to this activity.
            mExoPlayer.addListener(this);

            // Prepare the MediaSource.
            String userAgent = Util.getUserAgent(this.getActivity(), "ForGoodnessBakes");
            MediaSource mediaSource =
                    new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                            this.getActivity(), userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
            mExoPlayer.seekTo(currentPosition);
        }
    }

    // Release ExoPlayer.

    private void releasePlayer() {
        if (mExoPlayer != null) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
        mMediaSession.setActive(false);
    }



    @Override
    public void onPause() {
        super.onPause();
        pausePlayer();
    }

    @Override
    public void onResume() {
        super.onResume();
        startPlayer();
    }

    private void pausePlayer(){
        if (mExoPlayer != null) {
            mExoPlayer.setPlayWhenReady(false);
            mExoPlayer.getPlaybackState();
        }
    }
    private void startPlayer(){
        if (mExoPlayer != null) {
            mExoPlayer.setPlayWhenReady(true);
            mExoPlayer.getPlaybackState();
        }
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if ((playbackState == ExoPlayer.STATE_READY) && playWhenReady) {
            mStateBuilder.setState(PlaybackStateCompat.STATE_PLAYING,
                    mExoPlayer.getCurrentPosition(), 1f);
        } else if ((playbackState == ExoPlayer.STATE_READY)) {
            mStateBuilder.setState(PlaybackStateCompat.STATE_PAUSED,
                    mExoPlayer.getCurrentPosition(), 1f);
        }
        mMediaSession.setPlaybackState(mStateBuilder.build());

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity() {

    }


    private class MySessionCallback extends MediaSessionCompat.Callback {
        @Override
        public void onPlay() {
            mExoPlayer.setPlayWhenReady(true);
        }

        @Override
        public void onPause() {
            mExoPlayer.setPlayWhenReady(false);
        }

        @Override
        public void onSkipToPrevious() {
            mExoPlayer.seekTo(0);
        }
    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mExoPlayer != null) {
            outState.putLong(PLAYER_STATE, currentPosition);
        }
    }
}
