package ui;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.SignUpController;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class SignUpPanel extends JPanel{

	private JLabel username;
	private JTextField txtUserName;
	private JLabel lblPassword;
	private JTextField txtPassword;
	private JButton btnConectar_1;
	
	private SignUpController controller;
	
	public SignUpPanel(SignUpController controller) {
		this.controller = controller;
		
		this.setVisible(true);
		this.setLayout(new MigLayout("", "[][grow]", "[grow 40,fill][][][][grow]"));
		this.username = new JLabel("Username");
		username.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		this.add(username, "cell 0 1,alignx trailing");
		
		this.txtUserName = new JTextField();
		this.txtUserName.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		this.add(txtUserName, "cell 1 1,growx");
		this.txtUserName.setColumns(10);
		
		this.lblPassword = new JLabel("Password");
		this.lblPassword.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		this.add(lblPassword, "cell 0 2,alignx trailing");
		
		this.txtPassword = new JTextField();
		this.txtPassword.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		this.add(txtPassword, "cell 1 2,growx");
		this.txtPassword.setColumns(10);
		
		this.btnConectar_1 = new JButton("Create acaunt");
		this.btnConectar_1.setFont(new Font("DejaVu Sans", Font.PLAIN, 18));
		this.btnConectar_1.setIcon(new ImageIcon(MainUI.class.getResource("/ui/icons/login.png")));
		this.btnConectar_1.addActionListener(this.controller);
		this.add(btnConectar_1, "cell 1 3");		
	}
	
	public String getUsername() {
		return this.txtUserName.getText();
	}
	
	public String getPassword() {
		return this.txtPassword.getText();
	}
	
	
}
