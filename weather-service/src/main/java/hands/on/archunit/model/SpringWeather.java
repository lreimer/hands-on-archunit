package hands.on.archunit.model;

public class SpringWeather {

    public SpringWeather(final String city, final String weather) {
        this.city = city;
        this.weather = weather;
    }

    private String city;
    private String weather;

    public String getCity() {
        return city;
    }

    public String getWeather() {
        return weather;
    }
}
