package CSC131Fall2018.Group4;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

//class to store each authors name and commit history
public class Contributor {

	public class Metrics implements IMetrics {
		public String name;
		public String email;
		public int age;
		public int commitsPerMonth;
		public int totalCommits;
		public double commitPercentage;
	}

	public String name, email;
	public int numCommits, total, frequency;
	public double percentage, days;
	Period diff;
	LinkedHashMap<String, String> commitMessages;
	ArrayList<String> commits;
	String initDate, endDate, initText, endText;
	public Metrics metrics = new Metrics();
	
	public Contributor(String name, String email, int totalCommits) {
		this.name = name;
		this.email = email;
		this.initDate = "null";
		this.endDate = "null";
		this.numCommits = 0;
		this.total = totalCommits;
		this.commitMessages = new LinkedHashMap<>();
		this.commits = new ArrayList<>();
	}

	// returns days between first and last commit
	public int getAge() {
		return (int) this.days;
	}

	// returns date of last commit
	public String getEndDate() {
		return endDate;
	}

	// returns date of first commit
	public String getInitialDate() {
		return initDate;
	}

	// returns author name
	public String getName() {
		return name;
	}

	// returns author email
	public String getEmail() {
		return email;
	}

	// returns number of commits for the author
	public int getNumCommits() {
		return numCommits;
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
