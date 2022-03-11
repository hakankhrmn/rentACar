package com.turkcell.rentACar.entities.abstracts;

public enum CityEnum {

	ANKARA("Ankara"),
	ISTANBUL("İstanbul"),
	BALIKESIR("Balıkesir"),
	ESKISEHIR("Eskişehir");
	
	private String cityName;

	private CityEnum(String cityName) {
		this.cityName = cityName;
	}

	public String getCityName() {
		return cityName;
	}
	
}
