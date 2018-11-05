import java.io.File;

public class FileCount {

	private int fileCount;

	public FileCount(String path) {

		fileCount = countFiles(path);
	}

	int countFiles(String path) {
		File dir = new File(path);
		int count = 0;
		for (File f : dir.listFiles()) {
			if (f.isDirectory()) {
				count += countFiles(f.getAbsolutePath());
			} else {
				count++;
			}
		}
		return count;
	}

	int getFileCount() {
		return fileCount;
	}
}
