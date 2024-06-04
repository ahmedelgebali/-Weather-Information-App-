# WeatherDemo

## Overview
WeatherDemo is a Java-based application that provides real-time weather updates for a specified location. The application features a graphical user interface (GUI) created using JavaFX, and integrates with the OpenWeatherMap API to fetch weather data.

## Features
- **Real-time Weather Data**: Fetches and displays current weather information including temperature, humidity, wind speed, and weather description.
- **Unit Conversion**: Allows switching between Celsius and Fahrenheit for temperature and metric and imperial units for wind speed.
- **Search History**: Maintains a history of recent weather searches with timestamps.
- **Dynamic Backgrounds**: Changes the background based on the time of day.
- **Error Handling**: Proper error handling for invalid location input or failed API requests.

## Requirements
- Java Development Kit (JDK) 8 or later
- Internet connection to access the OpenWeatherMap API
- OpenWeatherMap API key

## Installation and Setup
1. **Clone the Repository**
   ```sh
   git clone <repository-url>
   cd weather-demo
   ```

2. **Obtain an OpenWeatherMap API Key**
    - Sign up at [OpenWeatherMap](https://openweathermap.org/) to get a free API key.

3. **Configure the API Key**
    - Open `OpenWeatherMapAPI.java` and replace the placeholder with your actual API key:
      ```java
      private static final String API_KEY = "your_api_key"; // Ensure this is correct
      ```

4. **Compile and Run the Application**
   ```sh
   javac -d bin src/com/weatherdemo/*.java
   java -cp bin com.weatherdemo.WeatherApp
   ```

## Usage
1. **Start the Application**
    - Run the `WeatherApp` class to start the application.
    - The main window of the application will appear.

2. **Search for Weather Data**
    - Enter a location in the input field (e.g., "Cairo") and click the "Search" button.
    - The application will display the current weather data for the specified location.

3. **Switch Temperature Units**
    - Click the "Switch to °F" button to switch between Celsius and Fahrenheit.
    - The button text will update to reflect the current unit.

4. **View Search History**
    - The search history is displayed at the bottom of the application window, showing the locations and timestamps of recent searches.

## Project Structure
```
weather-demo/
├── src/
│   └── com/weatherdemo/
│       ├── WeatherApp.java          // Main application class with GUI setup
│       ├── OpenWeatherMapAPI.java   // API integration class
│       └── Weather.java             // Weather data model class
├── README.md                        // This readme file
└── bin/                             // Directory for compiled classes
```

## Code Explanation
### WeatherApp.java
- **start(Stage primaryStage)**: Initializes the GUI components and sets up event handlers.
- **getUnitSymbol()**: Returns the symbol for the current temperature unit.
- **changeBackground(VBox root)**: Changes the background color based on the time of day.
- **main(String[] args)**: Launches the application.

### OpenWeatherMapAPI.java
- **getWeather(String location, String unit)**: Makes an HTTP GET request to the OpenWeatherMap API and parses the JSON response to create a `Weather` object.

### Weather.java
- **Weather class**: A simple data model class for storing weather information (temperature, humidity, wind speed, description).

## Error Handling
- The application displays a user-friendly error message in the GUI if the weather data cannot be fetched.
- The console outputs detailed error messages for debugging purposes.

## Contact
For any issues or questions, please contact [Ahmed Gebali] at [elgebalia34@gmailcom].
