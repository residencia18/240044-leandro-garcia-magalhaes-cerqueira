package cripto;

import java.io.FileOutputStream;
import java.io.IOException;

public class WriteBytes {
	
	public static void main(String[] arguments) {
		int[] data = { 71, 73, 70, 56, 57, 97, 13, 0, 12, 0, 145, 0,
		0, 255, 255, 255, 255, 255, 0, 0, 0, 0, 0, 0, 0, 44, 0,
		0, 0, 0, 13, 0, 12, 0, 0, 2, 38, 132, 45, 121, 11, 25,
		175, 150, 120, 20, 162, 132, 51, 110, 106, 239, 22, 8,
		160, 56, 137, 96, 72, 77, 33, 130, 86, 37, 219, 182, 230,
		137, 89, 82, 181, 50, 220, 103, 20, 0, 59 };
		try {
			FileOutputStream file = new FileOutputStream("coringa.png");
			for (int i = 0; i < data.length; i++)
				file.write(data[i]);
			file.close();
		} catch (IOException e) {
			System.out.println("Error -- " + e.toString());
		}
	}
}
