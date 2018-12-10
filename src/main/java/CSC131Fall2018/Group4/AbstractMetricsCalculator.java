package CSC131Fall2018.Group4;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.io.File;
import java.util.Set;

// All metric calculating classes should extend AbstractMetricsCalculator and be able to calculate their metric
// for a single file and for an entire repository
abstract public class AbstractMetricsCalculator
{
    public AbstractMetricsCalculator(File f) throws Exception
    {
        this.newCalculation(f);
    }

    public AbstractMetricsCalculator(Repository r) throws Exception
    {
        this.newCalculation(r);
    }

    protected abstract void newCalculation(File f) throws Exception;
    protected abstract void newCalculation(Repository r) throws Exception;

    static public Set getAllImplementations()
    {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("CSC131Fall2018.Group4"))
                .setScanners(new SubTypesScanner())
                .filterInputsBy(new FilterBuilder().includePackage("CSC131Fall2018.Group4")));

        Set grandChildren = reflections.getSubTypesOf(AbstractMetricsCalculator.class);

        return grandChildren;
    }
}
