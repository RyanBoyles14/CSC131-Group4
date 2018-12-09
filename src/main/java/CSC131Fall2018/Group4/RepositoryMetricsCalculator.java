package main.java.CSC131Fall2018.Group4;

import java.io.File;

public class RepositoryMetricsCalculator extends AbstractMetricsCalculator {

	public RepositoryMetricsCalculator(Repository r) throws Exception {
		// TODO Auto-generated constructor stub
		super(r);
	}

	@Override
	protected void newCalculation(File f) throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	protected void newCalculation(Repository r) throws Exception {
		// TODO Auto-generated method stub
		r.metrics.fileCount = r.getList().size();
		
	}

	

}
