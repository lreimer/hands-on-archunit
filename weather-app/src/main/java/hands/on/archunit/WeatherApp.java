package hands.on.archunit;

import hands.on.archunit.cmd.GetWeatherCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Spec;

@Command(version = "@|yellow Weather App 1.0|@", subcommands = {GetWeatherCommand.class}, mixinStandardHelpOptions = true)
public class WeatherApp implements Runnable {

    @Spec
    private CommandSpec spec;

    private WeatherApp() { }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new WeatherApp()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        throw new CommandLine.ParameterException(spec.commandLine(), "Missing required command");
    }
}
