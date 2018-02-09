package com.redhat.jboss.datagrid.examples.weatherapp.services;

import com.redhat.jboss.datagrid.examples.weatherapp.models.LocationWeather;

public interface WeatherService {

	public LocationWeather getWeatherForLocation(String location);
	
}
