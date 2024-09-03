package net.bteuk.liveearthlibrary.OpenWeatherMap.responsedata;

/**
 * Represents the main weather data as returned
 */
public class Main
{
    //Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit
    private double temp;

    //Temperature. This temperature parameter accounts for the human perception of weather. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit
    private double feels_like;

    //Atmospheric pressure on the sea level, hPa
    private int pressure;

    //Humidity in %
    private int humidity;

    //Minimum temperature at the moment. This is minimal currently observed temperature (within large megalopolises and urban areas)
    private double temp_min;

    //Maximum temperature at the moment. This is maximal currently observed temperature (within large megalopolises and urban areas)
    private double temp_max;

    //Units for these temperatures are as normal temp

    //Atmospheric pressure on the sea level, hPa
    private int sea_level;

    //Atmospheric pressure on the ground level, hPa
    private int grnd_level;

    public double getTemp() {
        return temp;
    }
}
