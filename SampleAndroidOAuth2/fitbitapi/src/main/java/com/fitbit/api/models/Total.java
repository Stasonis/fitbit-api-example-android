package com.fitbit.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Total {

    @SerializedName("distance")
    @Expose
    private Distance distance;
    @SerializedName("floors")
    @Expose
    private Floors floors;
    @SerializedName("steps")
    @Expose
    private Steps steps;

    /**
     * @return The distance
     */
    public Distance getDistance() {
        return distance;
    }

    /**
     * @param distance The distance
     */
    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    /**
     * @return The floors
     */
    public Floors getFloors() {
        return floors;
    }

    /**
     * @param floors The floors
     */
    public void setFloors(Floors floors) {
        this.floors = floors;
    }

    /**
     * @return The steps
     */
    public Steps getSteps() {
        return steps;
    }

    /**
     * @param steps The steps
     */
    public void setSteps(Steps steps) {
        this.steps = steps;
    }

}
