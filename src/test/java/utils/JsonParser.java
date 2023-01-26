package utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JsonParser {
    private static final JSONParser parser = new JSONParser();
    public static JSONObject config;

    public static void parsing() {
        try {
            config = (JSONObject) parser.parse(new FileReader("src/test/resources/testData.json"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static String getTo() {
        parsing();
        return (String) config.get("to");
    }

    public static String getSubject() {
        parsing();
        return (String) config.get("subject");
    }

    public static String getBody() {
        parsing();
        return (String) config.get("body");
    }

}
