package conection;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import baselayout.ErrorPanel;
import client.RFSClient;

public class ConnectionController implements ActionListener{
	private RFSClient model;
	private ConnectionPanel view;

	public ConnectionController(RFSClient model) {
		this.model = model;
	}
	
	public void setView(ConnectionPanel view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String hostname = this.view.getHostname();
		String port = this.view.getPort();
		try {			
			this.model.connect(hostname, port);
			JOptionPane.showMessageDialog(new JFrame(), "Connection succeded to :"+hostname+":"+port, "Conection Success!!", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new ErrorPanel(e.toString()), e.toString(), "Conection Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
