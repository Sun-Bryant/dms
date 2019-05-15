package com.syd.model;

import java.util.Date;

public class Punish {
    private int id;
    private int dorm;
    private Date date;
    private String punishContent;

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

    public String getPunishContent() {
        return punishContent;
    }
    public void setPunishContent(String punishContent) {
        this.punishContent = punishContent;
    }
}
