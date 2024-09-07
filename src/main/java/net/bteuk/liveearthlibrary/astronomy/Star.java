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
public class Star
{
    /** The common name of the star */
    private final String szName;

    /** The equatorial coordinates (RA, DEC and distance (In PC)) of the star */
    private final SphericalCoordinates equatorialCoordinates;

    /** The radius of the star (In Solar Radii)*/
    private final double dRadius;

    /** The b-v colour of the star */
    private final double bvColour;

    /** The apparent magnitude, m, of the star */
    private final float fApparentMagnitude;

    public Star(String szName, SphericalCoordinates coordinates, double dRadius, double bvColour, float fApparentMagnitude)
    {
        this.szName = szName;
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

    public static Star[] getStarList()
    {
        Gson gson = new Gson();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        String jsonString;

        //Try to read in the json
        try
        {
            fileReader = new FileReader("src/main/java/net/bteuk/liveearthlibrary/astronomy/All Stars.json");
            bufferedReader = new BufferedReader(fileReader);
            jsonString = bufferedReader.readLine();

        }
        catch (IOException e)
        {
            return new Star[0];
        }
        finally
        {
            if (fileReader != null)
            {
                try {
                    bufferedReader.close();
                }
                catch (IOException e)
                {
                    return new Star[0];
                }

                try {
                    fileReader.close();
                }
                catch (IOException e)
                {
                    return new Star[0];
                }
            }
        }

        //Extract the Json data
        StarData starData = gson.fromJson(jsonString, StarData.class);

        //Fetch the size of the data list and initialise a new Star array with this star
        int iNumStars = starData.data.size();
        Star allStars[] = new Star[iNumStars];

        //Goes through the 'data' and extracts the information by converting the list of objects for each star into meaningful data
        for (int i = 0 ; i < iNumStars ; i++)
        {
            List<Object> starDetails = starData.data.get(i);
            //Declares the variables, setting the default values
            long oid = -1;
            String mainId = "Unnamed Star";
            double ra = 0;
            double dec = 0;
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
                vMag = (Double) starDetails.get(4);

            //Create the star object for this star
            Star star = new Star(mainId,
                    new SphericalCoordinates(new Angle(dec, 0, 0, AngleUnit.Degrees), new Angle(ra, 0, 0, AngleUnit.Degrees)),
                    0, 0, (float) vMag);

            //Add this star to the list of stars to return
            allStars[i] = star;
        }
        return allStars;
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
