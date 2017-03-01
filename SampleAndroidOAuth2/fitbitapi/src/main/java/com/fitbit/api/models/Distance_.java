package com.fitbit.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Distance_ {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("value")
    @Expose
    private Double value;

    /**
     * @return The date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return The value
     */
    public Double getValue() {
        return value;
    }

    /**
     * @param value The value
     */
    public void setValue(Double value) {
        this.value = value;
    }

}
