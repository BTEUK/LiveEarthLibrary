package net.bteuk.liveearthlibrary.astronomy;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import net.bteuk.liveearthlibrary.spacegeometry.Angle;
import net.bteuk.liveearthlibrary.spacegeometry.AngleUnit;
import net.bteuk.liveearthlibrary.spacegeometry.SphericalCoordinates;

import java.io.*;
import java.util.List;

/**
 * A class to represent a Star
 */
public class Star extends CelestialObject
{
    /** The apparent magnitude in the blue band */
    private final float fBMagnitude;

    /** The apparent magnitude, m, of the star in visible */
    private final float fVMagnitude;

    /** The radius of the star (In Solar Radii)*/
    private final double dRadius;

    public Star(String szName, SphericalCoordinates coordinates, double dRadius, float fApparentBlueMagnitude, float fApparentVisMagnitude)
    {
        super(szName, coordinates);
        this.dRadius = dRadius;
        this.fBMagnitude = fApparentBlueMagnitude;
        this.fVMagnitude = fApparentVisMagnitude;
    }

    /**
     *
     * @return The radius of this star in Solar Radii
     */
    public String getName()
    {
        return szName;
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
    public float getBvColour()
    {
        return fBMagnitude-fVMagnitude;
    }

    /**
     *
     * @return The apparent magnitude of this star in visible
     */
    public float getfApparentVisibleMagnitude()
    {
        return fVMagnitude;
    }

    /**
     * Extracts the information from the star data provided in the resources of this library, serialises this, and returns the star data
     * @return An array of Stars corresponding to the Star data stored in the resources of this library
     */
    public static Star[] getStarListFromResources()
    {
        //Loads the data from files
        String szJsonString = readStarDataFromLibraryResources();

        return getStarListFromJsonText(szJsonString);
    }

    /**
     * Serialises the Json string of Simbad star data, and returns the star data contained therein
     * @param szJsonText The Json string to extract the star data from
     * @return An array of Stars
     */
    public static Star[] getStarListFromJsonText(String szJsonText)
    {
        //Extract the Json data
        StarData starData = (new Gson()).fromJson(szJsonText, StarData.class);

        //Fetch the size of the data list and initialise a new Star array with this star
        int iNumStars = starData.data.size();
        Star allStars[] = new Star[iNumStars];

        System.out.println("[LiveEarthLibrary] " +iNumStars +" load from file");

        //Goes through the 'data' and extracts the information by converting the list of objects for each star into meaningful data
        for (int i = 0 ; i < iNumStars ; i++)
        {
            List<Object> starDetails = starData.data.get(i);
            //Declares the variables, setting the default values
            long oid = -1;
            String mainId = "Unnamed Star";
            double ra = 0;
            double dec = 0;
            double bMag = 10; //Ensures it isn't displayed if no details are provided
            double vMag = 10; //Ensures it isn't displayed if no details are provided

            //First check whether there is information in each field, then extract it

            if (starDetails.get(0) != null)
                oid = ((Double) starDetails.get(0)).longValue();
            if (starDetails.get(1) != null)
                mainId = (String) starDetails.get(1);
            if (starDetails.get(2) != null)
                ra = (Double) starDetails.get(2);
            if (starDetails.get(3) != null)
                dec = (Double) starDetails.get(3);
            if (starDetails.get(4) != null)
                bMag = (Double) starDetails.get(4);
            if (starDetails.get(5) != null)
                vMag = (Double) starDetails.get(5);

            //Create the star object for this star
            Star star = new Star(mainId,
                    new SphericalCoordinates(new Angle(dec, 0, 0, AngleUnit.Degrees), new Angle(ra, 0, 0, AngleUnit.Degrees)),
                    0, (float) bMag, (float) vMag);

            //Add this star to the list of stars to return
            allStars[i] = star;
        }
        return allStars;
    }

    /**
     * Reads in the json string from the 'All Stars.json' file in resources, and returns this json
     * @return A JSON string for the data in the file
     */
    private static String readStarDataFromLibraryResources()
    {
        //Try to read in the json
        BufferedReader bufferedReader = null;

        try
        {
            InputStream inputStream = Star.class.getResourceAsStream("/All Stars.json");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            return bufferedReader.readLine();

        }
        catch (IOException e)
        {
            System.out.println("[LiveEarthLibrary] Error reading file: "+e.getMessage());
            e.printStackTrace();
            return "";
        }
        catch (Exception e)
        {
            System.out.println("[LiveEarthLibrary] Error reading file: "+e.getMessage());
            e.printStackTrace();
            return "";
        }
        finally
        {
            if (bufferedReader != null)
            {
                try
                {
                    bufferedReader.close();
                }
                catch (IOException e)
                {
                    return "";
                }
            }
        }
    }

    /**
     * Calculates an rgb value for the colour of this star
     * @return An array of floats, representing the r, g, b values
     */
    public float[] calculateRGB()
    {
        //Declare variables
        float r, g, b;

        //Get the b-v value
        float bv = getBvColour();

        //Set anything redder than -0.4 to be -0.4
        if (bv < -0.4f) bv = -0.4f;

        //Set anything bluer than 2.0 to be 2.0
        if (bv > 2.0f) bv = 2.0f;

        //Red component
        if (bv < 0.0f)
            r = 0.61f + 0.11f * bv + 0.1f * bv * bv;
        else if (bv < 0.4f)
            r = 0.83f + 0.17f * bv;
        else if (bv < 1.6f)
            r = 1.0f;
        else
            r = 1.0f - 0.47f * (bv - 1.6f);

        //Green component
        if (bv < 0.0f)
            g = 0.7f + 0.07f * bv + 0.1f * bv * bv;
        else if (bv < 0.4f)
            g = 0.87f + 0.11f * bv;
        else if (bv < 1.5f)
            g = 0.98f - 0.16f * (bv - 0.4f);
        else
            g = 0.82f - 0.5f * (bv - 1.5f);

        // Blue component
        if (bv < 0.0f)
            b = 1.0f;
        else if (bv < 0.4f)
            b = 1.0f - 0.5f * bv;
        else if (bv < 1.5f)
            b = 0.6f - 0.7f * (bv - 0.4f);
        else
            b = 0.0f;

        // Return RGB array
        return new float[]{r, g, b};
    }
}

/**
 * A class representing the information in the json
 */
class StarData {
    List<Metadata> metadata;
    List<List<Object>> data;  // Star data is a list of lists (flexible for different types)
}

/**
 * A class representing the metadata
 */
class Metadata {
    String name;
    String description;
    String datatype;
    String unit;
    String ucd;
    @SerializedName("arraysize")  // Use @SerializedName if JSON field names don't match
    String arraySize;
}

