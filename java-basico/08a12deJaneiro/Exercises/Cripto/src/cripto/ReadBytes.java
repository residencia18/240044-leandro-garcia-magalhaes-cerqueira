package cripto;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadBytes {
	public static void main(String[] arguments) {
		try {
			FileInputStream file = new FileInputStream("coringa.png");
			boolean eof = false; int count = 0;
			while (!eof) {
				int input = file.read();
				if (input != -1) {
					System.out.print(input + " ");
					count++;
				} else eof = true;
			}
			file.close();
			System.out.println("\nBytes lidos: " + count);
		} catch (IOException e) {
			System.out.println("Error -- " + e.toString());
		}
	}
}
