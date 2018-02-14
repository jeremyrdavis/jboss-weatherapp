package com.redhat.jboss.datagrid.examples.weatherapp.rest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

import com.redhat.jboss.datagrid.examples.weatherapp.models.LocationWeather;
import com.redhat.jboss.datagrid.examples.weatherapp.services.WeatherService;

@Path("/weather")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class WeatherServiceEndpoint {
	
	private static final Logger LOGGER = Logger.getLogger(WeatherServiceEndpoint.class);
	
	static final String[] locations = { "Rome, Italy", "Como, Italy", "Basel, Switzerland", "Bern, Switzerland",
			"London, UK", "Ottawa, Canada", "Toronto, Canada", "Lisbon, Portugal", "Porto, Portugal", "Raleigh, USA",
			"Washington, USA" };

	@Inject
	WeatherService weatherService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LocationWeather> listAllLocations() {
		List<LocationWeather> results = new ArrayList<LocationWeather>(11);
    		for (String location : locations) {
			results.add(weatherService.getWeatherForLocation(location));
		}
        return results;
    }

    @GET
    @Path("/{location}")
    @Produces(MediaType.APPLICATION_JSON)
	public LocationWeather getWeatherForLocation(@PathParam("location") String location) {
    		LOGGER.debug("getWeatherForLocation called for " + location);
		return weatherService.getWeatherForLocation(location);
	}
    

}
