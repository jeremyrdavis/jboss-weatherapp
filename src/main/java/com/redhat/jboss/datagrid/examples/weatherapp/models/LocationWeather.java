package com.redhat.jboss.datagrid.examples.weatherapp.models;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LocationWeather implements Serializable {
	final float temperature;
	final String conditions;
	final String country;

	public LocationWeather(float temperature, String conditions, String country) {
		this.temperature = temperature;
		this.conditions = conditions;
		this.country = country;
	}

	public float getTemperature() {
		return temperature;
	}

	public String getConditions() {
		return conditions;
	}

	public String getCountry() {
		return country;
	}

	// @Override
	// public String toString() {
	// return String.format("Temperature: %.1f° C, Conditions: %s", temperature,
	// conditions);
	// }

}
