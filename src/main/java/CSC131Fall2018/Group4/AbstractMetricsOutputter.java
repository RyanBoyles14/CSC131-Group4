package CSC131Fall2018.Group4;

import java.io.OutputStream;

abstract class AbstractMetricsOutputter
{
	final protected String rootName = "Group4Metrics";
	final protected double version = 1.0;
	abstract public void addMetric(IMetrics metrics) throws Exception;
	abstract public void out(OutputStream stream)  throws Exception;
}