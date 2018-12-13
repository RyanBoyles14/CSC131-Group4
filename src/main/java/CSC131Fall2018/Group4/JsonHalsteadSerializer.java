package CSC131Fall2018.Group4;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class JsonHalsteadSerializer extends JsonSerializer<HalsteadBuilder>
{
	public void serialize(HalsteadBuilder halsteadBuilder, JsonGenerator jgen, SerializerProvider provider)
			throws IOException
	{
		// value.getClass().getDeclaringClass().getSimpleName();

		jgen.writeStartObject();

		jgen.writeObjectField(halsteadBuilder.getName(), halsteadBuilder.toString());

		jgen.writeEndObject();
	}
}