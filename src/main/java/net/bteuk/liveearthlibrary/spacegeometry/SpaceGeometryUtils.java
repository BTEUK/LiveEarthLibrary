package net.bteuk.liveearthlibrary.spacegeometry;

import java.util.Calendar;
import java.util.TimeZone;

import static java.lang.Math.floorDiv;

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

    /**
     * Converts a given data and time to a sidereal time at a given longitude
     * @param dateTime The time and date at which to calculate the sidereal time.
     * @param longitude The longitude (East positive) of the location to get the sidereal time for
     */
    public static Angle calculateLocalSiderealTime(Calendar dateTime, Angle longitude)
    {
        dateTime.setTimeZone(TimeZone.getTimeZone("GMT"));

        int iYear = dateTime.get(Calendar.YEAR);
        int iMonth = dateTime.get(Calendar.MONTH) + 1;
        if (iMonth == 1 || iMonth == 2)
        {
            iYear = iYear - 1;
            iMonth = iMonth + 12;
        }

        //Calculate leap years - i.e the number of feb 29ths
        int A = floorDiv(iYear, 100);
        int B = 2 - A + floorDiv(A, 4);

        double julianDateAtMidnightJD0 = (int) (365.25 * (iYear + 4716)) + (int) (30.6001 * (iMonth + 1)) + dateTime.get(Calendar.DAY_OF_MONTH) + B - 1524.5;

        //Current GMT time
        Angle gmtTimeAngle = new Angle(dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), dateTime.get(Calendar.SECOND), AngleUnit.Time);

        double julianDateNowJDut = julianDateAtMidnightJD0 +  gmtTimeAngle.inDegrees()/360.0;
        double julianDateNowJDtt = julianDateNowJDut;

        double julianDaysSince2000Dtt = julianDateNowJDtt - 2451545.0f;
        double julianDaysSince2000Dut = julianDateAtMidnightJD0 - 2451545.0f;

        double dCenturiesSince2000T = (julianDaysSince2000Dtt)/36525f;

        double GMTSiderealTime = (6.697375 + 0.065707485828 * julianDaysSince2000Dut + 1.0027379 * ((float) (gmtTimeAngle.inDegrees()/15f)) + 0.0854103 * dCenturiesSince2000T + 0.0000258 * dCenturiesSince2000T * dCenturiesSince2000T) % 24;
        Angle greenwichSiderealTime = new Angle(GMTSiderealTime, 0, 0, AngleUnit.Time);

        return new Angle(greenwichSiderealTime.inRadians()+longitude.inRadians());
    }
}
