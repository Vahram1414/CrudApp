package com.vagram.crudapp.model;

public class Specialities {
    public String getSpeciality1() {
        return speciality1;
    }

    public void setSpeciality1(String speciality1) {
        this.speciality1 = speciality1;
    }

    public String getSpeciality2() {
        return speciality2;
    }

    public void setSpeciality2(String speciality2) {
        this.speciality2 = speciality2;
    }

    public String getSpeciality3() {
        return speciality3;
    }

    public void setSpeciality3(String speciality3) {
        this.speciality3 = speciality3;
    }

    private String speciality1 = "Java Developer";
    private String speciality2 = "DevOps";
    private String speciality3 = "Data Sientists";

    public Specialities() {

    }

    public Specialities(String speciality1, String speciality2, String speciality3) {
        this.speciality1 = speciality1;
        this.speciality2 = speciality2;
        this.speciality3 = speciality3;
    }
}
