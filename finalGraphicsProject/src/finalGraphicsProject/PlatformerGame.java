package finalGraphicsProject;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
public class PlatformerGame {

	public static void main(String[] args) {
		MainFrame frame = new MainFrame(); 
		frame.setSize(700,700);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // holds x and y sizes for panel
		
		frame.setLocation((int)(screenSize.getWidth()/2 - frame.getSize().getWidth()/2), (int)(screenSize.getHeight()/2 - frame.getSize().getHeight()/2));

		frame.setResizable(false);// prevents the frame from getting changed in size
		
		frame.setTitle("Akhil's Final Graphics Project"); 
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);// stops program when "x" button is hit
		
		
		
	
		
		
	}

}
