package com.fitbit.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Tracker_ {

    @SerializedName("activeScore")
    @Expose
    private Integer activeScore;
    @SerializedName("caloriesOut")
    @Expose
    private Integer caloriesOut;
    @SerializedName("distance")
    @Expose
    private Double distance;
    @SerializedName("floors")
    @Expose
    private Integer floors;
    @SerializedName("steps")
    @Expose
    private Integer steps;

    /**
     * @return The activeScore
     */
    public Integer getActiveScore() {
        return activeScore;
    }

    /**
     * @param activeScore The activeScore
     */
    public void setActiveScore(Integer activeScore) {
        this.activeScore = activeScore;
    }

    /**
     * @return The caloriesOut
     */
    public Integer getCaloriesOut() {
        return caloriesOut;
    }

    /**
     * @param caloriesOut The caloriesOut
     */
    public void setCaloriesOut(Integer caloriesOut) {
        this.caloriesOut = caloriesOut;
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

    /**
     * @return The floors
     */
    public Integer getFloors() {
        return floors;
    }

    /**
     * @param floors The floors
     */
    public void setFloors(Integer floors) {
        this.floors = floors;
    }

    /**
     * @return The steps
     */
    public Integer getSteps() {
        return steps;
    }

    /**
     * @param steps The steps
     */
    public void setSteps(Integer steps) {
        this.steps = steps;
    }

}
