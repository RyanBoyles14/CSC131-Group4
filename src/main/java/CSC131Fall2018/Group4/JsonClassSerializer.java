package CSC131Fall2018.Group4;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class JsonClassSerializer extends JsonSerializer<CSC131Fall2018.Group4.Class>
{
	public void serialize(CSC131Fall2018.Group4.Class klass, JsonGenerator jgen, SerializerProvider provider)
			throws IOException
	{
		// value.getClass().getDeclaringClass().getSimpleName();

		jgen.writeStartObject();

		jgen.writeObjectField(klass.getName(), klass.getInheritance().toString());

		jgen.writeEndObject();
	}
}