package CSC131Fall2018.Group4;

import java.io.*;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.*;

import javax.xml.stream.XMLStreamException;

public class Driver implements Callable<Void>
{
    @Parameters(arity = "1", description = "URL to a git project")
    String gitProjectUrl;

    @Option(names = "--output-to-stdout", description = "Send output to stdout. Enabled by default.")
    boolean outputToStdout = true;

    @Option(names = "--output-to-file", description = "Send output to specified file.")
    File outputFilename = null;

    @Option(names = "--output-format", description = "Format output in JSON or XML. JSON enabled by default.")
    String outputFormat= "json";

    @Option(names = { "-h", "--help" }, usageHelp = true, description = "print help information")
    boolean printHelp = false;

    @Option(names = { "--print-all-metrics" }, description = "print all metrics")
    boolean printAll = false;

    @Option(names = { "-c", "--contributor" }, description = "print contributor metrics")
    boolean printContributor = false;

    @Option(names = { "-C", "--coupling" }, description = "print coupling metric")
    boolean printCoupling = false;

    @Option(names = { "-H", "--halstead" }, description = "print halstead metric")
    boolean printHalstead = false;

    @Option(names = { "-I", "--inheritanceDepth" }, description = "print depth of inheritance metric")
    boolean printDepthOfInheritance = false;

    @Option(names = { "-t", "--timeComplexity" }, description = "print time complexity metric")
    boolean printTimeComplexity = false;

    @Option(names = { "-r", "--overall-repository" }, description = "print overall repository metrics")
    boolean printOverallRepository = false;

    public static void main(String[] args)
    {
        CommandLine.call(new Driver(), args);

        return;
    }

    @Override
    public Void call() throws Exception
    {
        try (final Repository repo = new Repository(this.gitProjectUrl))
        {
            AbstractMetricsOutputter outputter = newOutputterWithFormat(this.outputFormat);

            this.addAllRequestMetrics(outputter, repo);

            if (this.outputToStdout)
            {
                outputter.out(System.out);
            }

            if (this.outputFilename != null)
            {
                boolean createdFile = this.outputFilename.createNewFile();
                if (createdFile)
                {
                    FileOutputStream outputStream = new FileOutputStream(this.outputFilename);
                    outputter.out(outputStream);
                    outputStream.close();
                }
                else
                {
                    throw new RuntimeException("Could not create " + this.outputFilename + ". A file by that name already exists.");
                }
            }
        }
        catch (Exception e)
        {
            System.err.println("Failed to output metrics for " + this.gitProjectUrl + ": " + e.getMessage());
        }

        return null;
    }

    private AbstractMetricsOutputter newOutputterWithFormat(String format)
            throws RuntimeException, XMLStreamException
    {
        if (format.equalsIgnoreCase("json"))
        {
            return new MetricsJsonOutputter();
        }
        else if (format.equalsIgnoreCase("xml"))
        {
            return new MetricsXmlOutputter();
        }
        else
        {
            throw new RuntimeException("Invalid output format option \"" + this.outputFormat.toString() + "\"");
        }
    }

    private void addAllRequestMetrics(AbstractMetricsOutputter outputter, Repository repository)
            throws Exception
    {
        //if (this.printCoupling)
        //    outputter.addMetric(repository.getCouplingMetric());

        if (this.printAll || this.printHalstead)
            outputter.addMetric(repository.getHalsteadMetrics());

        if (this.printAll || this.printDepthOfInheritance)
            outputter.addMetric(repository.getDepthOfInheritanceMetrics());

        if (this.printAll || this.printTimeComplexity)
            outputter.addMetric(repository.getTimeComplexityMetrics());

        if (this.printAll || this.printOverallRepository)
            outputter.addMetric(repository.getRepositoryMetrics());

        if (this.printAll || this.printContributor)
            outputter.addMetrics(repository.getContributorsMetrics());
    }
}
