package CSC131Fall2018.Group4;

import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.Set;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
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
        try (final Repository repo = new Repository(this.gitProjectUrl))
        {
            AbstractMetricsOutputter blah = new MetricsJsonOutputter();
            blah.addMetric(repo.metrics);
            blah.out(System.out);

            System.out.println();

            AbstractMetricsOutputter blah2 = new MetricsXmlOutputter();
            blah2.addMetric(repo.metrics);
            blah2.out(System.out);
        }
        catch (Exception e)
        {
            System.err.println("Failed to output metrics for " + this.gitProjectUrl + ": " + e.getMessage());
        }

        return null;
    }

}
