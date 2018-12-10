package CSC131Fall2018.Group4;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.OutputStream;
import java.util.List;

public class MetricsJsonOutputter extends AbstractMetricsOutputter
{
	private ObjectMapper mapper;
	private JsonNode rootNode;

	public MetricsJsonOutputter()
	{
		JsonFactory factory = new JsonFactory();
		factory.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);

		this.mapper = new ObjectMapper(factory);

		SimpleModule module = new SimpleModule();
		module.addSerializer(CSC131Fall2018.Group4.Class.class, new JsonClassSerializer());
		this.mapper.registerModule(module);

		this.rootNode = this.mapper.createObjectNode();

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
			throws Exception
	{
		((ObjectNode) this.rootNode.get(this.rootName).get("metrics"))
				.putPOJO(metrics.getClass().getDeclaringClass().getSimpleName(),
						metrics);
	}

	public void addMetrics(List<IMetrics> metrics)
			throws Exception
	{
		for (IMetrics metric : metrics)
		{
			((ObjectNode) this.rootNode.get(this.rootName).get("metrics"))
					.putPOJO(metric.getClass().getDeclaringClass().getSimpleName(),
							metric);
		}
	}

	public void out(OutputStream stream)
			throws Exception
	{
		this.mapper.writerWithDefaultPrettyPrinter().writeValue(stream, this.rootNode);
	}
}
