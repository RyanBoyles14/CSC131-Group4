package com.CSC131Fall2018.Group4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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

	// builds a list of Author objects from the Git repository
	public AuthorStats(Git gitObject) throws NoHeadException, GitAPIException, IOException {
		git = gitObject;
		log = git.log().call();
		buildLogs();
		parseID();
		parseMsg();
	}

	// parse "msgLog.txt" to update commit history
	private void parseMsg() throws FileNotFoundException {
		Scanner sc = new Scanner(new File("msgLog.txt"));
		while (sc.hasNextLine()) {
			s = sc.nextLine();
			name = s.substring(s.indexOf("[") + 1, s.indexOf(","));
			message = sc.nextLine();
			/*for (Author a : authors) {
				if (a.name.equals(name)) {
					a.add(message);
				}
			}*/
		}
		sc.close();
	}

	// parse "idLog.txt" to create Author objects
	private void parseID() throws FileNotFoundException {
		Scanner sc = new Scanner(new File("idLog.txt"));
		while (sc.hasNextLine()) {
			s = sc.nextLine();
			name = s.substring(s.indexOf("[") + 1, s.indexOf(","));
			namesList.add(name);
			email = s.substring(s.indexOf(",") + 2, s.lastIndexOf(","));
			idList.put(name, email);
		}
		for (Map.Entry<String, String> e : idList.entrySet()) {
			authors.add(new Author(e.getKey(), e.getValue()));
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
}

// class to store each authors name and commit history
class Author {

	String name, email;
	int numCommits;
	LinkedHashMap<String, String> commitMessages;
	String initDate;

	public Author(String name, String email) {
		this.name = name;
		this.email = email;
		this.numCommits = 0;
		this.commitMessages = new LinkedHashMap<>();
	}

	public void add(String date, String msg) {
		commitMessages.put(date, msg);
		numCommits++;
	}

	// toString format: "name (email) : ? commits since ????-??-?? \n history: ..."
	public String toString() {
		String s = String.format("%s (%s) : %d commits since %s\nhistory:\n", name, email, numCommits,
				initDate);
		/*for (String m : commitMessages) {
			s += m + "\n";
		}*/
		return s;
	}

	// convert text date to numerical
	private String convertDate(String date) {
		return null;
	}

}
