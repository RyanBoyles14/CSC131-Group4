import picocli.CommandLine;
import picocli.CommandLine.*;

public class Driver
{
    @Option(names = { "-h", "--help" }, description = "print help information")
    boolean printHelp = false;

    public static void main(String[] args)
    {
        Driver app = new Driver();
        CommandLine cmd = new CommandLine(app);
        cmd.parse(args);

        if (app.printHelp)
        {
            cmd.usage(System.out);
            return;
        }

        return;
    }

}
