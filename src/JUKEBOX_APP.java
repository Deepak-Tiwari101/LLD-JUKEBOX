import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import commands.CommandRegistry;

public class JUKEBOX_APP {
    public static void main(String[] args) {
        if (args.length != 1) {
            throw new RuntimeException();
        }
        List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
        run(commandLineArgs);
    }

    public static void run(List<String> commandLineArgs) {

        Configuration conf = Configuration.getInstance();

        CommandRegistry commandRegistry = conf.getCommandRegistry();

        String inputFile = commandLineArgs.get(0).split("=")[1];

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {

            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                commandRegistry.invokeCommand(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
