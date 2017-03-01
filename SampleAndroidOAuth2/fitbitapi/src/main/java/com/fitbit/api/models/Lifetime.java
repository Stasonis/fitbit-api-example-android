package com.fitbit.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Lifetime {

    @SerializedName("total")
    @Expose
    private Total_ total;
    @SerializedName("tracker")
    @Expose
    private Tracker_ tracker;

    /**
     * @return The total
     */
    public Total_ getTotal() {
        return total;
    }

    /**
     * @param total The total
     */
    public void setTotal(Total_ total) {
        this.total = total;
    }

    /**
     * @return The tracker
     */
    public Tracker_ getTracker() {
        return tracker;
    }

    /**
     * @param tracker The tracker
     */
    public void setTracker(Tracker_ tracker) {
        this.tracker = tracker;
    }

}
