package net.bteuk.liveearthlibrary.OpenWeatherMap.responsedata;

import lombok.Getter;

/**
 * Represents the "Weather" conditions
 */
public class Weather
{
	/** Weather condition id (https://openweathermap.org/weather-conditions) */
	@Getter
	private int id;

	/** Group of weather parameters (Rain, Snow, Clouds etc.) */
	@Getter
	private String main;

	/** Weather condition within the group */
	@Getter
	private String description;

	/** The weather icon id */
	@Getter
	private int icon;
}