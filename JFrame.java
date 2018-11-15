import javax.swing.JFrame;

public class Window {
	// creates the height and width variables
	// private means only Window class can access them
	private static int HEIGHT = 600;
	private static int WIDTH = 480;
	
	//main method
	public static void main(String args[]) {
		// create the frame
		JFrame frame = new JFrame("Frame Title");
		// set the size of the frame to our variables
		frame.setSize(HEIGHT, WIDTH);
		// necessary for closing, allows the exit button to work
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// allows the frame to be seen
		frame.setVisible(true);
		// centers the frame (default is top-left corner)
		frame.setLocationRelativeTo(null);
		
	}
	
}	
