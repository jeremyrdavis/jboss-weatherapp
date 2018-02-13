package com.redhat.jboss.datagrid.examples.weatherapp.services;

import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.infinispan.Cache;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

import com.redhat.jboss.datagrid.examples.weatherapp.models.LocationWeather;

@ApplicationScoped
public class CachingWeatherService implements WeatherService {

	private final EmbeddedCacheManager cacheManager;

	final private Cache<String, LocationWeather> cache;

	@Inject
	OpenWeatherMapAPIService weatherService;

	public CachingWeatherService() {
		super();
		GlobalConfiguration globalConfig = new GlobalConfigurationBuilder().globalJmxStatistics()
				.allowDuplicateDomains(Boolean.TRUE).build();
		cacheManager = new DefaultCacheManager(globalConfig);
		ConfigurationBuilder config = new ConfigurationBuilder();
		config.expiration().lifespan(5, TimeUnit.SECONDS);
		cacheManager.defineConfiguration("weather", config.build());
		cache = cacheManager.getCache("weather");
	}

	public LocationWeather getWeatherForLocation(String location) {
		LocationWeather weather = cache.get(location);

		if (weather == null) {
			System.out.println(location + " not found in the cache.  Retrieving from OWMAPI.");
			weather = weatherService.getWeatherForLocation(location);
			cache.put(location, weather);
		}
		return weather;
	}

}
