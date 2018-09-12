import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PruebaUI {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnRecuperarContrasea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PruebaUI window = new PruebaUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PruebaUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow][][][grow]", "[grow][][][][grow]"));
		
		JLabel lblUsername = new JLabel("Username");
		frame.getContentPane().add(lblUsername, "cell 1 1,alignx trailing");
		
		textField = new JTextField();
		frame.getContentPane().add(textField, "cell 2 1,growx");
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		frame.getContentPane().add(lblPassword, "cell 1 2,alignx trailing");
		
		textField_1 = new JTextField();
		frame.getContentPane().add(textField_1, "cell 2 2,growx");
		textField_1.setColumns(10);
		
		JButton btnLogin = new JButton("login");
		frame.getContentPane().add(btnLogin, "cell 1 3");
		
		btnRecuperarContrasea = new JButton("recuperar contraseña");
		frame.getContentPane().add(btnRecuperarContrasea, "cell 2 3");
	}

}
