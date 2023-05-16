package com.example.iotdashboard.Service.Model;

public class Device {
    private String id;

    private String name;

    private String lastUpdate;

    private String image;

    public Device() {
    }

    public Device(String name, String lastUpdate, String image) {
        this.name = name;
        this.lastUpdate = lastUpdate;
        this.image = image;
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
}
