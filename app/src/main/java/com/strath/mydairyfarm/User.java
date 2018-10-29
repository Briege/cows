package com.strath.mydairyfarm;

/**
 * Created by oirere on 19/10/2018.
 */

public class User {

    private String name, email, phone, farmName;

    public User() {

    }

    public User(String name, String email, String phone, String farmName) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.farmName = farmName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }


}
