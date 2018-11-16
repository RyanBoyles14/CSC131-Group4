import java.io.*;

public class Complex {
	File f;
	public Complex(File f) {
		this.f = f;
	}
	
	public void findMethods(File f) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(f));
		Boolean isMethod = false;
		String line;
		

		while (br.readLine() != null) {
			line = br.readLine();
			if(line.startsWith("public")) {
				isMethod = true;
			}
		}
	}
}
