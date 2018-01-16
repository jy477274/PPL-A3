import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.*;
public class StartupWindow extends JFrame{
	
	private JTextField item1;
	private JTextField item2;
	private JTextField item3;
	private JPasswordField passField;
	

	public StartupWindow(){
		super("Login");
		setLayout(new FlowLayout());
		item1 = new JTextField(10);
		add(item1);

		item2 = new JTextField("Enter text here");
		add(item2);
		
		passField = new JPasswordField("MyPass");
		add(passField);
	}

}
