package net.bteuk.liveearthlibrary.OpenWeatherMap.responsedata;

/**
 * Represents the "Weather" conditions
 */
public class Weather
{
	//Weather condition id (https://openweathermap.org/weather-conditions)
	private int id;

	//Group of weather parameters (Rain, Snow, Clouds etc.)
	private String main;

	//Weather condition within the group
	private String description;

	//The weather icon id
	private int icon;
}