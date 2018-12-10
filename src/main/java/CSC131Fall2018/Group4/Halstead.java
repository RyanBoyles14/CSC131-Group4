package CSC131Fall2018.Group4;

public class Halstead
{
	public class Metrics implements IMetrics
	{
		public int totalOperators;
		public int totalOperands;
		public int vocab;
		public int length;
		public int time;
		public int bugs;
		public int effort;
		public int difficulty;
		public double calcLength;
		public double volume;
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
