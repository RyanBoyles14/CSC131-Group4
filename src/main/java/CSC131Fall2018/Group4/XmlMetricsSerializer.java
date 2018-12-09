package CSC131Fall2018.Group4;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class XmlMetricsSerializer extends JsonSerializer<IMetrics>
{
	public void serialize(IMetrics metrics, JsonGenerator jgen, SerializerProvider provider)
			throws IOException
	{
		// value.getClass().getDeclaringClass().getSimpleName();

		jgen.writeStartObject();

		for (Field field : metrics.getClass().getDeclaredFields())
		{
			try
			{
				if (Modifier.isPublic(field.getModifiers()))
				{
					jgen.writeObjectField(field.getName(), field.get(metrics));
				}
			}
			catch (IllegalAccessException e)
			{
				continue;
			}
		}

		jgen.writeEndObject();
	}
}