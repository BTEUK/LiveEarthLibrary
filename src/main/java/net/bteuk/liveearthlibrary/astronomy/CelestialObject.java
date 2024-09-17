package net.bteuk.liveearthlibrary.astronomy;

import net.bteuk.liveearthlibrary.spacegeometry.SphericalCoordinates;
import org.orekit.data.DataContext;
import org.orekit.data.DataProvidersManager;
import org.orekit.data.DirectoryCrawler;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public abstract class CelestialObject
{
    public static DataProvidersManager manager = loadData();

    /** The common name of the object */
    protected final String szName;

    /** The equatorial coordinates (RA, DEC and distance (In PC)) of the star */
    private SphericalCoordinates equatorialCoordinates;

    /**
     *
     * @param szName The name of the object
     * @param coordinates Equatorial coordinates of the object
     */
    public CelestialObject(String szName, SphericalCoordinates coordinates)
    {
        this.szName = szName;
        this.equatorialCoordinates = coordinates;
    }

    protected void updateEquatorialCoordinates(SphericalCoordinates equatorialCoordinates)
    {
        this.equatorialCoordinates = equatorialCoordinates;
    }

    /**
     *
     * @return A reference to the spherical coordinates of this object
     */
    public SphericalCoordinates getEquatorialCoordinates()
    {
        return this.equatorialCoordinates;
    }

    /**
     * Load the bundled orekit data into the data manager
     * @return A reference to the DataProvidersManager singleton
     */
    private static DataProvidersManager loadData()
    {
        DataProvidersManager manager = null;

        //Attempt to load the data
        try
        {
            URL filePath = CelestialObject.class.getResource("/orekit-data-master");
            assert filePath != null;
            File orekitData = new File(filePath.toURI());
            manager = DataContext.getDefault().getDataProvidersManager();
            manager.addProvider(new DirectoryCrawler(orekitData));
        }
        catch (URISyntaxException | NullPointerException e)
        {
            e.printStackTrace();
        }

        return manager;
    }
}
