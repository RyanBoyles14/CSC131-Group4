package CSC131Fall2018.Group4;

import java.io.OutputStream;

abstract class AbstractMetricsOutputter
{
	abstract public void addMetric(IMetrics metrics);
	abstract public void out(OutputStream stream);
}