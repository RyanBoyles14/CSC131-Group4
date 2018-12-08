package CSC131Fall2018.Group4;

import java.io.PrintStream;

abstract class AbstractMetricsOutputter
{
	abstract public void addMetric(IMetrics metrics);
	abstract public void out(PrintStream stream);
}
