package net.bteuk.liveearthlibrary.spacegeometry;

/**
 * A set of utils for space geometry
 */
public class SpaceGeometryUtils
{
    /**
     * Calculates the position of a celestial body on an observers horizon based on the body's equatorial coordinates
     * and the observers geographical coordinates and sidereal time
     * @param equatorialCoordinates Coordinates of the celestial body in equatorial coordinates, in radians
     * @param observerCoordinates Coordinates of the Earth observer in geographical coordinates, in radians
     * @param siderealTime The sidereal time of the observer
     * @return A set of spherical coordinates in radians of the position of the object to the observer
     */
    public static SphericalCoordinates equatorialToHorizontal(SphericalCoordinates equatorialCoordinates, SphericalCoordinates observerCoordinates, Angle siderealTime)
    {
        double RA = equatorialCoordinates.longitude.inRadians();
        double DEC = equatorialCoordinates.latitude.inRadians();

        double observerLatitude = observerCoordinates.latitude.inRadians();

        //Comparison of sidereal to RA
        double hourAngle = siderealTime.inRadians()-RA;
        if (hourAngle < 0)
            hourAngle = hourAngle+(2*Math.PI);

        //Calculates the azimuth

        //I comment this out because we use atan2 instead of tan. I leave it here because it illustrates the mathematical operation
/*        double tanAzimuth =                                (Math.sin(hourAngle)) /
                    //-----------------------------------------------------------------------------------------------------------
                    ((Math.cos(hourAngle) * Math.sin(observerLatitude)) - (Math.tan(DEC) * Math.cos(observerLatitude))) ;
          double dAzimuth = Math.atan(tanAzimuth);
*/
        double dAzimuth = Math.atan2((Math.sin(hourAngle)), ((Math.cos(hourAngle) * Math.sin(observerLatitude)) - (Math.tan(DEC) * Math.cos(observerLatitude))));
        //Makes the 0 angle on the plane be at north by adding PI
        Angle azimuth = new Angle(dAzimuth + Math.PI);

        //Calculates the altitude
        double sinAltitude = (Math.sin(observerLatitude) * Math.sin(DEC)) + (Math.cos(observerLatitude) * Math.cos(DEC) * Math.cos(hourAngle));
        double dAltitude = Math.asin(sinAltitude);
        Angle altitude = new Angle(dAltitude);

        SphericalCoordinates horizontalCoordinates = new SphericalCoordinates(altitude, azimuth);
        return horizontalCoordinates;
    }
}
