package de.dal3x.dalbot.external;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.dal3x.dalbot.token.Token;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TenorScraper {

    /**
     * Gets an random image URL depending on the limit and searchterm The limit decides from how
     * many random images one is choosen
     * 
     * @param searchTerm what to search
     * @param limit how many URLs should be searched
     * @return the URL
     */
    public static URL getRandomImageURL(String searchTerm, int limit) {
        List<URL> list = getImageURLs(searchTerm, limit);
        return list.get(new Random().nextInt(list.size()));
    }

    /**
     * Gets a list of a number of image URLs depending on the limit and searchterm
     * 
     * @param searchTerm what to search
     * @param limit how many URLs should be searched
     * @return the list of the URLs
     */
    public static List<URL> getImageURLs(String searchTerm, int limit) {
        LinkedList<URL> list = new LinkedList<URL>();
        JSONObject json = getSearchResults(searchTerm, limit);
        JSONArray results = (JSONArray) json.get("results");
        for (int i = 0; i < limit; i++) {
            JSONObject subObject = results.getJSONObject(i);
            String s = subObject.getString("url");
            try {
                list.add(new URL(s));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * Get Search Result GIFs
     */
    public static JSONObject getSearchResults(String searchTerm, int limit) {

        // make search request - using default locale of EN_US

        final String url = String.format("https://api.tenor.com/v1/search?q=%1$s&key=%2$s&limit=%3$s", searchTerm,
                Token.tenor, limit);
        try {
            return get(url);
        } catch (IOException | JSONException ignored) {
        }
        return null;
    }

    /**
     * Construct and run a GET request
     */
    private static JSONObject get(String url) throws IOException, JSONException {
        HttpURLConnection connection = null;
        try {
            // Get request
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            // Handle failure
            int statusCode = connection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK && statusCode != HttpURLConnection.HTTP_CREATED) {
                String error = String.format("HTTP Code: '%1$s' from '%2$s'", statusCode, url);
                throw new ConnectException(error);
            }

            // Parse response
            return parser(connection);
        } catch (Exception ignored) {
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return new JSONObject("");
    }

    /**
     * Parse the response into JSONObject
     */
    private static JSONObject parser(HttpURLConnection connection) throws JSONException {
        char[] buffer = new char[1024 * 4];
        int n;
        InputStream stream = null;
        try {
            stream = new BufferedInputStream(connection.getInputStream());
            InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
            StringWriter writer = new StringWriter();
            while (-1 != (n = reader.read(buffer))) {
                writer.write(buffer, 0, n);
            }
            return new JSONObject(writer.toString());
        } catch (IOException ignored) {
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException ignored) {
                }
            }
        }
        return new JSONObject("");
    }
}