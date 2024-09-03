package net.bteuk.liveearthlibrary.OpenWeatherMap.responsedata;

/**
 * Represents miscellaneous response data
 */
public class System
{
    //Internal parameter
    private int type;

    //Internal parameter
    private long id;

    //Message
    private String message;

    //Country code
    private String country;

    //Sunrise and sunset, unix, UTC
    private long sunrise;
    private long sunset;
}
