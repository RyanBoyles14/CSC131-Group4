package CSC131Fall2018.Group4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.revwalk.RevCommit;

// list of authors and their commit history, constructed during URL cloning
public class AuthorStats
{
    public class Metrics implements IMetrics
    {
        public int totalCommits = 0;
    }

    public AuthorStats.Metrics metrics = this.new Metrics();

    Git git;
    Iterable<RevCommit> log;
    Iterator<RevCommit> itr;
    RevCommit commit;
    ArrayList<Author> authors = new ArrayList<>();
    ArrayList<String> namesList = new ArrayList<>();
    LinkedHashMap<String, String> idList = new LinkedHashMap<>();
    String s, name, email, message, date;

	// builds a list of Author objects from the Git repository
	public AuthorStats(Git gitObject) throws NoHeadException, GitAPIException, IOException {
		git = gitObject;
		log = git.log().call();
		buildLogs();
		parseID();
		update();
	}

	// parse "idLog.txt" to update commit history for each author
	private void update() throws FileNotFoundException {
		Scanner sc = new Scanner(new File("idLog.txt"));
		while (sc.hasNextLine()) {
			s = sc.nextLine();
			name = s.substring(s.indexOf("[") + 1, s.indexOf(","));
			date = s.substring(s.lastIndexOf(",") + 2, s.lastIndexOf("-") - 1);

			for (Author a : authors) {
				if (a.getName().equals(name)) {
					a.add(date);
				}
			}
		}
		sc.close();
	}

    // parse "idLog.txt" to create Author objects, calculates total commits
    private void parseID() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("idLog.txt"));
        while (sc.hasNextLine()) {
            this.metrics.totalCommits++;
            s = sc.nextLine();
            name = s.substring(s.indexOf("[") + 1, s.indexOf(","));
            namesList.add(name);
            email = s.substring(s.indexOf(",") + 2, s.lastIndexOf(","));
            idList.put(name, email);
        }
        for (Map.Entry<String, String> e : idList.entrySet()) {
            authors.add(new Author(e.getKey(), e.getValue(), this.metrics.totalCommits));
        }
        sc.close();

    }

	// creates local files to parse
	private void buildLogs() throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter idWriter = new PrintWriter("idLog.txt", "UTF-8");

		Stack<String> idStack = new Stack<>();

		itr = log.iterator();
		while (itr.hasNext()) {
			commit = itr.next();
			idStack.push(commit.getAuthorIdent().toString());

		}
		while (!idStack.empty()) {
			if (idStack.size() == 1) {
				idWriter.print(idStack.peek());

				idStack.pop();
			} else {
				idWriter.println(idStack.peek());

				idStack.pop();
			}
		}
		idWriter.close();

	}

    // return list of author objects
    public ArrayList<Author> returnAuthors() {
        return authors;
    }
}

// class to store each authors name and commit history
class Author {

	private String name, email;
	private int numCommits, total, frequency;
	private double percentage, days;
	Period diff;
	LinkedHashMap<String, String> commitMessages;
	ArrayList<String> commits;
	String initDate, endDate, initText, endText;

	public Author(String name, String email, int totalCommits) {
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
    public String getAge() {
        int temp = (int) days;
        if (days < 1)
            return "0 days";
        if (days == 1)
            return "1 day";
        return temp + " days";
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
    public String getFrequency() {
        if (frequency == 1)
            return frequency + " commit per month";

        return frequency + " commits per month";
    }

    // returns number of commits for the author
    public int getNumCommits() {
        return numCommits;
    }

    // returns percentage of commits in string form
    public String getPercentage() {
        this.percentage = (double) numCommits / (double) total * 100;
        return String.format("%.2f%%", percentage);
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