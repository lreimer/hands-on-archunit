package hands.on.archunit.cmd;

import hands.on.archunit.persistence.WeatherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "get", description = "Get current weather for city")
public class GetWeatherCommand implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetWeatherCommand.class);

    @Option(names = {"-c", "--city"}, description = "The city", interactive = true)
    private String city;

    private WeatherRepository repository;

    public GetWeatherCommand() {
        this.repository = new WeatherRepository();
        this.repository.initialize();
    }

    @Override
    public void run() {
        LOGGER.info("Current {}", repository.getWeatherForCity(city));
    }
}
