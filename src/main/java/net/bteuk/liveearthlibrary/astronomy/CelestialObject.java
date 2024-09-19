package net.bteuk.liveearthlibrary.astronomy;

import net.bteuk.liveearthlibrary.spacegeometry.SphericalCoordinates;
import org.orekit.data.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

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
        manager = DataContext.getDefault().getDataProvidersManager();
        manager.addProvider(new ZipJarCrawler("orekit-data-master.zip"));

        return manager;
    }

    private static void copyStreamToFile(InputStream inputStream, Path targetPath) throws IOException
    {
        // Copy the InputStream to the target location
        Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
        inputStream.close();
    }
}
