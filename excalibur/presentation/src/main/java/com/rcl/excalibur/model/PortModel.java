
package com.rcl.excalibur.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class PortModel {

    @SerializedName("arrivalDate")
    private String mArrivalDate;
    @SerializedName("arrivalTime")
    private Long mArrivalTime;
    @SerializedName("departureDate")
    private String mDepartureDate;
    @SerializedName("departureTime")
    private Long mDepartureTime;
    @SerializedName("portCode")
    private String mPortCode;
    @SerializedName("portName")
    private String mPortName;
    @SerializedName("portType")
    private String mPortType;

    public String getArrivalDate() {
        return mArrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        mArrivalDate = arrivalDate;
    }

    public Long getArrivalTime() {
        return mArrivalTime;
    }

    public void setArrivalTime(Long arrivalTime) {
        mArrivalTime = arrivalTime;
    }

    public String getDepartureDate() {
        return mDepartureDate;
    }

    public void setDepartureDate(String departureDate) {
        mDepartureDate = departureDate;
    }

    public Long getDepartureTime() {
        return mDepartureTime;
    }

    public void setDepartureTime(Long departureTime) {
        mDepartureTime = departureTime;
    }

    public String getPortCode() {
        return mPortCode;
    }

    public void setPortCode(String portCode) {
        mPortCode = portCode;
    }

    public String getPortName() {
        return mPortName;
    }

    public void setPortName(String portName) {
        mPortName = portName;
    }

    public String getPortType() {
        return mPortType;
    }

    public void setPortType(String portType) {
        mPortType = portType;
    }

}
