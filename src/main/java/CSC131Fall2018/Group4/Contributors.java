package CSC131Fall2018.Group4;

import java.util.ArrayList;

public class Contributors
{
	public class Metrics implements IMetrics
	{
		public ArrayList<Contributor> contributors = new ArrayList<>();
	}

	private IMetrics metrics;

	Contributors()
	{
		this.metrics = new Contributors.Metrics();
	}

	public IMetrics getMetrics()
	{
		return this.metrics;
	}
}
