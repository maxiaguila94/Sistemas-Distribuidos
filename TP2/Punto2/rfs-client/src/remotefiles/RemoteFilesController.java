package remotefiles;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import client.RFSClient;
import remoteinterfaces.FileMetadata;

public class RemoteFilesController implements ActionListener{

	RemoteFilesPanel view;
	RFSClient model;
	List<FileMetadata> files = new ArrayList<FileMetadata>();
	
	public RemoteFilesController(RFSClient model) {
		this.model = model;
	}
	
	public void setView(RemoteFilesPanel view) {
		this.view = view;
	}
	public RFSClient getModel() {
		return this.model;
	}
	
	
	@Override
	public void actionPerformed (ActionEvent e) {
		
		FileMetadata file = (FileMetadata) this.view.getFile(e.getActionCommand());

		if(file != null) {
			SingleRemoteFileController controller = new SingleRemoteFileController(this.getModel());
			SingleRemoteFilePanel view = new SingleRemoteFilePanel(new JFrame(), true, file, controller);
		}
     }
	


	
	public List<FileMetadata> getAvailableFiles(){		
		return this.getModel().getAvailableFiles();
		
	}
}

@SuppressWarnings("serial") class Dialogz extends JDialog{
	JButton close = new JButton("close");
	
	public Dialogz(JFrame owner,boolean modal, String command) {
		super(owner, modal);
		setSize(800, 400);
		System.out.println(this.getModalityType());
		close.setText(command);
		add(close);
		setLocationRelativeTo(owner);
		setVisible(true);
		
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
				closez();
			}
		});
	} 
	
	void closez(){
		setModal(false);
		this.dispose();
		System.out.println("Method Done");
		
	}
	
}