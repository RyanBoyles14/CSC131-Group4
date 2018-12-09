package main.java.CSC131Fall2018.Group4;

public class RepositoryMetricsCalculator {

	public RepositoryMetricsCalculator(Repository r) {
		r.metrics.fileCount = r.getList().size();
	}

}
