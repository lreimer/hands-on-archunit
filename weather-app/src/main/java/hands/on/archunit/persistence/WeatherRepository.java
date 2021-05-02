package hands.on.archunit.persistence;

import hands.on.archunit.model.Weather;

import java.util.HashMap;
import java.util.Map;

public class WeatherRepository {
    private Map<String, Weather> weather = new HashMap<>();

    public void initialize() {
        weather.put("Rosenheim", new Weather("Rosenheim", "Sunshine"));
        weather.put("London", new Weather("London", "Rainy"));
    }

    public Weather getWeatherForCity(String city) {
        return weather.getOrDefault(city, new Weather(city, "Unknown"));
    }
}
