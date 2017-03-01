package com.fitbit.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Tracker {

    @SerializedName("distance")
    @Expose
    private Distance_ distance;
    @SerializedName("floors")
    @Expose
    private Floors_ floors;
    @SerializedName("steps")
    @Expose
    private Steps_ steps;

    /**
     * @return The distance
     */
    public Distance_ getDistance() {
        return distance;
    }

    /**
     * @param distance The distance
     */
    public void setDistance(Distance_ distance) {
        this.distance = distance;
    }

    /**
     * @return The floors
     */
    public Floors_ getFloors() {
        return floors;
    }

    /**
     * @param floors The floors
     */
    public void setFloors(Floors_ floors) {
        this.floors = floors;
    }

    /**
     * @return The steps
     */
    public Steps_ getSteps() {
        return steps;
    }

    /**
     * @param steps The steps
     */
    public void setSteps(Steps_ steps) {
        this.steps = steps;
    }

}
