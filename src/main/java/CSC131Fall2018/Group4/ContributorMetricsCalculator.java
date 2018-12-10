package CSC131Fall2018.Group4;

import java.io.File;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ContributorMetricsCalculator extends AbstractMetricsCalculator {
	public ContributorMetricsCalculator(Repository r) throws Exception {
		super(r);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void newCalculation(File f) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override 
	protected void newCalculation(Repository r) throws Exception {
		// TODO Auto-generated method stub
		for (Contributor c : r.getContributors()) {
			((Contributor.Metrics) c.getMetrics()).totalCommits = c.numCommits;
			double p = computePercentage(c);
			((Contributor.Metrics) c.getMetrics()).percentage = String.format("%.2f%%", p);
			((Contributor.Metrics) c.getMetrics()).frequency = computeFreq(c) + " commits per month";
			((Contributor.Metrics) c.getMetrics()).activityPeriod = (int) c.days + " days";
			((Contributor.Metrics) c.getMetrics()).firstCommit = c.initDate;
			((Contributor.Metrics) c.getMetrics()).lastCommit = c.endDate;
		}		
	}
	
	@SuppressWarnings("deprecation")
	private int computeFreq(Contributor c) {
		Date initial = new Date(c.initText);
		Date end = new Date(c.endText);
		c.days = TimeUnit.DAYS.convert(end.getTime() - initial.getTime(), TimeUnit.MILLISECONDS);
		LocalDate i = initial.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate e = end.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		c.diff = Period.between(i, e);
		if (c.diff.getMonths() == 0) {
			return c.numCommits;
		} else {
			return c.numCommits / (c.diff.getMonths() + 1);
		}
	}
	private double computePercentage(Contributor c) {
		return (double) c.numCommits / (double) c.total * 100;
	}
	

}
