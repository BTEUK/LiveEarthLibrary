package net.bteuk.liveearthlibrary.OpenWeatherMap.responsedata;

import lombok.Getter;

/**
 * Represents the main weather data as returned
 */
public class Main
{
    /** Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit */
    @Getter
    private double temp;

    /** Temperature. This temperature parameter accounts for the human perception of weather. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit */
    @Getter
    private double feels_like;

    /** Atmospheric pressure on the sea level, hPa */
    @Getter
    private int pressure;

    /** Humidity in % */
    @Getter
    private int humidity;

    /** Minimum temperature at the moment. This is minimal currently observed temperature (within large megalopolises and urban areas). Units as temp*/
    @Getter
    private double temp_min;

    /** Maximum temperature at the moment. This is maximal currently observed temperature (within large megalopolises and urban areas). Units as temp */
    @Getter
    private double temp_max;

    /** Atmospheric pressure on the sea level, hPa */
    @Getter
    private int sea_level;

    /** Atmospheric pressure on the ground level, hPa */
    @Getter
    private int grnd_level;
}
