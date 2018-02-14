package com.redhat.jboss.datagrid.examples.weatherapp.services;

import javax.cache.annotation.CacheResult;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.inject.Named;

import com.redhat.jboss.datagrid.examples.weatherapp.models.LocationWeather;

@Stateful @Named("cachingWeatherService")
public class CachingWeatherService implements WeatherService {

	@Inject
	OpenWeatherMapAPIService weatherService;
	
    @CacheResult(cacheName = "weather-cache")
	public LocationWeather getWeatherForLocation(String location) {
    		System.out.println("not found in cache");
    		return weatherService.getWeatherForLocation(location);
	}

}
