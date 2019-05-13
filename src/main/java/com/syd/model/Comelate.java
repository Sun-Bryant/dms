package com.syd.model;

import java.util.Date;

public class Comelate {
    private int id;
    private String studentNo;
    private String studentName;
    private String studentClass;
    private Date latetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public Date getLatetime() {
        return latetime;
    }

    public void setLatetime(Date latetime) {
        this.latetime = latetime;
    }
}
