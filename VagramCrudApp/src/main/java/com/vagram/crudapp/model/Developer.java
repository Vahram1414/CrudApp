package com.vagram.crudapp.model;

import java.util.List;

public class Developer {
    private Integer id;
    private String firstName;
    private String lastName;

    private Status status;

    private List<Skill> skills;

    private String spaciality;

    public Developer() {
    }

    public Developer(Integer id, String firstName, String lastName, Status status, List<Skill> skills, String speciality) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.skills = skills;
        this.spaciality = speciality;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public String getSpaciality() {
        return spaciality;
    }

    public void setSpaciality(String spaciality) {
        this.spaciality = spaciality;
    }
}
