package com.syd.model;

public class Breakdown {
    private int id;
    private String breakContent;
    private int status;
    private int examine;

    public int getExamine() {
        return examine;
    }

    public void setExamine(int examine) {
        this.examine = examine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBreakContent() {
        return breakContent;
    }

    public void setBreakContent(String breakContent) {
        this.breakContent = breakContent;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
