package de.dal3x.dalbot.external;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

public class DogFinder {

    public static URL getRandomDogImageURL() throws IOException {
        JSONObject json = new JSONObject(
                IOUtils.toString(new URL("https://dog.ceo/api/breeds/image/random"), Charset.forName("UTF-8")));
        String url = json.getString("message");
        return new URL(url);
    }

}
