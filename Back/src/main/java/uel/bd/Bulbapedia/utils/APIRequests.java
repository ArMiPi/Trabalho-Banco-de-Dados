package uel.bd.Bulbapedia.utils;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIRequests {
    public static JSONObject getAPIResponse(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(response.body());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static int getIDFromURL(String url) {
        String[] parts = url.split("/");

        return Integer.parseInt(parts[parts.length - 1]);
    }
}
