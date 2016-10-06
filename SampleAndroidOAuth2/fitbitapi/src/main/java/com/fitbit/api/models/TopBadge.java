package com.fitbit.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TopBadge {

    @SerializedName("badgeGradientEndColor")
    @Expose
    private String badgeGradientEndColor;
    @SerializedName("badgeGradientStartColor")
    @Expose
    private String badgeGradientStartColor;
    @SerializedName("badgeType")
    @Expose
    private String badgeType;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("cheers")
    @Expose
    private List<Object> cheers = new ArrayList<Object>();
    @SerializedName("dateTime")
    @Expose
    private String dateTime;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("earnedMessage")
    @Expose
    private String earnedMessage;
    @SerializedName("encodedId")
    @Expose
    private String encodedId;
    @SerializedName("image100px")
    @Expose
    private String image100px;
    @SerializedName("image125px")
    @Expose
    private String image125px;
    @SerializedName("image300px")
    @Expose
    private String image300px;
    @SerializedName("image50px")
    @Expose
    private String image50px;
    @SerializedName("image75px")
    @Expose
    private String image75px;
    @SerializedName("marketingDescription")
    @Expose
    private String marketingDescription;
    @SerializedName("mobileDescription")
    @Expose
    private String mobileDescription;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("shareImage640px")
    @Expose
    private String shareImage640px;
    @SerializedName("shareText")
    @Expose
    private String shareText;
    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;
    @SerializedName("shortName")
    @Expose
    private String shortName;
    @SerializedName("timesAchieved")
    @Expose
    private Integer timesAchieved;
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("unit")
    @Expose
    private String unit;

    /**
     * @return The badgeGradientEndColor
     */
    public String getBadgeGradientEndColor() {
        return badgeGradientEndColor;
    }

    /**
     * @param badgeGradientEndColor The badgeGradientEndColor
     */
    public void setBadgeGradientEndColor(String badgeGradientEndColor) {
        this.badgeGradientEndColor = badgeGradientEndColor;
    }

    /**
     * @return The badgeGradientStartColor
     */
    public String getBadgeGradientStartColor() {
        return badgeGradientStartColor;
    }

    /**
     * @param badgeGradientStartColor The badgeGradientStartColor
     */
    public void setBadgeGradientStartColor(String badgeGradientStartColor) {
        this.badgeGradientStartColor = badgeGradientStartColor;
    }

    /**
     * @return The badgeType
     */
    public String getBadgeType() {
        return badgeType;
    }

    /**
     * @param badgeType The badgeType
     */
    public void setBadgeType(String badgeType) {
        this.badgeType = badgeType;
    }

    /**
     * @return The category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category The category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return The cheers
     */
    public List<Object> getCheers() {
        return cheers;
    }

    /**
     * @param cheers The cheers
     */
    public void setCheers(List<Object> cheers) {
        this.cheers = cheers;
    }

    /**
     * @return The dateTime
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * @param dateTime The dateTime
     */
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The earnedMessage
     */
    public String getEarnedMessage() {
        return earnedMessage;
    }

    /**
     * @param earnedMessage The earnedMessage
     */
    public void setEarnedMessage(String earnedMessage) {
        this.earnedMessage = earnedMessage;
    }

    /**
     * @return The encodedId
     */
    public String getEncodedId() {
        return encodedId;
    }

    /**
     * @param encodedId The encodedId
     */
    public void setEncodedId(String encodedId) {
        this.encodedId = encodedId;
    }

    /**
     * @return The image100px
     */
    public String getImage100px() {
        return image100px;
    }

    /**
     * @param image100px The image100px
     */
    public void setImage100px(String image100px) {
        this.image100px = image100px;
    }

    /**
     * @return The image125px
     */
    public String getImage125px() {
        return image125px;
    }

    /**
     * @param image125px The image125px
     */
    public void setImage125px(String image125px) {
        this.image125px = image125px;
    }

    /**
     * @return The image300px
     */
    public String getImage300px() {
        return image300px;
    }

    /**
     * @param image300px The image300px
     */
    public void setImage300px(String image300px) {
        this.image300px = image300px;
    }

    /**
     * @return The image50px
     */
    public String getImage50px() {
        return image50px;
    }

    /**
     * @param image50px The image50px
     */
    public void setImage50px(String image50px) {
        this.image50px = image50px;
    }

    /**
     * @return The image75px
     */
    public String getImage75px() {
        return image75px;
    }

    /**
     * @param image75px The image75px
     */
    public void setImage75px(String image75px) {
        this.image75px = image75px;
    }

    /**
     * @return The marketingDescription
     */
    public String getMarketingDescription() {
        return marketingDescription;
    }

    /**
     * @param marketingDescription The marketingDescription
     */
    public void setMarketingDescription(String marketingDescription) {
        this.marketingDescription = marketingDescription;
    }

    /**
     * @return The mobileDescription
     */
    public String getMobileDescription() {
        return mobileDescription;
    }

    /**
     * @param mobileDescription The mobileDescription
     */
    public void setMobileDescription(String mobileDescription) {
        this.mobileDescription = mobileDescription;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The shareImage640px
     */
    public String getShareImage640px() {
        return shareImage640px;
    }

    /**
     * @param shareImage640px The shareImage640px
     */
    public void setShareImage640px(String shareImage640px) {
        this.shareImage640px = shareImage640px;
    }

    /**
     * @return The shareText
     */
    public String getShareText() {
        return shareText;
    }

    /**
     * @param shareText The shareText
     */
    public void setShareText(String shareText) {
        this.shareText = shareText;
    }

    /**
     * @return The shortDescription
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * @param shortDescription The shortDescription
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * @return The shortName
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * @param shortName The shortName
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * @return The timesAchieved
     */
    public Integer getTimesAchieved() {
        return timesAchieved;
    }

    /**
     * @param timesAchieved The timesAchieved
     */
    public void setTimesAchieved(Integer timesAchieved) {
        this.timesAchieved = timesAchieved;
    }

    /**
     * @return The value
     */
    public Integer getValue() {
        return value;
    }

    /**
     * @param value The value
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * @return The unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit The unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

}
