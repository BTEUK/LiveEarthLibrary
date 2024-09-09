package net.bteuk.liveearthlibrary.spacegeometry;

/**
 * A class representing an angle, containing various conversion methods for that angle
 */
public class Angle
{
    //The angle in radians
    private final double angle;

    /**
     * Allows the angle to be inputted as radians
     */
    public Angle(double angle)
    {
        this.angle = angle;
    }

    /**
     * Allows the angle to be inputted as a time or degrees
     */
    public Angle(double degreeOrHour, double minute, double second, AngleUnit angleUnit)
    {
        double dAngleInDegrees;

        switch (angleUnit)
        {
            case Degrees:
                dAngleInDegrees = degreeOrHour + minute/60.0 + second/3600.0;
                break;
            case Time:
                double dSeconds = (degreeOrHour*3600) + (minute*60.0) + (second);
                dAngleInDegrees = (dSeconds/86400.0)*360.0;
                break;
            default:
                dAngleInDegrees = degreeOrHour;
                break;
        }

        //Converts the prospectiveAngle to radians
        double prospectiveAngle = dAngleInDegrees*(Math.PI/180.0);

        //Trims the angle
        while (prospectiveAngle >= (Math.PI*2f))
        {
            prospectiveAngle = prospectiveAngle - (Math.PI*2f);
        }

        this.angle = prospectiveAngle;
    }

    /**
     * @return The angle in radius
     */
    public double inRadians()
    {
        return this.angle;
    }

    /**
     * Calculates the angle in decimal degrees from the angle in radians
     * @return The angle in decimal degrees
     */
    public double inDegrees()
    {
        return this.angle * (180.0/Math.PI);
    }

    /**
     * Calculates the angle in decimal degrees, and then the angle in degrees, arcminutes and arcseconds
     * @return An array representing the angle in degrees, arcminutes and arcseconds
     */
    public double[] inDegreesMinutesSeconds()
    {
        double dAngleDegrees = this.angle * (180.0/Math.PI);
        int iDegrees = (int) dAngleDegrees;

        double dDegreesRemainder = (dAngleDegrees - iDegrees);
        double dMinutes = dDegreesRemainder*60;
        int iMinutes = (int) dMinutes;

        double dMinuteRemainder = (dMinutes - iMinutes);
        double dSeconds = dMinuteRemainder*60;

        return new double[]{iDegrees, iMinutes, dSeconds};
    }

    /**
     * Calculates the angle in decimal degrees, and then the angle in degrees, arcminutes and arcseconds
     * @return An array representing the angle in degrees, arcminutes and arcseconds
     */
    public String inDegreesMinutesSecondsToString()
    {
        double dAngleDegrees = this.angle * (180.0/Math.PI);
        int iDegrees = (int) dAngleDegrees;

        double dDegreesRemainder = (dAngleDegrees - iDegrees);
        double dMinutes = dDegreesRemainder*60;
        int iMinutes = (int) dMinutes;

        double dMinuteRemainder = (dMinutes - iMinutes);
        double dSeconds = dMinuteRemainder*60;

        return "("+iDegrees +"°, " +iMinutes +"', "+ dSeconds+"\")";
    }

    /**
     * Formats an angle in degrees, arcminutes and arcseconds to a string
     * @param degMinSecArray An array representing an angle in degrees, arcminutes and arcseconds
     * @return A string expressing the angle in degrees, arcminutes and arcseconds
     */
    public static String degreesMinutesSecondsToString(double[] degMinSecArray)
    {
        return "("+degMinSecArray[0] +"°, " +degMinSecArray[1] +"', "+ degMinSecArray[2]+"\")";
    }


    /**
     * Calculates the angle in decimal degrees, and then the angle in hours, minutes and seconds
     * @return An array representing the angle in hours, minutes and seconds
     */
    public double[] inHoursMinutesSeconds()
    {
        double dAngleDegrees = this.angle * (180.0/Math.PI);
        double dAngleHours = dAngleDegrees/15.0;

        int iHours = (int) dAngleHours;

        double dHoursRemaining = (dAngleHours - iHours);
        double dMinutes = dHoursRemaining*60;
        int iMinutes = (int) dMinutes;

        double dMinuteRemainder = (dMinutes - iMinutes);
        double dSeconds = dMinuteRemainder*60d;

        return new double[]{iHours, iMinutes, dSeconds};
    }
}
