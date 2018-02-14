package com.redhat.jboss.datagrid.examples.weatherapp.caching;

import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.infinispan.cdi.embedded.ConfigureCache;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

public class WeatherCacheConfig {

	@WeatherCache
	@ConfigureCache("weather-cache")
	@Produces
	public Configuration weatherCache() {
		return new ConfigurationBuilder().expiration().lifespan(5, TimeUnit.SECONDS).build();
	}
	
    /**
     * Creates {@link org.infinispan.manager.EmbeddedCacheManager} with turned on duplicated JMX domains.
     *
     * @param configuration Weather Cache configuration.
     * @return EmbeddedCacheManager for Weather cache.
     */
    @Produces
    @ApplicationScoped
    public EmbeddedCacheManager weatherCacheManager(@WeatherCache Configuration configuration) {
        GlobalConfiguration globalConfiguration = new GlobalConfigurationBuilder().globalJmxStatistics().allowDuplicateDomains(true).build();
        return new DefaultCacheManager(globalConfiguration, configuration);
    }

}
