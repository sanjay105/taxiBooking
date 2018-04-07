package com.dbs.cabservices.cabservices.models;

import java.io.Serializable;

public class Cab implements Serializable{

    private String id;
    private String lon;
    private String lat;
    private String taxiNo;
    private String taxiType;
    private String carBrand;

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getTaxiType() {

        return taxiType;
    }

    public void setTaxiType(String taxiType) {
        this.taxiType = taxiType;
    }

    public String getTaxiNo() {

        return taxiNo;
    }

    public void setTaxiNo(String taxiNo) {
        this.taxiNo = taxiNo;
    }

    public String getLat() {

        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {

        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
