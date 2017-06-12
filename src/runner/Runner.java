package runner;

public class Runner {
	
	public static MainLoop loop;
	
	public static void main(String[] args) {
		loop = new MainLoop();
		try {
			loop.play();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

