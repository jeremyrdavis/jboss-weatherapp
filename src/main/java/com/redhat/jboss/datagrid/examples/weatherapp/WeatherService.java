package com.redhat.jboss.datagrid.examples.weatherapp;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.redhat.jboss.datagrid.examples.weatherapp.models.LocationWeather;
import com.redhat.jboss.datagrid.examples.weatherapp.services.OpenWeatherMapAPIService;

@Path("/weather")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class WeatherService {
	
	static final String[] locations = { "Rome, Italy", "Como, Italy", "Basel, Switzerland", "Bern, Switzerland",
			"London, UK", "Ottawa, Canada", "Toronto, Canada", "Lisbon, Portugal", "Porto, Portugal", "Raleigh, USA",
			"Washington, USA" };

	@Inject
	OpenWeatherMapAPIService weatherService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LocationWeather> listAllLocations() {
		List<LocationWeather> results = new ArrayList<LocationWeather>(11);
    		for (String location : locations) {
			results.add(weatherService.getWeatherForLocation(location));
		}
        return results;
    }

}
