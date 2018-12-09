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

	private String name, email;
	private int numCommits, total, frequency;
	private double percentage, days;
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

	// calculates commit frequency for author
	@SuppressWarnings("deprecation")
	private void computeFreq() {
		Date initial = new Date(initText);
		Date end = new Date(endText);
		days = TimeUnit.DAYS.convert(end.getTime() - initial.getTime(), TimeUnit.MILLISECONDS);
		LocalDate i = initial.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate e = end.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		diff = Period.between(i, e);
		if (diff.getMonths() == 0) {
			frequency = numCommits;
		} else {
			frequency = numCommits / (diff.getMonths() + 1);
		}
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

	// returns frequency of commits per month
	public int getFrequency() {
		return this.frequency;
	}

	// returns number of commits for the author
	public int getNumCommits() {
		return numCommits;
	}

	// returns percentage of commits in string form
	public double getPercentage() {
		this.percentage = (double) numCommits / (double) total * 100;
		return this.percentage;
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
		computeFreq();
	}

	// converts text date to numerical
	@SuppressWarnings("deprecation")
	private String convert(String date) {
		Date day = new Date(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(day);
	}

}
