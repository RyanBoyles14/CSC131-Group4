import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;

public class Repository {

	File dir;
	Git git;
	ArrayList<File> list = new ArrayList<>();
	AuthorStats authorStats;

	public Repository(String url, String localPath)
			throws InvalidRemoteException, TransportException, GitAPIException, IOException {

		this.dir = new File(localPath);
		this.git = Git.cloneRepository().setURI(url).setDirectory(dir).call();
		buildList(dir);
		this.authorStats = new AuthorStats(git);
		this.git.getRepository().close();
	}

	private void buildList(File directory) {
		for (File f : directory.listFiles()) {
			if (f.isDirectory()) {
				buildList(f);
			} else {
				list.add(f);
			}
		}
	}

	public ArrayList<File> getList() {
		return list;
	}

	public int getFileCount() {
		return list.size();
	}

	public void delete() {
		delete(dir);
	}

	private void delete(File directory) {
		for (File f : directory.listFiles()) {
			if (f.isDirectory()) {
				delete(f);
				f.delete();
			} else {
				f.delete();
			}
		}
		dir.delete();
	}

}
