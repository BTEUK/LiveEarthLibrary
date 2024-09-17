package net.bteuk.liveearthlibrary.astronomy;

import net.bteuk.liveearthlibrary.spacegeometry.SpaceGeometryUtils;
import net.bteuk.liveearthlibrary.spacegeometry.SphericalCoordinates;
import org.hipparchus.geometry.euclidean.threed.Vector3D;
import org.orekit.bodies.CelestialBody;
import org.orekit.frames.FramesFactory;
import org.orekit.time.AbsoluteDate;

public abstract class SolarSystemObject extends CelestialObject
{
    private final CelestialBody body;

    public SolarSystemObject(CelestialBody body, AbsoluteDate dateTime)
    {
        super(body.getName(), calculateEquatorialPositionOrekit(body, dateTime));
        this.body = body;
    }

    /**
     * Updates the equatorial coordinates of the moon
     * @param time The new time to calculate the position at
     */
    public void updateEquatorialCoordinates(AbsoluteDate time)
    {
        super.updateEquatorialCoordinates(calculateEquatorialPositionOrekit(this.body, time));
    }

    /**
     * Calculates the equatorial coordinates of the object given a time, using the orekit library
     * @param body The CelestialBody instance for this object
     * @param absoluteDate The time to calculate the position of the object at
     * @return Spherical coordinates of the object in the equatorial J2000 frame
     */
    public static SphericalCoordinates calculateEquatorialPositionOrekit(CelestialBody body, AbsoluteDate absoluteDate)
    {
        //Get the coordinates of the moon in J2000 equatorial coordinates
        Vector3D coordinates = body.getPosition(absoluteDate, FramesFactory.getEME2000());

        //Extract the cartesian coordinates
        double x = coordinates.getX();
        double y = coordinates.getY();
        double z = coordinates.getZ();

        //Convert to spherical and return
        return SpaceGeometryUtils.cartesianCoordinatesToSphericalCoordinates(x, y, z);
    }

}
