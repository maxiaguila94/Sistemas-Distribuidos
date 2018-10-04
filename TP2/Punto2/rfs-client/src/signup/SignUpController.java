package signup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import baselayout.ErrorPanel;
import client.RFSClient;

public class SignUpController implements ActionListener{

	
	private RFSClient model;
	private SignUpPanel view;

	public SignUpController(RFSClient model) {
		this.model = model;
	}
	
	public void setView(SignUpPanel view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String username = this.view.getUsername();
		String password = this.view.getPassword();
		try {
			this.model.signUp(username, password);
			JOptionPane.showMessageDialog(new JFrame(), "Signup succeded", "Conection Success!!", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
//			e.printStackTrace();
			JOptionPane.showMessageDialog(new ErrorPanel(e.toString()), e.toString(), "Conection Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	
}
