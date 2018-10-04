package localfiles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import client.RFSClient;

public class LocalFilesController implements ActionListener{

	LocalFilesPanel view;
	RFSClient model;
	
	public LocalFilesController(RFSClient moddel) {
		// TODO Auto-generated constructor stub
		this.model = moddel;
	}
	
	public void setView(LocalFilesPanel view) {
		this.view = view;
	}
	
	public LocalFilesPanel getView() {
		return view;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JFileChooser jfc = new JFileChooser();
		

		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int evento = jfc.showOpenDialog(this.getView());
		System.out.println(evento);
		if(evento == JFileChooser.APPROVE_OPTION) {
			File fichero = jfc.getSelectedFile();
			String file_name = jfc.getName(fichero);
			
			
			// TODO: acá puede ser un lugar donde controlar el hash
			this.model.writeFileToServer(fichero);
			
			
			this.getView().getTxtFiletext().setText(fichero.getAbsolutePath());
		}
	}

}
