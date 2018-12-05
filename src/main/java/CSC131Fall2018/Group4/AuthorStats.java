package main.java.CSC131Fall2018.Group4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.revwalk.RevCommit;

// list of authors and their commit history, constructed during URL cloning
public class AuthorStats {

	Git git;
	Iterable<RevCommit> log;
	Iterator<RevCommit> itr;
	RevCommit commit;
	ArrayList<Author> authors = new ArrayList<>();
	ArrayList<String> namesList = new ArrayList<>();
	LinkedHashMap<String, String> idList = new LinkedHashMap<>();
	String s, name, email, message, date;
	private int totalCommits = 0;

	// builds a list of Author objects from the Git repository
	public AuthorStats(Git gitObject) throws NoHeadException, GitAPIException, IOException {
		git = gitObject;
		log = git.log().call();
		buildLogs();
		parseID();
		parseMsg();
	}
	
	// returns total commits in the repository
	public int getTotalCommits() {
		return totalCommits;
	}

	// returns toString of all authors and commit history
	public String toString() {
		String s = "";
		for (Author a : authors) {
			s += a.toString() + "\n";
		}
		return s;
	}

	// returns shorter toString of all authors without commit history
	public String toStringShort() {
		String s = "";
		for (Author a : authors) {
			s += a.toStringShort() + "\n";
		}
		return s;
	}

	// parse "msgLog.txt" to update commit history
	private void parseMsg() throws FileNotFoundException {
		Scanner sc = new Scanner(new File("msgLog.txt"));
		while (sc.hasNextLine()) {
			s = sc.nextLine();
			name = s.substring(s.indexOf("[") + 1, s.indexOf(","));
			date = s.substring(s.lastIndexOf(",") + 2, s.lastIndexOf("-") - 1);
			date = convert(date);
			message = sc.nextLine();
			for (Author a : authors) {
				if (a.getName().equals(name)) {
					a.add(date, message);
				}
			}
		}
		sc.close();
	}

	// parse "idLog.txt" to create Author objects, calculates total commits
	private void parseID() throws FileNotFoundException {
		Scanner sc = new Scanner(new File("idLog.txt"));
		while (sc.hasNextLine()) {
			totalCommits++;
			s = sc.nextLine();
			name = s.substring(s.indexOf("[") + 1, s.indexOf(","));
			namesList.add(name);
			email = s.substring(s.indexOf(",") + 2, s.lastIndexOf(","));
			idList.put(name, email);
		}
		for (Map.Entry<String, String> e : idList.entrySet()) {
			authors.add(new Author(e.getKey(), e.getValue(), totalCommits));
		}
		sc.close();

	}

	// creates local files to parse
	private void buildLogs() throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter idWriter = new PrintWriter("idLog.txt", "UTF-8");
		PrintWriter msgWriter = new PrintWriter("msgLog.txt", "UTF-8");
		Stack<String> idStack = new Stack<>();
		Stack<String> msgStack = new Stack<>();
		itr = log.iterator();
		while (itr.hasNext()) {
			commit = itr.next();
			idStack.push(commit.getAuthorIdent().toString());
			msgStack.push(commit.getShortMessage());
		}
		while (!idStack.empty()) {
			if (idStack.size() == 1) {
				idWriter.print(idStack.peek());
				msgWriter.println(idStack.peek());
				msgWriter.print(msgStack.pop());
				idStack.pop();
			} else {
				idWriter.println(idStack.peek());
				msgWriter.println(idStack.peek());
				msgWriter.println(msgStack.pop());
				idStack.pop();
			}
		}
		idWriter.close();
		msgWriter.close();
	}

	// return list of author objects
	public ArrayList<Author> returnAuthors() {
		return authors;
	}

	// convert text date to numerical
	@SuppressWarnings("deprecation")
	private String convert(String date) {
		Date day = new Date(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd '@' K:mma");
		return sdf.format(day);
	}
}

// class to store each authors name and commit history
class Author {

	private String name, email, frequency;
	private int numCommits, total;
	private double percentage;
	LinkedHashMap<String, String> commitMessages;
	String initDate, endDate;

	public Author(String name, String email, int totalCommits) {
		this.name = name;
		this.email = email;
		this.initDate = "null";
		this.endDate = "null";
		this.numCommits = 0;
		this.total = totalCommits;
		this.commitMessages = new LinkedHashMap<>();
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
	// returns frequency of commits
	public String getFrequency() {
		return frequency;
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
	
	// updates commit history for author
	public void add(String date, String msg) {
		if (commitMessages.size() == 0) {
			initDate = date;
		}
		commitMessages.put(date, msg);
		endDate = date;
		numCommits++;
	}

	// toString format: "name (email) : ? commits since ????-??-?? @ ??:??AM/PM \n
	// history: ..."
	public String toString() {
		String s = String.format("%s (%s) : %d commits since %s\nhistory:\n", name, email, numCommits, initDate);
		for (Map.Entry<String, String> e : commitMessages.entrySet()) {
			s += String.format("(%s) %s\n", e.getKey(), e.getValue());
		}
		return s;
	}

	// toString format: "name (email : ? commits since ????-??-?? @ ??:??AM/PM"
	public String toStringShort() {
		return String.format("%s (%s) : %d commits since %s", name, email, numCommits, initDate);
	}

}
