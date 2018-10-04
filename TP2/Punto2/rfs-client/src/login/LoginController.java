package login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import baselayout.ErrorPanel;
import client.RFSClient;


public class LoginController implements ActionListener {
	
	private RFSClient model;
	private LoginPanel view;

	public LoginController(RFSClient model) {
		this.model = model;
	}
	
	public void setView(LoginPanel view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String username = this.view.getUsername();
		String password = this.view.getPassword();
		try {
			
			this.model.login(username, password);
			JOptionPane.showMessageDialog(new JFrame(), "Login succeded", "Conection Success!!", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new ErrorPanel(e.toString()), e.toString(), "Conection Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	
	
}
