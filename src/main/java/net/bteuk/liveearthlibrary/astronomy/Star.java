package net.bteuk.liveearthlibrary.astronomy;

import net.bteuk.liveearthlibrary.spacegeometry.SphericalCoordinates;

/**
 * A class to represent a Star
 */
public class Star
{
    /** The equatorial coordinates (RA, DEC and distance (In PC)) of the star */
    private final SphericalCoordinates equatorialCoordinates;

    /** The radius of the star (In Solar Radii)*/
    private final double dRadius;

    /** The b-v colour of the star */
    private final double bvColour;

    /** The apparent magnitude, m, of the star */
    private final float fApparentMagnitude;

    public Star(SphericalCoordinates coordinates, double dRadius, double bvColour, float fApparentMagnitude)
    {
        this.equatorialCoordinates = coordinates;
        this.dRadius = dRadius;
        this.bvColour = bvColour;
        this.fApparentMagnitude = fApparentMagnitude;
    }

    /**
     *
     * @return A reference to the spherical coordinates of this star
     */
    public SphericalCoordinates getEquatorialCoordinates()
    {
        return equatorialCoordinates;
    }

    /**
     *
     * @return The radius of this star in Solar Radii
     */
    public double getRadius()
    {
        return dRadius;
    }

    /**
     *
     * @return The b-v colour of this star
     */
    public double getBvColour()
    {
        return bvColour;
    }

    /**
     *
     * @return The apparent magnitude of this star
     */
    public float getfApparentMagnitude()
    {
        return fApparentMagnitude;
    }

}
