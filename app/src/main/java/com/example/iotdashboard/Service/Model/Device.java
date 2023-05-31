package com.example.iotdashboard.Service.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Device {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("last_update")
    @Expose
    private String lastUpdate;

    @SerializedName("img")
    @Expose
    private String image;
    @SerializedName("status")
    @Expose
    private String status;


    public Device(String name, String lastUpdate, String image, String status) {
        this.name = name;
        this.lastUpdate = lastUpdate;
        this.image = image;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
