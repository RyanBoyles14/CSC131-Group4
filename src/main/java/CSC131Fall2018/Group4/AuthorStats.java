package main.java.CSC131Fall2018.Group4;

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
    int totalCommits = 0;
    Git git;
    Iterable<RevCommit> log;
    Iterator<RevCommit> itr;
    RevCommit commit;
    ArrayList<Contributor> contributors = new ArrayList<>();
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

			for (Contributor a : contributors) {
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
            totalCommits++;
            s = sc.nextLine();
            name = s.substring(s.indexOf("[") + 1, s.indexOf(","));
            namesList.add(name);
            email = s.substring(s.indexOf(",") + 2, s.lastIndexOf(","));
            idList.put(name, email);
        }
        for (Map.Entry<String, String> e : idList.entrySet()) {
            contributors.add(new Contributor(e.getKey(), e.getValue(), totalCommits));
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
    public ArrayList<Contributor> returnContributors() {
        return contributors;
    }
}
