package net.bteuk.liveearthlibrary.OpenWeatherMap.responsedata;

import net.bteuk.liveearthlibrary.spacegeometry.Angle;
import net.bteuk.liveearthlibrary.spacegeometry.AngleUnit;
import net.bteuk.liveearthlibrary.spacegeometry.SphericalCoordinates;

/**
 * Represents a pair of geographical coordinates
 */
public class Coordinates
{
    /** Longitude of the location */
    private double lon;

    /** Latitude of the location */
    private double lat;

    /**
     * Creates a SphericalCoordinates object from the latitude and longitude coordinates
     * @return A SphericalCoordinates object representing these coordinates
     */
    public SphericalCoordinates getCoordinates()
    {
        SphericalCoordinates sphericalCoordinates = new SphericalCoordinates(
                new Angle(lat, 0, 0, AngleUnit.Degrees), new Angle(lon, 0, 0, AngleUnit.Degrees));
        return sphericalCoordinates;
    }
}
