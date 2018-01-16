import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;

public class Driver extends JFrame{

	public static void main(String[] args){
		
		StartupWindow origWindow = new StartupWindow();
		origWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		origWindow.setSize(1000, 1000);
		origWindow.setVisible(true);
		
		
	}
}
