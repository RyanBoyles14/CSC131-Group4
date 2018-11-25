import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
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
	HashSet<String> namesList = new HashSet<>();
	String s, name, email;

	// builds a list of Author objects from the Git repository
	public AuthorStats(Git gitObject) throws NoHeadException, GitAPIException, IOException {
		git = gitObject;
		log = git.log().call();
		buildLogs();
		parseID();

	}
	
	// parse "idLog.txt" to create Author objects
	private void parseID() throws FileNotFoundException {
		Scanner sc = new Scanner(new File("idLog.txt"));
		while (sc.hasNextLine()) {
			s = sc.nextLine();
			name = s.substring(s.indexOf("[") + 1, s.indexOf(","));
			email = s.substring(s.indexOf(",") + 2, s.lastIndexOf(","));
			namesList.add(name);
			if (!namesList.contains(name)) {
				authors.add(new Author(name, email));
			}
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
			idWriter.println(idStack.peek());
			msgWriter.println(idStack.peek());
			msgWriter.println(msgStack.pop());
			idStack.pop();
		}
		idWriter.close();
		msgWriter.close();
	}

	public ArrayList<Author> returnAuthors() {
		return authors;
	}
}

// class to store each authors name and commit history
class Author {

	String name, email;
	int numCommits;
	ArrayList<String> commitMessages;

	public Author(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public void add(String msg) {
		commitMessages.add(msg);
		numCommits++;
	}

	public String toString() {
		String s = String.format("Name: %s\nEmail: %s\n Commits: %d\nHistory:\n", name, email, numCommits);
		for (String m : commitMessages) {
			s += m + "\n";
		}
		return s;
	}

}
