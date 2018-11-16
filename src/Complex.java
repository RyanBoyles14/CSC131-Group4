import java.io.*;
import java.util.List;

public class Complex {
	File f;
	public Complex(File f) {
		this.f = f;
	}
	
	public void findMethods(File f) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(f));
		Boolean isMethod = false;
		String line;
		List<String> list = new ArrayList<String>; 

		while (br.readLine() != null) {
			line = br.readLine();
			if(line.startsWith("public")) {
				isMethod = true;
				list.add(line);
			}
		}
	}
}
