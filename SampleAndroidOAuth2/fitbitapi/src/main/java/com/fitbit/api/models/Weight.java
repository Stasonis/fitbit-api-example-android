package com.fitbit.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Weight {

    @SerializedName("bmi")
    @Expose
    private Double bmi;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("fat")
    @Expose
    private Double fat;
    @SerializedName("logId")
    @Expose
    private Long logId;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("weight")
    @Expose
    private Double weight;

    /**
     * @return The bmi
     */
    public Double getBmi() {
        return bmi;
    }

    /**
     * @param bmi The bmi
     */
    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }

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
     * @return The fat
     */
    public Double getFat() {
        return fat;
    }

    /**
     * @param fat The fat
     */
    public void setFat(Double fat) {
        this.fat = fat;
    }

    /**
     * @return The logId
     */
    public Long getLogId() {
        return logId;
    }

    /**
     * @param logId The logId
     */
    public void setLogId(Long logId) {
        this.logId = logId;
    }

    /**
     * @return The source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source The source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return The time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time The time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return The weight
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * @param weight The weight
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }


    private final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public Date getDateTime() {
        try {
            return DATE_TIME_FORMAT.parse(String.format(Locale.ENGLISH, "%s %s", getDate(), getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

}
