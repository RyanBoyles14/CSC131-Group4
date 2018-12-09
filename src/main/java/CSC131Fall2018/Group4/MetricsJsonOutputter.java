package CSC131Fall2018.Group4;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.io.OutputStream;

public class MetricsJsonOutputter extends AbstractMetricsOutputter
{
	private final ObjectMapper mapper = new ObjectMapper();
	private final JsonNode rootNode = this.mapper.createObjectNode();

	public MetricsJsonOutputter()
	{
		ObjectNode infoNode = ((ObjectNode) this.rootNode).putObject(this.rootName);
		infoNode
				.put("version", this.version)
				.putObject("metrics");
	}

	public static MetricsJsonOutputter createOutputter()
	{
		return new MetricsJsonOutputter();
	}

	public void addMetric(IMetrics metrics)
	{
		((ObjectNode) this.rootNode.get(this.rootName).get("metrics"))
				.putPOJO(metrics.getClass().getDeclaringClass().getSimpleName(), metrics);
	}

	public void out(OutputStream stream) throws IOException, JsonGenerationException, JsonMappingException
	{
		this.mapper.writerWithDefaultPrettyPrinter().writeValue(stream, this.rootNode);
	}
}
