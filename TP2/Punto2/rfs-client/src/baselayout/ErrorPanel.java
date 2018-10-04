package baselayout;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

public class ErrorPanel extends JFrame{
	
	
	public ErrorPanel(String errorMessage) {
		JLabel errorMesage = new JLabel(errorMessage);
		errorMesage.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		this.getContentPane().add(errorMesage);
	}
	
	
	
}
