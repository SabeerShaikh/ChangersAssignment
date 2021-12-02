package com.changersassignment.domain.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ImageData implements Serializable {
    @SerializedName("height")
    public String height;
    @SerializedName("url")
    public String url;
    @SerializedName("id")
    public String id;
    @SerializedName("width")
    public String width;


}
