package CSC131Fall2018.Group4;

import java.util.ArrayList;

public class TimeComplexity
{
	public class Metrics implements IMetrics
	{
		public String worstCase;
	}

	private IMetrics metrics;

	TimeComplexity()
	{
		this.metrics = new TimeComplexity.Metrics();
	}

	public IMetrics getMetrics()
	{
		return this.metrics;
	}
}
