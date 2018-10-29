package com.strath.mydairyfarm;

public class Vaccination {

    private String id,vaccinationdate, cowname, type, description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVaccinationdate() {
        return vaccinationdate;
    }

    public void setVaccinationdate(String vaccinationdate) {
        this.vaccinationdate = vaccinationdate;
    }

    public String getCowname() {
        return cowname;
    }

    public void setCowname(String cowname) {
        this.cowname = cowname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
