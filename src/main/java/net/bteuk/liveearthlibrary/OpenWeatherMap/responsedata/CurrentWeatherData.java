package net.bteuk.liveearthlibrary.OpenWeatherMap.responsedata;

/**
 * Represents the data in the response from a call to the Current Weather API of open streetmap
 * (api.openweathermap.org/data/2.5/weather)
 */
public class CurrentWeatherData
{
	//Coordinates of the weather station
	private Coordinates coord;

	//The weather conditions
	private Weather[] weather; //(https://openweathermap.org/weather-conditions)

	//An internal parameter
	private String base;

	//The main weather
	private Main main;

	//Visibility in meters (max value is 10000)
	private int visibility;

	//Wind data
	private Wind wind;

	//Cloud data
	private Clouds cloud;

	//Recent rainfall
	private Rain rain;

	//Recent snowfall
	private Snow snow;

	//Time of data calculation (unix, UTC)
	private long dt;

	//Miscellaneous data
	private System sys;

	//Shift in seconds from UTC
	private long timezone;

	//The City ID
	private int id;

	//The city name
	private String name;

	//An internal parameter
	private int cod;

	public Main getMain()
	{
		return main;
	}

}