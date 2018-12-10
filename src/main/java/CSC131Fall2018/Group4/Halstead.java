package CSC131Fall2018.Group4;

import java.util.ArrayList;

public class Halstead
{
	public class Metrics implements IMetrics
	{
		ArrayList<HalsteadBuilder> halstead = new ArrayList<>();
	}

	public IMetrics metrics;

	Halstead()
	{
		this.metrics = new Halstead.Metrics();
	}

	public IMetrics getMetrics()
	{
		return this.metrics;
	}
}
