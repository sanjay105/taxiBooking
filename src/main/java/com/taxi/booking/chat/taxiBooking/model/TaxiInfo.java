package com.taxi.booking.chat.taxiBooking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "taxi_info")
public class TaxiInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLat() {
		return Lat;
	}
	public void setLat(String lat) {
		Lat = lat;
	}
	public String getLon() {
		return Lon;
	}
	public void setLon(String lon) {
		Lon = lon;
	}
	public String getTaxiNo() {
		return TaxiNo;
	}
	public void setTaxiNo(String taxiNo) {
		TaxiNo = taxiNo;
	}
	public String getTaxiType() {
		return TaxiType;
	}
	public void setTaxiType(String taxiType) {
		TaxiType = taxiType;
	}
	public String getCarBrand() {
		return CarBrand;
	}
	public void setCarBrand(String carBrand) {
		CarBrand = carBrand;
	}
	private String Lat;
	private String Lon;
	private String TaxiNo;
	private String TaxiType;
	private String CarBrand;
	
}
