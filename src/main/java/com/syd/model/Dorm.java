package com.syd.model;

public class Dorm {
    private int dorm;
    private int capacity;
    private double utilities;//水电费

    public int getDorm() {
        return dorm;
    }

    public void setDorm(int dorm) {
        this.dorm = dorm;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getUtilities() {
        return utilities;
    }

    public void setUtilities(double utilities) {
        this.utilities = utilities;
    }
}
