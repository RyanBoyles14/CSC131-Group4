package CSC131Fall2018.Group4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;

public class Repository implements AutoCloseable {

	private Git git;
	private ArrayList<File> list = new ArrayList<>();
	private ArrayList<Class> classes = new ArrayList<>();
	private AuthorStats authorStats;
	
	// constructor clones repository from GitHub URL to a temporary directory
	public Repository(String url)
			throws InvalidRemoteException, TransportException, GitAPIException, IOException
	{
		this.git = Git.cloneRepository().setURI(url).setDirectory(Files.createTempDirectory(null).toFile()).call();
		this.buildList();
		this.authorStats = new AuthorStats(git);
	}
	
	// returns AuthorStats object for the repo
	public AuthorStats getAuthorStats() {
		return authorStats;
	}
	
	// builds ArrayList of files in the repository
	private void buildList()
	{
		for (File f : this.git.getRepository().getWorkTree().listFiles()) {
			if (f.isDirectory()) {
				if (!f.getName().equals(".git"))
				buildList(f);
			} else {
				list.add(f);
			}
		}
	}

	// builds ArrayList of files in given directory
	private void buildList(File directory)
	{
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

	// deletes the directory of the cloned repository
	@Override
	public void close() throws Exception
	{
		File repositoryDirectory = this.git.getRepository().getWorkTree();
		this.git.getRepository().close();
		this.deleteDirectory(repositoryDirectory);
	}
	
	// deletes a file directory
	private void deleteDirectory(File directory)
	{
		for (File f : directory.listFiles()) {
			if (f.isDirectory()) {
				deleteDirectory(f);
				f.delete();
			} else {
				f.delete();
			}
		}
		directory.delete();
	}

}
