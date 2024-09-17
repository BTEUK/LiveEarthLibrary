package net.bteuk.liveearthlibrary.astronomy;

import net.bteuk.liveearthlibrary.spacegeometry.Angle;
import net.bteuk.liveearthlibrary.spacegeometry.AngleUnit;
import net.bteuk.liveearthlibrary.spacegeometry.SpaceGeometryUtils;
import net.bteuk.liveearthlibrary.spacegeometry.SphericalCoordinates;
import org.orekit.bodies.CelestialBody;
import org.orekit.bodies.CelestialBodyFactory;
import org.orekit.time.*;
import java.util.Calendar;

/**
 * Represents the moon
 */
public class Moon extends SolarSystemObject
{
    private static final CelestialBody OrekitMoon = CelestialBodyFactory.getMoon();

    //Cycle shit

    //Moon light angle

    public Moon(AbsoluteDate dateTime)
    {
        super(OrekitMoon, dateTime);
    }

//    /**
//     * Calculates the equatorial coordinates of the moon given a time and place on Earth
//     * @param time The time to calculate the position of the moon at
//     * @return Spherical coordinates in the equatorial frame
//     */
//    public static SphericalCoordinates calculatePositionInSky(Calendar time)
//    {
//        //Julian time
//        double dJulianDayNow  = SpaceGeometryUtils.julianDateNow(time);
//        double dJulianCenturiesSince2000 = SpaceGeometryUtils.julianCenturiesSince2000(dJulianDayNow);
//
//        //Mean longitude
//        double dMeanLongitude = 218.3164591 + 481267.88134236 * dJulianCenturiesSince2000;
//        Angle meanLongitude = new Angle(dMeanLongitude, 0, 0, AngleUnit.Degrees);
//
//        //Moon elongation
//        double dAngleElongationInDegrees = 297.8502042 + 445267.1115168 * dJulianCenturiesSince2000;
//        Angle moonElongation = new Angle(dAngleElongationInDegrees, 0, 0, AngleUnit.Degrees);
//
//        //Anomaly of sun
//        double dMeanAnomalyOfSun = 357.5291092 + 35999.0502909 * dJulianCenturiesSince2000;
//        Angle anomalyOfSun = new Angle(dMeanAnomalyOfSun, 0, 0, AngleUnit.Degrees);
//
//        //Anomaly of moon
//        double dMeanAnomalyOfMoon = 134.9634114 + 477198.8676313 * dJulianCenturiesSince2000;
//        Angle anomalyOfMoon = new Angle(dMeanAnomalyOfMoon, 0, 0, AngleUnit.Degrees);
//
//        //Argument of latitude
//        double dArgumentOfLatitude = 93.2720993 + 483202.0175273 * dJulianCenturiesSince2000;
//        Angle argumentOfLatitude = new Angle(dArgumentOfLatitude, 0, 0, AngleUnit.Degrees);
//
//        //Ecliptic longitude
//        double dEclipticLongitude = meanLongitude.inDegrees();//+ variations ;
//        System.out.println("Setting ecliptic longitude to: " +249.624);
//        dEclipticLongitude = 249.624;
//
//        Angle trueEclipticLongitude = new Angle(dEclipticLongitude, 0, 0, AngleUnit.Degrees);
//        System.out.println("Current ecliptic longitude: " +dEclipticLongitude);
//
//
//        double dEclipticLatitude = -4.699;//+ variations ;
//        Angle trueEclipticLatitude = new Angle(dEclipticLatitude, 0, 0, AngleUnit.Degrees);
//
//        Angle obliquityOfEcliptic = SpaceGeometryUtils.obliquityOfOrbit(dJulianCenturiesSince2000);
//
//        double RA = Math.atan2((Math.sin(trueEclipticLongitude.inRadians())*Math.cos(obliquityOfEcliptic.inRadians()) - (Math.tan(trueEclipticLatitude.inRadians())*Math.sin(obliquityOfEcliptic.inRadians()))),
//                                                                Math.cos(trueEclipticLongitude.inRadians()));
//
//        double DEC = Math.asin(Math.sin(trueEclipticLatitude.inRadians())*Math.cos(obliquityOfEcliptic.inRadians()) + Math.cos(trueEclipticLatitude.inRadians())*Math.sin(obliquityOfEcliptic.inRadians())*Math.sin(trueEclipticLongitude.inRadians()));
//
//
//        return new SphericalCoordinates(new Angle(DEC, true), new Angle(RA));
//    }
}
