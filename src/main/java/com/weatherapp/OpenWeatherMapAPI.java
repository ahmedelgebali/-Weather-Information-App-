package com.weatherapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.net.URL;

import org.json.JSONObject;

public final class OpenWeatherMapAPI {
    private static final String API_KEY = "18e798c07391723616247beb6aa5207f";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";

    public static Weather getWeather(String location, String unit) throws IOException {
        String urlString = BASE_URL + location + "&units=" + unit + "&appid=" + API_KEY;
        System.out.println("Request URL: " + urlString); // Debugging statement
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            StringBuilder content = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            JSONObject jsonObject = new JSONObject(content.toString());
            System.out.println("Response JSON: " + jsonObject.toString(2)); // Debugging statement
            Weather weather = new Weather();
            weather.setTemp(jsonObject.getJSONObject("main").getDouble("temp"));
            weather.setHumidity(jsonObject.getJSONObject("main").getInt("humidity"));
            weather.setWindSpeed(jsonObject.getJSONObject("wind").getDouble("speed"));
            weather.setDescription(jsonObject.getJSONArray("weather").getJSONObject(0).getString("description"));
            return weather;
        } catch (IOException e) {
            System.err.println("Error reading API response: " + e.getMessage());
            throw e;
        } finally {
            conn.disconnect();
        }
    }
}
