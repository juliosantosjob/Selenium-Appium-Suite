package automation.example.com.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Dynamics {
    private static JSONObject objectJson;

    public static String readJson(String pathJson, String key) {
        try {
            objectJson = (JSONObject) new JSONParser().parse(new FileReader(pathJson));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        JSONArray list = (JSONArray) objectJson.get(key);
        int randomPosition = new Random().nextInt(list.size());
        return (String) list.get(randomPosition);
    }
}