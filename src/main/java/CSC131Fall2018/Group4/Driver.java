package CSC131Fall2018.Group4;

import java.nio.file.Files;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.*;

public class Driver implements Callable<Void>
{
    @Parameters(arity = "1", description = "URL to a git project")
    String gitProjectUrl;

    @Option(names = { "-h", "--help" }, usageHelp = true, description = "print help information")
    boolean printHelp = false;

    @Option(names = { "-c", "--coupling" }, description = "print coupling metric")
    boolean printCoupling = false;

    @Option(names = { "-H", "--halstead" }, description = "print halstead metric")
    boolean printHalstead = false;

    @Option(names = { "-I", "--inheritanceDepth" }, description = "print depth of inheritance metric")
    boolean printInheritanceDepth = false;

    @Option(names = { "-C", "--commits" }, description = "print amount of commits")
    boolean printCommitAmount = false;

    @Option(names = { "-t", "--timeComplexity" }, description = "print time complexities")
    boolean printTimeComplexity = false;

    public static void main(String[] args)
    {
        CommandLine.call(new Driver(), args);

        return;
    }

    @Override
    public Void call() throws Exception
    {
        try
        {
            Repository repo = new Repository(this.gitProjectUrl, Files.createTempDirectory(null).toString());
            System.out.println("Repository contains " + repo.getFileCount() + " files.");
        }
        catch (Exception e)
        {
            System.err.println("Could not open repository " + this.gitProjectUrl + ": " + e.getMessage());
        }

        return null;
    }

}
