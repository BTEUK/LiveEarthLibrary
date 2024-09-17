package net.bteuk.liveearthlibrary.spacegeometry;

/**
 * A class to represent a set of spherical coordinates
 */
public class SphericalCoordinates
{
    /**
     * Declination, Altitude, Latitude
     */
    public Angle latitude;

    /**
     * RA, Azimuth, Longitude
     */
    public Angle longitude;

    /**
     * The 'r' component in spherical coordinates. (Distance from the origin).
     */
    public double distance;

    /**
     *
     * @param latitude The latitude angle (Declination, Altitude, Latitude)
     * @param longitude The longitude angle (RA, Azimuth, Longitude)
     */
    public SphericalCoordinates(Angle latitude, Angle longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     *
     * @param latitude The latitude angle (Declination, Altitude, Latitude)
     * @param longitude The longitude angle (RA, Azimuth, Longitude)
     * @param dDistance The distance in meters that the object is from the origin
     */
    public SphericalCoordinates(Angle latitude, Angle longitude, double dDistance)
    {
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = dDistance;
    }
}
