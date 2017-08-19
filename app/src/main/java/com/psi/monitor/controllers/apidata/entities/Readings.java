package com.psi.monitor.controllers.apidata.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hamdhanywijaya@gmail.com on 8/16/17.
 */

public class Readings {
    @SerializedName("o3_sub_index")
    @Expose
    private ReadingItem o3SubIndex;
    @SerializedName("pm10_twenty_four_hourly")
    @Expose
    private ReadingItem pm10TwentyFourHourly;
    @SerializedName("pm10_sub_index")
    @Expose
    private ReadingItem pm10SubIndex;
    @SerializedName("co_sub_index")
    @Expose
    private ReadingItem coSubIndex;
    @SerializedName("pm25_twenty_four_hourly")
    @Expose
    private ReadingItem pm25TwentyFourHourly;
    @SerializedName("so2_sub_index")
    @Expose
    private ReadingItem so2SubIndex;
    @SerializedName("co_eight_hour_max")
    @Expose
    private ReadingItem coEightHourMax;
    @SerializedName("no2_one_hour_max")
    @Expose
    private ReadingItem no2OneHourMax;
    @SerializedName("so2_twenty_four_hourly")
    @Expose
    private ReadingItem so2TwentyFourHourly;
    @SerializedName("pm25_sub_index")
    @Expose
    private ReadingItem pm25SubIndex;
    @SerializedName("psi_twenty_four_hourly")
    @Expose
    private ReadingItem psiTwentyFourHourly;
    @SerializedName("o3_eight_hour_max")
    @Expose
    private ReadingItem o3EightHourMax;

    public ReadingItem getO3SubIndex() {
        return o3SubIndex;
    }

    public void setO3SubIndex(ReadingItem o3SubIndex) {
        this.o3SubIndex = o3SubIndex;
    }

    public ReadingItem getPm10TwentyFourHourly() {
        return pm10TwentyFourHourly;
    }

    public void setPm10TwentyFourHourly(ReadingItem pm10TwentyFourHourly) {
        this.pm10TwentyFourHourly = pm10TwentyFourHourly;
    }

    public ReadingItem getPm10SubIndex() {
        return pm10SubIndex;
    }

    public void setPm10SubIndex(ReadingItem pm10SubIndex) {
        this.pm10SubIndex = pm10SubIndex;
    }

    public ReadingItem getCoSubIndex() {
        return coSubIndex;
    }

    public void setCoSubIndex(ReadingItem coSubIndex) {
        this.coSubIndex = coSubIndex;
    }

    public ReadingItem getPm25TwentyFourHourly() {
        return pm25TwentyFourHourly;
    }

    public void setPm25TwentyFourHourly(ReadingItem pm25TwentyFourHourly) {
        this.pm25TwentyFourHourly = pm25TwentyFourHourly;
    }

    public ReadingItem getSo2SubIndex() {
        return so2SubIndex;
    }

    public void setSo2SubIndex(ReadingItem so2SubIndex) {
        this.so2SubIndex = so2SubIndex;
    }

    public ReadingItem getCoEightHourMax() {
        return coEightHourMax;
    }

    public void setCoEightHourMax(ReadingItem coEightHourMax) {
        this.coEightHourMax = coEightHourMax;
    }

    public ReadingItem getNo2OneHourMax() {
        return no2OneHourMax;
    }

    public void setNo2OneHourMax(ReadingItem no2OneHourMax) {
        this.no2OneHourMax = no2OneHourMax;
    }

    public ReadingItem getSo2TwentyFourHourly() {
        return so2TwentyFourHourly;
    }

    public void setSo2TwentyFourHourly(ReadingItem so2TwentyFourHourly) {
        this.so2TwentyFourHourly = so2TwentyFourHourly;
    }

    public ReadingItem getPm25SubIndex() {
        return pm25SubIndex;
    }

    public void setPm25SubIndex(ReadingItem pm25SubIndex) {
        this.pm25SubIndex = pm25SubIndex;
    }

    public ReadingItem getPsiTwentyFourHourly() {
        return psiTwentyFourHourly;
    }

    public void setPsiTwentyFourHourly(ReadingItem psiTwentyFourHourly) {
        this.psiTwentyFourHourly = psiTwentyFourHourly;
    }

    public ReadingItem getO3EightHourMax() {
        return o3EightHourMax;
    }

    public void setO3EightHourMax(ReadingItem o3EightHourMax) {
        this.o3EightHourMax = o3EightHourMax;
    }
}
