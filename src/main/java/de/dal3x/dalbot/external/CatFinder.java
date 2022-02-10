package de.dal3x.dalbot.external;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class CatFinder {

    public static URL getRandomCatImageURL() throws IOException {
        InputStream inputStream = new URL("https://api.thecatapi.com/v1/images/search?mime_types=jpg,png").openStream();         
        JSONArray array = new JSONArray(new JSONTokener(inputStream));
        JSONObject json = array.getJSONObject(0);
        String url = json.getString("url");
        return new URL(url);
    }
}
