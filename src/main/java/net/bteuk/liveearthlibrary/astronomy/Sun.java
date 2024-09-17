package net.bteuk.liveearthlibrary.astronomy;

import org.orekit.bodies.CelestialBody;
import org.orekit.bodies.CelestialBodyFactory;
import org.orekit.time.AbsoluteDate;

public class Sun extends SolarSystemObject
{
    private static final CelestialBody OrekitSun = CelestialBodyFactory.getSun();

    //The Sun cannot exist without a position. The Sun always has position. So we need to specify date.
    public Sun(AbsoluteDate absoluteDate)
    {
        super(OrekitSun, absoluteDate);
    }
}
