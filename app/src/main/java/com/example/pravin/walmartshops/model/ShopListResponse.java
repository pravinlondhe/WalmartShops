package com.example.pravin.walmartshops.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShopListResponse {

    @SerializedName("no")
    @Expose
    private int no;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("coordinates")
    @Expose
    private List<Double> coordinates = null;
    @SerializedName("streetAddress")
    @Expose
    private String streetAddress;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("stateProvCode")
    @Expose
    private String stateProvCode;
    @SerializedName("zip")
    @Expose
    private String zip;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("sundayOpen")
    @Expose
    private boolean sundayOpen;
    @SerializedName("timezone")
    @Expose
    private String timezone;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvCode() {
        return stateProvCode;
    }

    public void setStateProvCode(String stateProvCode) {
        this.stateProvCode = stateProvCode;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isSundayOpen() {
        return sundayOpen;
    }

    public void setSundayOpen(boolean sundayOpen) {
        this.sundayOpen = sundayOpen;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

}
