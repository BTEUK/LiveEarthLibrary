package net.bteuk.liveearthlibrary.OpenWeatherMap.responsedata;

import lombok.Getter;

/**
 * Represents the wind data in a response
 */
public class Wind
{
    /** Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour */
    @Getter
    private double speed;

    /** Wind direction, degrees (meteorological) */
    @Getter
    private int deg;

    /** Wind gust. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour */
    @Getter
    private double gust;
}
