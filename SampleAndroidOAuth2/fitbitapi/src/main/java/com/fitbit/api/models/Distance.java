package com.fitbit.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Distance {

    @SerializedName("activity")
    @Expose
    private String activity;
    @SerializedName("distance")
    @Expose
    private Double distance;

    /**
     * @return The activity
     */
    public String getActivity() {
        return activity;
    }

    /**
     * @param activity The activity
     */
    public void setActivity(String activity) {
        this.activity = activity;
    }

    /**
     * @return The distance
     */
    public Double getDistance() {
        return distance;
    }

    /**
     * @param distance The distance
     */
    public void setDistance(Double distance) {
        this.distance = distance;
    }

}
