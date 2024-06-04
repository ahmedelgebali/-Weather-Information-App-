package com.weatherapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeatherApp extends Application {

    private String unit = "metric"; // Default to Celsius
    private List<String> searchHistory = new ArrayList<>();
    private ListView<String> historyListView = new ListView<>();

    @Override
    public void start(Stage primaryStage) {
        // Create UI elements
        TextField locationField = new TextField();
        locationField.setPromptText("Enter location");

        Button searchButton = new Button("Search");
        Button toggleUnitButton = new Button("Switch to °F");
        Label temperatureLabel = new Label();
        Label humidityLabel = new Label();
        Label windSpeedLabel = new Label();
        Label descriptionLabel = new Label();
        Label forecastLabel = new Label();

        // Create root layout
        VBox root = new VBox(10, locationField, searchButton, toggleUnitButton, temperatureLabel, humidityLabel, windSpeedLabel, descriptionLabel, forecastLabel, historyListView);
        root.setPadding(new Insets(10));

        // Handle search button click
        searchButton.setOnAction(event -> {
            String location = locationField.getText();
            try {
                Weather weather = OpenWeatherMapAPI.getWeather(location, unit);
                // Update UI elements with retrieved weather data
                temperatureLabel.setText("Temperature: " + String.format("%.2f %s", weather.getTemp(), getUnitSymbol()));
                humidityLabel.setText("Humidity: " + weather.getHumidity() + "%");
                windSpeedLabel.setText("Wind Speed: " + weather.getWindSpeed() + " m/s");
                descriptionLabel.setText("Description: " + weather.getDescription());

                // Update search history
                String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                searchHistory.add(location + " at " + timestamp);
                historyListView.getItems().setAll(searchHistory);

                // Change background based on time of day
                changeBackground(root);
            } catch (IOException e) {
                e.printStackTrace(); // Print stack trace for debugging
                temperatureLabel.setText("Error fetching weather data.");
            }
        });

        // Handle unit conversion
        toggleUnitButton.setOnAction(event -> {
            if (unit.equals("metric")) {
                unit = "imperial";
                toggleUnitButton.setText("Switch to °C");
            } else {
                unit = "metric";
                toggleUnitButton.setText("Switch to °F");
            }
        });

        Scene scene = new Scene(root, 300, 400);
        primaryStage.setTitle("Weather App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String getUnitSymbol() {
        return unit.equals("metric") ? "°C" : "°F";
    }

    private void changeBackground(VBox root) {
        // Example logic to change background based on time of day
        int hour = new Date().getHours();
        if (hour >= 6 && hour < 12) {
            root.setStyle("-fx-background-color: #FFD700;"); // Morning
        } else if (hour >= 12 && hour < 18) {
            root.setStyle("-fx-background-color: #87CEEB;"); // Afternoon
        } else {
            root.setStyle("-fx-background-color: #1E90FF;"); // Evening
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
