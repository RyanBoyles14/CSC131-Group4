package CSC131Fall2018.Group4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;

public class Repository implements AutoCloseable
{
	public class Metrics implements IMetrics
	{
		public int fileCount;
		public int totalCommits;
	}

	private Git git;
	private ArrayList<File> fileList = new ArrayList<>();
	private ArrayList<Class> classes = new ArrayList<>();
	public ContributorBuilder contributorBuilder;
	public Contributors contributors = new Contributors();
	public DepthOfInheritance depthOfInheritance = new DepthOfInheritance();
	public Halstead halstead = new Halstead();
	public TimeComplexity timeComplexity = new TimeComplexity();
	public Repository.Metrics metrics = this.new Metrics();
	
	// constructor clones repository from GitHub URL to a temporary directory
	public Repository(String url)
			throws InvalidRemoteException, TransportException, GitAPIException, IOException
	{
		this.git = Git.cloneRepository().setURI(url).setDirectory(Files.createTempDirectory(null).toFile()).call();
		this.buildList();
		this.contributorBuilder = new ContributorBuilder(git);
		((Contributors.Metrics) this.contributors.getMetrics()).contributors = this.contributorBuilder.returnContributors();
		this.calculateAllMetrics();
	}

	// deletes the directory of the cloned repository
	@Override
	public void close() throws Exception
	{
		File repositoryDirectory = this.git.getRepository().getWorkTree();
		this.git.getRepository().close();
		this.deleteDirectory(repositoryDirectory);
	}

	// return list of files
	public ArrayList<File> getList() {
		return fileList;
	}

	public ArrayList<Contributor> getContributors()
	{
		return this.contributorBuilder.contributors;
	}

	public IMetrics getContributorsMetrics()
	{
		//List<IMetrics> listMetrics = new ArrayList<>();
		//this.contributorBuilder.contributors.forEach(contributor -> listMetrics.add(contributor.getMetrics()));
		return this.contributors.getMetrics();
	}

	public IMetrics getDepthOfInheritanceMetrics()
	{
		return this.depthOfInheritance.getMetrics();
	}

	public IMetrics getHalsteadMetrics()
	{
		return this.halstead.getMetrics();
	}

	public IMetrics getTimeComplexityMetrics()
	{
		return this.timeComplexity.getMetrics();
	}

	public IMetrics getRepositoryMetrics()
	{
		return this.metrics;
	}

	private void calculateAllMetrics()
	{
		Set implementations = AbstractMetricsCalculator.getAllImplementations();
		for (Object implementation : implementations)
		{
			try
			{
				// construction of AbstractMetricsCalculator should write to their respective metrics variables
				AbstractMetricsCalculator calculator = (AbstractMetricsCalculator) ((java.lang.Class) implementation).getConstructor(Repository.class).newInstance(this);
			}
			catch (Exception e)
			{
				continue;
			}
		}
	}

	// builds ArrayList of files in the repository
	private void buildList()
	{
		for (File f : this.git.getRepository().getWorkTree().listFiles()) {
			if (f.isDirectory()) {
				if (!f.getName().equals(".git"))
				buildList(f);
			} else {
				fileList.add(f);
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
				fileList.add(f);
			}
		}
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
