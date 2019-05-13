package com.syd.model;

import java.util.Date;

public class Security {
    private int id;
    private String electricity;
    private String dangerGood;
    private String lockDoor;
    private int dorm;
    private Date date;

    public int getDorm() {
        return dorm;
    }

    public void setDorm(int dorm) {
        this.dorm = dorm;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getElectricity() {
        return electricity;
    }

    public void setElectricity(String electricity) {
        this.electricity = electricity;
    }

    public String getDangerGood() {
        return dangerGood;
    }

    public void setDangerGood(String dangerGood) {
        this.dangerGood = dangerGood;
    }

    public String getLockDoor() {
        return lockDoor;
    }

    public void setLockDoor(String lockDoor) {
        this.lockDoor = lockDoor;
    }
}
