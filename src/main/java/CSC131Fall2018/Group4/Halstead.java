package CSC131Fall2018.Group4;

import java.util.ArrayList;

public class Halstead
{
	public class Metrics implements IMetrics
	{
		public ArrayList<HalsteadBuilder> halstead = new ArrayList<>();
	}

	private IMetrics metrics;

	Halstead()
	{
		this.metrics = new Halstead.Metrics();
	}

	public IMetrics getMetrics()
	{
		return this.metrics;
	}
}
