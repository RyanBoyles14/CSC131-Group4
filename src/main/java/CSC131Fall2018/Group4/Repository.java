package CSC131Fall2018.Group4;

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
	
	// constructor clones repository from GitHub URL to any local file path
	public Repository(String url, String localPath)
			throws InvalidRemoteException, TransportException, GitAPIException, IOException {

		this.dir = new File(localPath);
		this.git = Git.cloneRepository().setURI(url).setDirectory(dir).call();
		buildList(dir);
		this.authorStats = new AuthorStats(git);
		this.git.getRepository().close();
	}
	
	// returns AuthorStats object for the repo
	public AuthorStats getAuthorStats() {
		return authorStats;
	}
	
	// builds ArrayList of files in the repository
	private void buildList(File directory) {
		for (File f : directory.listFiles()) {
			if (f.isDirectory()) {
				if (!f.getName().equals(".git"))
				buildList(f);
			} else {
				list.add(f);
			}
		}
	}
	
	// return list of files
	public ArrayList<File> getList() {
		return list;
	}

	// return file count
	public int getFileCount() {
		return list.size();
	}
	
	// deletes the cloned directory, should be used on exit since JGit will not overwrite
	public void delete() {
		delete(dir);
	}
	
	// deletes a file directory
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
