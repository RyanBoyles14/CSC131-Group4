package CSC131Fall2018.Group4;

import java.io.File;

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
}
