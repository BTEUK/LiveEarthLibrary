package net.bteuk.liveearthlibrary.OpenWeatherMap.responsedata;

import lombok.Getter;

/**
 * Represents the amount of rain that has fallen recently
 */
public class Rain
{
    /** (where available) Rain volume for the last 1 hour, mm. */
    @Getter
    private int oneHour;

    /** (where available) Rain volume for the last 3 hours, mm. */
    @Getter
    private int threeHour;
}
