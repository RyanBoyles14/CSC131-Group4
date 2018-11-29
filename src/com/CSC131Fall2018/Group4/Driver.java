package com.CSC131Fall2018.Group4;

import java.io.IOException;
import java.nio.file.Files;

import picocli.CommandLine;
import picocli.CommandLine.*;

public class Driver
{
    @Parameters(arity = "1", description = "URL to a git project")
    String gitProjectUrl;

    @Option(names = { "-h", "--help" }, description = "print help information")
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
        Driver app = new Driver();
        CommandLine cmd = new CommandLine(app);
        try
        {
            cmd.parse(args);
        }
        catch (MissingParameterException e)
        {
            System.err.println("Missing url to repository.");
            return;
        }

        if (app.printHelp)
        {
            cmd.usage(System.out);
            return;
        }

        try
        {
            Repository repo = new Repository(app.gitProjectUrl, Files.createTempDirectory(null).toString());
            System.out.println("Repository contains " + repo.getFileCount() + " files.");
        }
        catch (Exception e)
        {
            System.err.println("Could not open repository " + app.gitProjectUrl + ": " + e.getMessage());
        }

        return;
    }

}
