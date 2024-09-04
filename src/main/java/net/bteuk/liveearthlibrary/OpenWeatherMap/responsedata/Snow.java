package net.bteuk.liveearthlibrary.OpenWeatherMap.responsedata;

import lombok.Getter;

/**
 * Represents the amount of snow that has fallen recently
 */
public class Snow
{
    /** (where available) Snow volume for the last 1 hour, mm. */
    @Getter
    private int oneHour;

    /** (where available) Snow volume for the last 3 hours, mm. */
    @Getter
    private int threeHour;
}
