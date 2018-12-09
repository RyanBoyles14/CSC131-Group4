package CSC131Fall2018.Group4;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import org.codehaus.stax2.XMLOutputFactory2;
import org.codehaus.stax2.XMLStreamWriter2;

public class MetricsXmlOutputter extends AbstractMetricsOutputter
{
	private XmlMapper mapper;
	private final XMLOutputFactory2 xmlOutputFactory = (XMLOutputFactory2)XMLOutputFactory.newFactory();
	private final StringWriter intermediateString = new StringWriter();
	private XMLStreamWriter2 intermediateStringStream;

	public MetricsXmlOutputter() throws XMLStreamException
	{
		XmlFactory factory = new XmlFactory();
		factory.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);

		JacksonXmlModule module = new JacksonXmlModule();
		module.addSerializer(IMetrics.class, new XmlMetricsSerializer());
		module.setDefaultUseWrapper(false);

		this.mapper = new XmlMapper(factory, module);

		this.intermediateStringStream = xmlOutputFactory.createXMLStreamWriter(this.intermediateString, "UTF-8");
	}

	public static MetricsXmlOutputter createOutputter() throws XMLStreamException
	{
		return new MetricsXmlOutputter();
	}

	public void addMetric(IMetrics metrics) throws IOException
	{
		this.mapper.writeValue(this.intermediateStringStream, metrics);
		/*
		try {
			this.intermediateStringStream.writeRaw(this.mapper.writeValueAsString(metrics));
		}
		catch (Exception e)
		{
			throw new IOException(e.getMessage());
		}
		*/

		/*
		try
		{
			this.intermediateStringStream.writeStartElement(metrics.getClass().getDeclaringClass().getSimpleName());

			for (Field field : metrics.getClass().getDeclaredFields())
			{
				this.mapper.writeValue(this.intermediateStringStream, field.get(metrics));
			}


			this.intermediateStringStream.writeEndElement();
		}
		catch (XMLStreamException e)
		{
			throw new IOException(e.getMessage());
		}
		catch (IllegalAccessException e)
		{
			throw new IOException(e.getMessage());
		}
		*/
	}

	public void out(OutputStream stream) throws Exception
	{
		Writer osw = new OutputStreamWriter(stream);
		osw.write(this.finalizeIntermediateString());
		osw.flush();
	}

	private String finalizeIntermediateString() throws IOException, XMLStreamException
	{
		final StringWriter finalString = new StringWriter();
		final XMLStreamWriter2 finalStream = xmlOutputFactory.createXMLStreamWriter(finalString, "UTF-8");

		finalStream.writeStartDocument();
		finalStream.writeStartElement(this.rootName);

		finalStream.writeStartElement("version");
		finalStream.writeCharacters(String.valueOf(this.version));
		finalStream.writeEndElement();

		finalStream.writeStartElement("metrics");
		finalStream.writeRaw(this.intermediateString.toString());
		finalStream.writeEndElement();

		finalStream.writeEndElement();
		finalStream.writeEndDocument();

		return finalString.toString();
	}
}
