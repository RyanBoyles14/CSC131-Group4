package CSC131Fall2018.Group4;

import java.io.File;

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
			c.metrics.name = c.getName();
			c.metrics.email = c.getEmail();
			c.metrics.totalCommits = c.getNumCommits();
			c.metrics.commitPercentage = c.getPercentage();
			c.metrics.commitsPerMonth = c.getFrequency();
			c.metrics.age = c.getAge();
		}
			
	}
	

}
