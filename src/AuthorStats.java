import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.revwalk.RevCommit;

public class AuthorStats {
	
	Git git;
	PrintWriter writer;
	Iterable<RevCommit> log;
	Iterator<RevCommit> itr;
	
	public AuthorStats(Git gitObject) throws NoHeadException, GitAPIException, IOException {
		this.git = gitObject;
		this.log = git.log().call();
		this.itr = log.iterator();
		this.writer = new PrintWriter("log.txt", "UTF-8");
		RevCommit commit;
		while (itr.hasNext()) {
			commit = itr.next();
			writer.println(commit.getAuthorIdent());
		}
		writer.close();
	}
}
