package net.bteuk.liveearthlibrary.OpenWeatherMap.responsedata;

import lombok.Getter;

/**
 * Represents the data in the response from a call to the Current Weather API of open streetmap
 * (api.openweathermap.org/data/2.5/weather)
 */
public class CurrentWeatherData
{
	/** Coordinates of the weather station */
	@Getter
	private Coordinates coord;

	/** The weather conditions
	 * <p> </p>
	 * See <a href="https://openweathermap.org/weather-conditions"> A list of whether conditions and codes</a>
	 */
	@Getter
	private Weather[] weather;

	/** An internal parameter */
	@Getter
	private String base;

	/** The main weather */
	@Getter
	private Main main;

	/** Visibility in meters (max value is 10000) */
	@Getter
	private int visibility;

	/** Wind data */
	@Getter
	private Wind wind;

	/** Cloud data */
	@Getter
	private Clouds cloud;

	/** Recent rainfall */
	@Getter
	private Rain rain;

	/** Recent snowfall */
	@Getter
	private Snow snow;

	/** Time of data calculation (unix, UTC) */
	@Getter
	private long dt;

	/** Miscellaneous data */
	@Getter
	private System sys;

	/** Shift in seconds from UTC */
	@Getter
	private long timezone;

	/** The City ID */
	@Getter
	private int id;

	/** The city name */
	@Getter
	private String name;

	/** An internal parameter */
	@Getter
	private int cod;
}