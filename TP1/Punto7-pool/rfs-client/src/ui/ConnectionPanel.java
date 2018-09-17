package ui;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.ConnectionController;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class ConnectionPanel extends JPanel {
	
	public JLabel lblHostname;
	public JTextField txtHostName;
	public JTextField txtPuerto;
	public JButton btnConectar_1;
	public JLabel lblPuerto;
	
	public ConnectionController controller;
	
	public ConnectionPanel(ConnectionController controller) {
		this.controller = controller;
		
		this.setVisible(true);
		this.setLayout(new MigLayout("", "[][grow]", "[grow 40,fill][][][][grow]"));
		this.lblHostname = new JLabel("IP");
		this.txtHostName = new JTextField("localhost");
		this.lblPuerto = new JLabel("Puerto");
		this.txtPuerto = new JTextField("7896");
		this.btnConectar_1 = new JButton("Conectar");
		this.lblHostname.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		this.add(lblHostname, "cell 0 1");
		
		
		this.txtHostName.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		this.add(txtHostName, "cell 1 1,growx");
		this.txtHostName.setColumns(10);
		
		
		this.lblPuerto.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		this.add(lblPuerto, "cell 0 2,alignx trailing");
		
		
		this.txtPuerto.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		this.add(txtPuerto, "cell 1 2,growx");
		this.txtPuerto.setColumns(10);
		
		
		this.btnConectar_1.setFont(new Font("DejaVu Sans", Font.PLAIN, 18));
		this.btnConectar_1.setIcon(new ImageIcon(MainUI.class.getResource("/ui/icons/login.png")));
		this.btnConectar_1.addActionListener(this.controller);
		this.add(btnConectar_1, "cell 1 3");
	}
	
	public String getHostname() {
		return this.txtHostName.getText();
	}
	
	public String getPort() {
		return this.txtPuerto.getText();
	}
}


