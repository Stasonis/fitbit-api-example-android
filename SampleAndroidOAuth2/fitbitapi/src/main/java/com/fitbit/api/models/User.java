package com.fitbit.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class User {

    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("autoStrideEnabled")
    @Expose
    private Boolean autoStrideEnabled;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("avatar150")
    @Expose
    private String avatar150;
    @SerializedName("averageDailySteps")
    @Expose
    private Integer averageDailySteps;
    @SerializedName("clockTimeDisplayFormat")
    @Expose
    private String clockTimeDisplayFormat;
    @SerializedName("corporate")
    @Expose
    private Boolean corporate;
    @SerializedName("corporateAdmin")
    @Expose
    private Boolean corporateAdmin;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("dateOfBirth")
    @Expose
    private String dateOfBirth;
    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("distanceUnit")
    @Expose
    private String distanceUnit;
    @SerializedName("encodedId")
    @Expose
    private String encodedId;
    @SerializedName("features")
    @Expose
    private Features features;
    @SerializedName("foodsLocale")
    @Expose
    private String foodsLocale;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("glucoseUnit")
    @Expose
    private String glucoseUnit;
    @SerializedName("height")
    @Expose
    private Double height;
    @SerializedName("heightUnit")
    @Expose
    private String heightUnit;
    @SerializedName("locale")
    @Expose
    private String locale;
    @SerializedName("memberSince")
    @Expose
    private String memberSince;
    @SerializedName("offsetFromUTCMillis")
    @Expose
    private Integer offsetFromUTCMillis;
    @SerializedName("startDayOfWeek")
    @Expose
    private String startDayOfWeek;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("strideLengthRunning")
    @Expose
    private Double strideLengthRunning;
    @SerializedName("strideLengthRunningType")
    @Expose
    private String strideLengthRunningType;
    @SerializedName("strideLengthWalking")
    @Expose
    private Double strideLengthWalking;
    @SerializedName("strideLengthWalkingType")
    @Expose
    private String strideLengthWalkingType;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("topBadges")
    @Expose
    private List<TopBadge> topBadges = new ArrayList<TopBadge>();
    @SerializedName("waterUnit")
    @Expose
    private String waterUnit;
    @SerializedName("waterUnitName")
    @Expose
    private String waterUnitName;
    @SerializedName("weight")
    @Expose
    private Double weight;
    @SerializedName("weightUnit")
    @Expose
    private String weightUnit;

    /**
     * @return The age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age The age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return The autoStrideEnabled
     */
    public Boolean getAutoStrideEnabled() {
        return autoStrideEnabled;
    }

    /**
     * @param autoStrideEnabled The autoStrideEnabled
     */
    public void setAutoStrideEnabled(Boolean autoStrideEnabled) {
        this.autoStrideEnabled = autoStrideEnabled;
    }

    /**
     * @return The avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar The avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * @return The avatar150
     */
    public String getAvatar150() {
        return avatar150;
    }

    /**
     * @param avatar150 The avatar150
     */
    public void setAvatar150(String avatar150) {
        this.avatar150 = avatar150;
    }

    /**
     * @return The averageDailySteps
     */
    public Integer getAverageDailySteps() {
        return averageDailySteps;
    }

    /**
     * @param averageDailySteps The averageDailySteps
     */
    public void setAverageDailySteps(Integer averageDailySteps) {
        this.averageDailySteps = averageDailySteps;
    }

    /**
     * @return The clockTimeDisplayFormat
     */
    public String getClockTimeDisplayFormat() {
        return clockTimeDisplayFormat;
    }

    /**
     * @param clockTimeDisplayFormat The clockTimeDisplayFormat
     */
    public void setClockTimeDisplayFormat(String clockTimeDisplayFormat) {
        this.clockTimeDisplayFormat = clockTimeDisplayFormat;
    }

    /**
     * @return The corporate
     */
    public Boolean getCorporate() {
        return corporate;
    }

    /**
     * @param corporate The corporate
     */
    public void setCorporate(Boolean corporate) {
        this.corporate = corporate;
    }

    /**
     * @return The corporateAdmin
     */
    public Boolean getCorporateAdmin() {
        return corporateAdmin;
    }

    /**
     * @param corporateAdmin The corporateAdmin
     */
    public void setCorporateAdmin(Boolean corporateAdmin) {
        this.corporateAdmin = corporateAdmin;
    }

    /**
     * @return The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return The dateOfBirth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth The dateOfBirth
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return The displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName The displayName
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return The distanceUnit
     */
    public String getDistanceUnit() {
        return distanceUnit;
    }

    /**
     * @param distanceUnit The distanceUnit
     */
    public void setDistanceUnit(String distanceUnit) {
        this.distanceUnit = distanceUnit;
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
     * @return The features
     */
    public Features getFeatures() {
        return features;
    }

    /**
     * @param features The features
     */
    public void setFeatures(Features features) {
        this.features = features;
    }

    /**
     * @return The foodsLocale
     */
    public String getFoodsLocale() {
        return foodsLocale;
    }

    /**
     * @param foodsLocale The foodsLocale
     */
    public void setFoodsLocale(String foodsLocale) {
        this.foodsLocale = foodsLocale;
    }

    /**
     * @return The fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName The fullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return The gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender The gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return The glucoseUnit
     */
    public String getGlucoseUnit() {
        return glucoseUnit;
    }

    /**
     * @param glucoseUnit The glucoseUnit
     */
    public void setGlucoseUnit(String glucoseUnit) {
        this.glucoseUnit = glucoseUnit;
    }

    /**
     * @return The height
     */
    public Double getHeight() {
        return height;
    }

    /**
     * @param height The height
     */
    public void setHeight(Double height) {
        this.height = height;
    }

    /**
     * @return The heightUnit
     */
    public String getHeightUnit() {
        return heightUnit;
    }

    /**
     * @param heightUnit The heightUnit
     */
    public void setHeightUnit(String heightUnit) {
        this.heightUnit = heightUnit;
    }

    /**
     * @return The locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * @param locale The locale
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * @return The memberSince
     */
    public String getMemberSince() {
        return memberSince;
    }

    /**
     * @param memberSince The memberSince
     */
    public void setMemberSince(String memberSince) {
        this.memberSince = memberSince;
    }

    /**
     * @return The offsetFromUTCMillis
     */
    public Integer getOffsetFromUTCMillis() {
        return offsetFromUTCMillis;
    }

    /**
     * @param offsetFromUTCMillis The offsetFromUTCMillis
     */
    public void setOffsetFromUTCMillis(Integer offsetFromUTCMillis) {
        this.offsetFromUTCMillis = offsetFromUTCMillis;
    }

    /**
     * @return The startDayOfWeek
     */
    public String getStartDayOfWeek() {
        return startDayOfWeek;
    }

    /**
     * @param startDayOfWeek The startDayOfWeek
     */
    public void setStartDayOfWeek(String startDayOfWeek) {
        this.startDayOfWeek = startDayOfWeek;
    }

    /**
     * @return The state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state The state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return The strideLengthRunning
     */
    public Double getStrideLengthRunning() {
        return strideLengthRunning;
    }

    /**
     * @param strideLengthRunning The strideLengthRunning
     */
    public void setStrideLengthRunning(Double strideLengthRunning) {
        this.strideLengthRunning = strideLengthRunning;
    }

    /**
     * @return The strideLengthRunningType
     */
    public String getStrideLengthRunningType() {
        return strideLengthRunningType;
    }

    /**
     * @param strideLengthRunningType The strideLengthRunningType
     */
    public void setStrideLengthRunningType(String strideLengthRunningType) {
        this.strideLengthRunningType = strideLengthRunningType;
    }

    /**
     * @return The strideLengthWalking
     */
    public Double getStrideLengthWalking() {
        return strideLengthWalking;
    }

    /**
     * @param strideLengthWalking The strideLengthWalking
     */
    public void setStrideLengthWalking(Double strideLengthWalking) {
        this.strideLengthWalking = strideLengthWalking;
    }

    /**
     * @return The strideLengthWalkingType
     */
    public String getStrideLengthWalkingType() {
        return strideLengthWalkingType;
    }

    /**
     * @param strideLengthWalkingType The strideLengthWalkingType
     */
    public void setStrideLengthWalkingType(String strideLengthWalkingType) {
        this.strideLengthWalkingType = strideLengthWalkingType;
    }

    /**
     * @return The timezone
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     * @param timezone The timezone
     */
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    /**
     * @return The topBadges
     */
    public List<TopBadge> getTopBadges() {
        return topBadges;
    }

    /**
     * @param topBadges The topBadges
     */
    public void setTopBadges(List<TopBadge> topBadges) {
        this.topBadges = topBadges;
    }

    /**
     * @return The waterUnit
     */
    public String getWaterUnit() {
        return waterUnit;
    }

    /**
     * @param waterUnit The waterUnit
     */
    public void setWaterUnit(String waterUnit) {
        this.waterUnit = waterUnit;
    }

    /**
     * @return The waterUnitName
     */
    public String getWaterUnitName() {
        return waterUnitName;
    }

    /**
     * @param waterUnitName The waterUnitName
     */
    public void setWaterUnitName(String waterUnitName) {
        this.waterUnitName = waterUnitName;
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

    /**
     * @return The weightUnit
     */
    public String getWeightUnit() {
        return weightUnit;
    }

    /**
     * @param weightUnit The weightUnit
     */
    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

}
