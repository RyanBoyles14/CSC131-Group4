package CSC131Fall2018.Group4;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.IOException;
import java.io.OutputStream;

abstract class AbstractMetricsOutputter
{
	final protected String rootName = "Group4Metrics";
	final protected double version = 1.0;
	abstract public void addMetric(IMetrics metrics);
	abstract public void out(OutputStream stream)  throws IOException, JsonGenerationException, JsonMappingException;
}