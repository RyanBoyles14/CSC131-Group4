package CSC131Fall2018.Group4;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class JsonHalsteadSerializer extends JsonSerializer<HalsteadBuilder>
{
	public void serialize(CSC131Fall2018.Group4.HalsteadBuilder hb, JsonGenerator jgen, SerializerProvider provider)
			throws IOException
	{
		// value.getClass().getDeclaringClass().getSimpleName();

		jgen.writeStartObject();

		jgen.writeRaw(hb.getName());
		jgen.writeObjectField("Total Operators", hb.getOprt());
		jgen.writeObjectField("Total Operands", hb.getOpnd());
		jgen.writeObjectField("Total Vocab", hb.getVocab());
		jgen.writeObjectField("Total Length", hb.getLength());
		jgen.writeObjectField("Total Time", hb.getTime());
		jgen.writeObjectField("Total Bugs", hb.getBugs());
		jgen.writeObjectField("Total Effort", hb.getEffort());
		jgen.writeObjectField("Total Difficulty", hb.getDiff());
		jgen.writeObjectField("Total Calculated Length", hb.getCalcLength());
		jgen.writeObjectField("Total Volume", hb.getVolume());

		jgen.writeEndObject();
	}
}