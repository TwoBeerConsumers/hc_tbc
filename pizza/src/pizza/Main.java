package pizza;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
	final static Charset ENCODING = StandardCharsets.UTF_8;
	int R, C, L, H;
	
	public static void main(String[] args) throws IOException {
		Main test = new Main();
		char[][] pizza = test.loadPizza("small.in");
		test.displayPizza(pizza);
	}

	private void calculateSlices(char[][] pizza) {
//		int[][] coords
//		int i=0;
//		while (i < C-H) {
//			int 
//		}
	}
	
	private void displayPizza(char[][] pizza) {
		System.out.println("R="+R+" / C="+C + " / L=" + L + " / H="+ H);
		
		for (int i=0;i<R;i++) {
			for (int j=0; j<C; j++) {
				System.out.print(pizza[i][j]);
			}
			System.out.println();
		}
	}

	private char[][] loadPizza(String filename) throws IOException {
		char[][] pizza = null;
		Path path = Paths.get(filename);
		try (Scanner scanner = new Scanner(path, ENCODING.name())) {
			R = scanner.nextInt();
			C = scanner.nextInt();
			L = scanner.nextInt();
			H = scanner.nextInt();
			
			scanner.nextLine();
			
			pizza = new char[R][C];
			for (int i=0;i<R;i++) {
				String row = scanner.nextLine();
				for (int j=0; j<C; j++) {
					pizza[i][j] = row.charAt(j);
				}
			}
			scanner.close();
		}
		return pizza;
	}
}
