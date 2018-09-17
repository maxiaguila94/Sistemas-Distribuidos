package ui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.FilesController;

@SuppressWarnings("serial")
public class FilesPanel extends JPanel {
	
	FilesController controller;
	JTextField txtFiletext;
	
	public FilesPanel(FilesController controller) {
		this.controller = controller;
		
		
		txtFiletext = new JTextField();
		txtFiletext.setText("file_text");
		txtFiletext.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		txtFiletext.setColumns(10);
		this.add(txtFiletext);
		
		this.setBorder(javax.swing.BorderFactory.createTitledBorder(
				null, "TRANSFERIR ARCHIVOS AL SERVIDOR", 
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
				javax.swing.border.TitledBorder.DEFAULT_POSITION, 
				new java.awt.Font("DejaVu Sans", Font.PLAIN, 18), 
				new java.awt.Color(0, 0, 0))
			);
        
		JButton btnTransferirArchivos = new JButton("Transferir Archivos");
		btnTransferirArchivos.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		btnTransferirArchivos.addActionListener(this.controller);
		btnTransferirArchivos.setActionCommand("showJFC");
		this.add(btnTransferirArchivos);

	}

	public JTextField getTxtFiletext() {
		return txtFiletext;
	}
}
