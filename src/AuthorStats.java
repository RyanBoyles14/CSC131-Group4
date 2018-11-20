import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Stack;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.revwalk.RevCommit;

public class AuthorStats {

	Git git;
	PrintWriter writer;
	Iterable<RevCommit> log;
	Iterator<RevCommit> itr;
	RevCommit commit;
	Stack<String> stack;

	public AuthorStats(Git gitObject) throws NoHeadException, GitAPIException, IOException {
		git = gitObject;
		log = git.log().call();
		buildIDLog();

	}

	private void buildIDLog() throws FileNotFoundException, UnsupportedEncodingException {
		writer = new PrintWriter("idLog.txt", "UTF-8");
		itr = log.iterator();
		stack = new Stack<>();
		while (itr.hasNext()) {
			commit = itr.next();
			stack.push(commit.getAuthorIdent().toString());
		}
		while (!stack.empty()) {
			writer.println(stack.pop());
		}
		writer.close();
	}
}
