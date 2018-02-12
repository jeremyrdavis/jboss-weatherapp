package com.redhat.jboss.datagrid.examples.weatherapp.services;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.enterprise.context.ApplicationScoped;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.redhat.jboss.datagrid.examples.weatherapp.models.LocationWeather;

@ApplicationScoped
public class OpenWeatherMapAPIService implements WeatherService{

	final private static String OWM_BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
	private DocumentBuilder db;
	private final String apiKey = "b3b81df33fb859482428a8c4c668c2f2";

	public OpenWeatherMapAPIService() {
		super();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
		}
	}

	private Document fetchData(String location) {
		HttpURLConnection conn = null;
		try {
			String query = String.format("%s?q=%s&mode=xml&units=metric&APPID=%s", OWM_BASE_URL,
					URLEncoder.encode(location.replaceAll(" ", ""), "UTF-8"), apiKey);
			URL url = new URL(query);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/xml");
			if (conn.getResponseCode() != 200) {
				throw new Exception();
			}
			return db.parse(conn.getInputStream());
		} catch (Exception e) {
			return null;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	public LocationWeather getWeatherForLocation(String location) {
		
		if (location == null) {
			throw new RuntimeException("'location' is null");
		} else {
			System.out.println("fetching weather for '" + location + "'");
			Document dom = fetchData(location);
			if (dom == null) {
				throw new RuntimeException("'dom' is null");
			}
			Element current = (Element) dom.getElementsByTagName("current").item(0);
			Element city = (Element) current.getElementsByTagName("city").item(0);
			Element temperature = (Element) current.getElementsByTagName("temperature").item(0);
			Element weather = (Element) current.getElementsByTagName("weather").item(0);
			String[] split = location.split(",");
			return new LocationWeather(city.getAttribute("name").toString(), Float.parseFloat(temperature.getAttribute("value")),
					weather.getAttribute("value"), split[1].trim());
		}
	}
}
