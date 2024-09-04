package net.bteuk.liveearthlibrary.OpenWeatherMap;

import com.google.gson.Gson;
import net.bteuk.liveearthlibrary.OpenWeatherMap.responsedata.CurrentWeatherData;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.apache.hc.core5.io.CloseMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Represents a request to the OpenWeatherMap API's current weather node (api.openweathermap.org/data/2.5/weather)
 */
public class CurrentWeatherRequest
{
    //Coordinates for the data request
    private final double dLatitude;
    private final double dLongitude;

    //An API key for OpenWeatherMap
    private final String szApiKey;

    //Units to request the data back in
    private Units units;

    /**
     * Initiates the request object for a request
     * @param dLatitude Latitude coordinate of the location you are requesting data for
     * @param dLongitude Longitude coordinate of the location you are requesting data for
     * @param szApiKey An API key for OpenWeatherMap
     */
    public CurrentWeatherRequest(double dLatitude, double dLongitude, String szApiKey)
    {
        this.dLatitude = dLatitude;
        this.dLongitude = dLongitude;

        //Ensures API key is initialised
        if (szApiKey == null)
            szApiKey = "";

        this.szApiKey = szApiKey;
    }

    /**
     * Makes a data request to the API
     * @param reponseFormat The format to retrieve the data back in
     * @return A CurrentWeatherData object with all of the data returned by the API
     */
    public CurrentWeatherData makeRequest(ReponseFormat reponseFormat)
    {
        //Creates the data object
        CurrentWeatherData currentWeatherData;

        //Makes the call to the API
        String szJsonResponse = htmlCall();

        System.out.println(szJsonResponse);

        //Serialise the data
        Gson gson = new Gson();
        currentWeatherData = gson.fromJson(szJsonResponse, CurrentWeatherData.class);

        return currentWeatherData;
    }

    private String htmlCall()
    {
        String szAPIAddressEndPoint  = "https://api.openweathermap.org/data/2.5/weather?lat="+dLatitude
                +"&lon="+dLongitude
                +"&appid="+szApiKey;


        //Creates the client object
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //----Creates the request----
        //Declares the request builder object
        ClassicRequestBuilder builder;
        builder = ClassicRequestBuilder.get(szAPIAddressEndPoint);

        //Checks to see if an API key was specified
        if (szApiKey.equals(""))
        {
            //No API key was specified, so do not add to request. There is no need to send private information over the internet which isn't even needed
        }

        //Adds the request json
//        builder = builder.setEntity("", ContentType.APPLICATION_JSON);

        //Builds the request
        ClassicHttpRequest httpRequest = builder.build();

        //Attempt to execute the request
        try
        {
            //Executes the request
            String szJsonResponse = httpClient.execute(httpRequest, new HttpClientResponseHandler<String>()
            {
                public String handleResponse(ClassicHttpResponse response) throws HttpException, IOException
                {
                    //Prints the status of the request - should always be 200
                    System.out.println("\nLiveEarthLibrary.request() status of the request: " + response.getCode() + " " + response.getReasonPhrase());

                    if (true) {
                        System.out.println("\nResponse Headers:");
                        for (int i = 0; i < response.getHeaders().length; i++) {
                            System.out.println(i + " - " + response.getHeaders()[i]);
                        }
                        System.out.println("End of headers\n");
                    }

                    //Extracts the content from the response
                    int iCode = response.getCode();
                    //TODO: Some error handling

                    final HttpEntity entity = response.getEntity();
                    InputStream content = entity.getContent();
                    InputStreamReader reader = new InputStreamReader(content);
                    BufferedReader br = new BufferedReader(reader);

                    //Puts the response values into a BuildTheEarthAPIResponse class
                    String szResponseString = readAll(br);

                    // Ensure it is fully consumed
                    EntityUtils.consume(entity);
                    return szResponseString;
                }
            });
            return szJsonResponse;
        }
        catch (Exception e)
        {
            return null;
        }
        finally
        {
            httpClient.close(CloseMode.GRACEFUL);
        }
    }

    /**
     * Reads all lines from an InputStream into a String
     * @return The full string content of the InputStream
     */
    private static String readAll(BufferedReader br)
    {
        StringBuilder sb = new StringBuilder("");
        try
        {
            String line;
            line = br.readLine();
            while (line != null)
            {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            sb = new StringBuilder();
        }
        return sb.toString();
    }

}
