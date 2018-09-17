package ui;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controllers.MainController;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class SidebarPanel extends JPanel{
	
	public JButton btnConectar;
	public JButton btnLogin;
	public JButton btnSignUp;
	public JButton btnFileTransfer;
	private JButton btnError;
	
	public SidebarPanel(MainController controller) {
		this.setBackground(SystemColor.window);
		this.setLayout(new MigLayout("fillx", "[grow,center]", "[grow 30,fill][][][][][][grow,fill]"));

		this.btnConectar = new JButton("Connect");
		this.btnConectar.setIcon(new ImageIcon(this.getClass().getResource("/ui/icons/rss-symbol.png")));
		this.btnConectar.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		this.add(this.btnConectar, "cell 0 1,grow");
		// event handler 
		this.btnConectar.addActionListener(controller);
		this.btnConectar.setActionCommand("showConnectionPanel");
		
		
		this.btnLogin = new JButton("Login");
		this.btnLogin.setIcon(new ImageIcon(this.getClass().getResource("/ui/icons/login.png")));
		this.btnLogin.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		this.add(this.btnLogin, "cell 0 2,grow");		
		// event handler
		this.btnLogin.addActionListener(controller);
		this.btnLogin.setActionCommand("showLoginPanel");
		
		this.btnSignUp = new JButton("Sign up");
		this.btnSignUp.setIcon(new ImageIcon(this.getClass().getResource("/ui/icons/icon.png")));
		this.btnSignUp.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		this.add(btnSignUp, "cell 0 3,grow");
		// event handler
		this.btnSignUp.addActionListener(controller);
		this.btnSignUp.setActionCommand("showSignUpPanel");
		
		this.btnFileTransfer = new JButton("Files");
		this.btnFileTransfer.setIcon(new ImageIcon(this.getClass().getResource("/ui/icons/file.png")));
		this.btnFileTransfer.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		this.add(btnFileTransfer, "cell 0 4,grow");
		// event handler
		this.btnFileTransfer.addActionListener(controller);
		this.btnFileTransfer.setActionCommand("showRemoteFilesPanel");
		
	}	
}
