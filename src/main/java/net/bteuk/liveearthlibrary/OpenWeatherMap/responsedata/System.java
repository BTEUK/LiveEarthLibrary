package net.bteuk.liveearthlibrary.OpenWeatherMap.responsedata;

import lombok.Getter;

/**
 * Represents miscellaneous response data
 */
public class System
{
    /** Internal parameter */
    @Getter
    private int type;

    /** Internal parameter */
    @Getter
    private long id;

    /** Message */
    @Getter
    private String message;

    /** Country code */
    @Getter
    private String country;

    /** Sunrise, unix, UTC */
    @Getter
    private long sunrise;

    /** Sunset, unix, UTC */
    @Getter
    private long sunset;
}
