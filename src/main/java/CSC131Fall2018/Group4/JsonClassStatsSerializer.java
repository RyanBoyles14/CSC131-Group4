package CSC131Fall2018.Group4;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class JsonClassStatsSerializer extends JsonSerializer<CouplingMetricsCalculator.ClassStats>
{
	public void serialize(CouplingMetricsCalculator.ClassStats classStat, JsonGenerator jgen, SerializerProvider provider)
			throws IOException
	{
		jgen.writeStartObject();

		jgen.writeNumberField("index", classStat.index);
		jgen.writeStringField("name", classStat.classname);

		jgen.writeFieldName("interaction");
		jgen.writeStartArray();
		for (CouplingMetricsCalculator.InteractionEntry entry : classStat.interactionCoupling)
		{
			jgen.writeStartObject();
			jgen.writeStringField("target", entry.targetClassname);
			jgen.writeStringField("value", entry.value);
			jgen.writeEndObject();
		}
		jgen.writeEndArray();

		jgen.writeFieldName("component");
		jgen.writeStartArray();
		for (CouplingMetricsCalculator.ComponentEntry entry : classStat.componentCoupling)
		{
			jgen.writeStartObject();
			jgen.writeStringField("target", entry.targetClassname);
			jgen.writeStringField("value", entry.value);
			jgen.writeEndObject();
		}
		jgen.writeEndArray();

		jgen.writeFieldName("inheritance");
		jgen.writeStartArray();
		for (CouplingMetricsCalculator.InheritanceEntry entry : classStat.inheritanceCoupling)
		{
			jgen.writeString(entry.inheritedClassname);
		}
		jgen.writeEndArray();

		jgen.writeEndObject();
	}
}