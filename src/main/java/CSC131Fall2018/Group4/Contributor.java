package CSC131Fall2018.Group4;

import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

//class to store each authors name and commit history
public class Contributor {

	public class Metrics implements IMetrics {
		public String firstCommit;
		public String lastCommit;
		public int totalCommits;
		public String percentage;
		public String frequency;
		public String activityPeriod;

	}

	private String name, email;
	int numCommits, total, frequency;
	double percentage, days;
	Period diff;
	LinkedHashMap<String, String> commitMessages;
	ArrayList<String> commits;
	String initDate, endDate, initText, endText;
	IMetrics metrics;

	public Contributor(String name, String email, int totalCommits) {
		this.metrics = new Contributor.Metrics();
		this.name = name;
		this.email = email;
		this.initDate = "null";
		this.endDate = "null";
		this.numCommits = 0;
		this.total = totalCommits;
		this.commitMessages = new LinkedHashMap<>();
		this.commits = new ArrayList<>();
	}

	public IMetrics getMetrics() {
		return this.metrics;
	}

	// returns author name
	public String getName() {
		return name;
	}

	// returns author email
	public String getEmail() {
		return email;
	}

	// updates commit history, initial and final commit dates for author
	public void add(String date) {
		String d = convert(date);
		if (commits.size() == 0) {
			initText = date;
			initDate = d;
		}
		commits.add(d);
		endDate = d;
		endText = date;
		numCommits++;
	}

	// converts text date to numerical
	@SuppressWarnings("deprecation")
	private String convert(String date) {
		Date day = new Date(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(day);
	}

}
