package com.changersassignment.domain.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BreedData implements Serializable {
    @SerializedName("bred_for")
    public String bred_for;
    @SerializedName("breed_group")
    public String breed_group;
    @SerializedName("id")
    public String id;
    @SerializedName("life_span")
    public String life_span;
    @SerializedName("name")
    public String name;
    @SerializedName("origin")
    public String origin;
    @SerializedName("reference_image_id")
    public String reference_image_id;
    @SerializedName("image")
    public ImageData image;

}
