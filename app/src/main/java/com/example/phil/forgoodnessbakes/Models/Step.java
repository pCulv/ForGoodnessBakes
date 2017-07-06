package com.example.phil.forgoodnessbakes.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Step
{

    private String id;

    private String shortDescription;
    @SerializedName("description")
    @Expose
    private String description;

    private String videoURL;

    private String thumbnailURL;



    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getShortDescription ()
    {
        return shortDescription;
    }

    public void setShortDescription (String shortDescription)
    {
        this.shortDescription = shortDescription;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getVideoURL ()
    {
        return videoURL;
    }

    public void setVideoURL (String videoURL)
    {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL ()
    {
        return thumbnailURL;
    }

    public void setThumbnailURL (String thumbnailURL)
    {
        this.thumbnailURL = thumbnailURL;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", shortDescription = "+shortDescription+", description = "+description+", videoURL = "+videoURL+", thumbnailURL = "+thumbnailURL+"]";
    }



}