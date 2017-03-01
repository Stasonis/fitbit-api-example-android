package com.fitbit.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class WeightLogs {

    @SerializedName("weight")
    @Expose
    private List<Weight> weight = new ArrayList<Weight>();

    /**
     * @return The weight
     */
    public List<Weight> getWeight() {
        return weight;
    }

    /**
     * @param weight The weight
     */
    public void setWeight(List<Weight> weight) {
        this.weight = weight;
    }

}
