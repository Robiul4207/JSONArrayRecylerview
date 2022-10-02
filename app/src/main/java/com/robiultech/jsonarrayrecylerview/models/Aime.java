package com.robiultech.jsonarrayrecylerview.models;

public class Aime {

    String name,email,phone,district,imgUrl;

    public Aime() {
    }

    public Aime(String name, String email, String phone, String district, String imgUrl) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.district = district;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getDistrict() {
        return district;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
