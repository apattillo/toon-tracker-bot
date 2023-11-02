package com.pattillo.entity;

public class ToonInfo {

    private String ownerId;
    private String ownerName;
    private String toonName;
    private String toonClass;
    private String toonLevel;

    public ToonInfo() {
    }

    public ToonInfo(String ownerId, String ownerName, String toonName, String toonClass, String toonLevel) {
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.toonName = toonName;
        this.toonClass = toonClass;
        this.toonLevel = toonLevel;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getToonName() {
        return toonName;
    }

    public void setToonName(String toonName) {
        this.toonName = toonName;
    }

    public String getToonClass() {
        return toonClass;
    }

    public void setToonClass(String toonClass) {
        this.toonClass = toonClass;
    }

    public String getToonLevel() {
        return toonLevel;
    }

    public void setToonLevel(String toonLevel) {
        this.toonLevel = toonLevel;
    }
}
