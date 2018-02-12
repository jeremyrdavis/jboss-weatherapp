package com.redhat.jboss.datagrid.examples.weatherapp.models;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.w3c.dom.Element;

@XmlRootElement
public class LocationWeather implements Serializable {
	final float temperature;
	final String conditions;
	final String country;
	final String city;

	public LocationWeather(String city, float temperature, String conditions, String country) {
		this.city = city;
		this.temperature = temperature;
		this.conditions = conditions;
		this.country = country;
	}

	public String getCity() {
		return city;
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
