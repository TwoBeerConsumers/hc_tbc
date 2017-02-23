package pizza;

public class Main {
	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		int i = 0;
		while (true) {
			System.out.println("i= "+ i);
			i++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
