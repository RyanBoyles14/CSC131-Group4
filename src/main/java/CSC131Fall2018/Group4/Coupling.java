package CSC131Fall2018.Group4;

import java.util.ArrayList;

public class Coupling
{
	public class Metrics implements IMetrics
	{
		public ArrayList<CouplingMetricsCalculator.ClassStats> classes = new ArrayList<CouplingMetricsCalculator.ClassStats>();
	}

	private IMetrics metrics;

	Coupling()
	{
		this.metrics = new Coupling.Metrics();
	}

	public IMetrics getMetrics()
	{
		return this.metrics;
	}
}