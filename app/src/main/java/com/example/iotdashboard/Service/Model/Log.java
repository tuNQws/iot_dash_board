package com.example.iotdashboard.Service.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Log {
    @SerializedName("log_id")
    @Expose
    private String id;
    @SerializedName("device")
    @Expose
    private String deviceName;
    @SerializedName("value")
    @Expose
    private int deviceValue;
    @SerializedName("sensor_type")
    @Expose
    private String sensorType;
    @SerializedName("log_time")
    @Expose
    private String logTime;
    @SerializedName("log_day")
    @Expose
    private String logDay;
    @SerializedName("ip")
    @Expose
    private String logIp;

    @SerializedName("unit")
    @Expose
    private String unit;

    public Log(String id, String deviceName, int deviceValue, String sensorType, String logTime, String logDay, String logIp, String unit) {
        this.id = id;
        this.deviceName = deviceName;
        this.deviceValue = deviceValue;
        this.sensorType = sensorType;
        this.logTime = logTime;
        this.logDay = logDay;
        this.logIp = logIp;
        this.unit = unit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getDeviceValue() {
        return deviceValue;
    }

    public void setDeviceValue(int deviceValue) {
        this.deviceValue = deviceValue;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public String getLogDay() {
        return logDay;
    }

    public void setLogDay(String logDay) {
        this.logDay = logDay;
    }

    public String getLogIp() {
        return logIp;
    }

    public void setLogIp(String logIp) {
        this.logIp = logIp;
    }
}
