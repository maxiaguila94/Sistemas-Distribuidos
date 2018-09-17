package ui;


import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import remoteobjects.FileMetadata;
import controllers.SingleRemoteFileController;

public class SingleRemoteFilePanel extends JDialog{
	
	private JTextField txtRemoteFileName;
	private JTextField txtRemoteSize;
	private JTextField txtRemoteLastModified;
	private JTextField txtRemoteLastAccessTime;
	private JTextField txtRemoteCreationTime;
	private JTextField txtRemoteStatus;
	private JTextField txtLocalFilename;
	private JTextField txtLocalSize;
	private JTextField txtLocalLastmodified;
	private JTextField txtLocalLastaccesstime;
	private JTextField txtLocalCreationDate;
	private JTextField txtLocalStatus;
	private JButton RemoteOpen;
	private JButton RemoteWrite;
	private JButton RemoteRead;
	private JButton RemoteClose;
	private JButton LocalWrite;
	
	private FileMetadata remote_file;
	private FileMetadata local_file;
	private SingleRemoteFileController controller;
	
	
	public SingleRemoteFilePanel(JFrame owner,boolean modal, FileMetadata remote_file, SingleRemoteFileController controller) {
		super(owner, modal);
		this.controller = controller;
		this.remote_file = remote_file;
		
	
		setSize(900, 400);
		
		setLocationRelativeTo(owner);
		this.getContentPane().setLayout(new MigLayout("", "[grow][grow]", "[grow]"));

		this.initialize();
		
		setVisible(true);
		
	} 
	
	public FileMetadata getRemoteFileMetadata() {
		return this.remote_file;
	}
	
	private void initialize() {
		// TODO Auto-generated method stub
		
		//Panel Izquierdo
				JPanel panel_2 = new JPanel();
				this.getContentPane().add(panel_2, "cell 1 0,grow");
				panel_2.setLayout(new MigLayout("", "[grow][][][grow]", "[grow][grow][][][][][][][][][][][]"));
				panel_2.setBorder(javax.swing.BorderFactory.createTitledBorder(
		        		null, 
		        		"ARCHIVO REMOTO", 
		        		javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
		        		javax.swing.border.TitledBorder.DEFAULT_POSITION, 
		        		new java.awt.Font("DejaVu Sans", Font.PLAIN, 18), 
		        		new java.awt.Color(0, 0, 0))
		        	);
				panel_2.setVisible(true);
				
				
				JPanel panel_5 = new JPanel();
				panel_2.add(panel_5, "cell 0 0,grow");
				panel_5.setLayout(new MigLayout("", "[grow][grow]", "[grow][grow][grow][grow][grow][grow]"));
				
				JLabel lblRemoteFileName = new JLabel("File Name:");
				lblRemoteFileName.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
				panel_5.add(lblRemoteFileName, "cell 0 0,alignx trailing");
				
				txtRemoteFileName = new JTextField(remote_file.getFileName());
				txtRemoteFileName.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
				panel_5.add(txtRemoteFileName, "cell 1 0,growx");
				txtRemoteFileName.setColumns(10);
				
				JLabel lblRemoteSize = new JLabel("Size:");
				lblRemoteSize.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
				panel_5.add(lblRemoteSize, "cell 0 1,alignx trailing");
				
				txtRemoteSize = new JTextField(Long.toString(remote_file.getSize()));
				txtRemoteSize.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
				panel_5.add(txtRemoteSize, "cell 1 1,growx");
				txtRemoteSize.setColumns(10);
				
				JLabel lblLastModified = new JLabel("Last Modified:");
				lblLastModified.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
				panel_5.add(lblLastModified, "cell 0 2,alignx trailing");
				
				txtRemoteLastModified = new JTextField(remote_file.getLastAccessTime().toString());
				txtRemoteLastModified.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
				panel_5.add(txtRemoteLastModified, "cell 1 2,growx");
				txtRemoteLastModified.setColumns(10);
				
				JLabel lblLastAccessTime = new JLabel("Last Access Time:");
				lblLastAccessTime.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
				panel_5.add(lblLastAccessTime, "cell 0 3,alignx trailing");
				
				txtRemoteLastAccessTime = new JTextField(remote_file.parsedFileTimeToString(remote_file.getLastAccessTime()));
				txtRemoteLastAccessTime.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
				panel_5.add(txtRemoteLastAccessTime, "cell 1 3,growx");
				txtRemoteLastAccessTime.setColumns(10);
				
				JLabel lblCreationTime = new JLabel("Creation Time:");
				lblCreationTime.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
				panel_5.add(lblCreationTime, "cell 0 4,alignx trailing");
				
				txtRemoteCreationTime = new JTextField(remote_file.getCreationDate().toString());
				txtRemoteCreationTime.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
				panel_5.add(txtRemoteCreationTime, "cell 1 4,growx");
				txtRemoteCreationTime.setColumns(10);
				
				JLabel lblStatus = new JLabel("Status:");
				lblStatus.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
				panel_5.add(lblStatus, "cell 0 5,alignx trailing");
				
				txtRemoteStatus = new JTextField(remote_file.getStatus());
				txtRemoteStatus.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
				panel_5.add(txtRemoteStatus, "cell 1 5,growx");
				txtRemoteStatus.setColumns(10);
				
				JPanel panel_6 = new JPanel();
				panel_2.add(panel_6, "cell 0 1,grow");
				panel_6.setLayout(new MigLayout("", "[grow]", "[]"));
				
//				this.RemoteOpen = new JButton("Open");
//				this.RemoteOpen.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
//				panel_6.add(RemoteOpen, "cell 0 0");
								
//				this.RemoteWrite = new JButton("Write");
//				this.RemoteWrite.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
//				panel_6.add(RemoteWrite, "cell 1 0");

				this.RemoteRead = new JButton("Read");
				this.RemoteRead.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
				panel_6.add(RemoteRead, "cell 0 0");
				
//				this.RemoteClose = new JButton("Close");
//				this.RemoteClose.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
//				panel_6.add(RemoteClose, "cell 3 0");
				
				//Manejo de eventos
//				RemoteOpen.addActionListener(this.controller);
//				RemoteOpen.setActionCommand("rmtOpen");
//				RemoteWrite.addActionListener(this.controller);
//				RemoteWrite.setActionCommand("rmtWrite");
				RemoteRead.addActionListener(this.controller);
				RemoteRead.setActionCommand("rmtRead");
//				RemoteClose.addActionListener(this.controller);
//				RemoteClose.setActionCommand("rmtClose");
				
				//Panel Derecho
				
				this.local_file = this.controller.lookUpLocalCopy(this.remote_file.getFileName());
				
//				System.out.println(this.local_file);
//				System.out.println(this.local_file.getFileName());
//				System.out.println(this.local_file.getSize());
//				System.out.println(this.local_file.getCreationDate());
//				
				
				if(this.local_file != null) {
					
					JPanel panel_1 = new JPanel();
					this.getContentPane().add(panel_1, "cell 1 0,grow");
					panel_1.setLayout(new MigLayout("", "[grow][][][grow]", "[grow][grow][][][][][][][][][][][]"));
					panel_1.setBorder(javax.swing.BorderFactory.createTitledBorder(
			        		null, 
			        		"ARCHIVO LOCAL", 
			        		javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
			        		javax.swing.border.TitledBorder.DEFAULT_POSITION, 
			        		new java.awt.Font("DejaVu Sans", Font.PLAIN, 18), 
			        		new java.awt.Color(0, 0, 0))
			        	);
					panel_1.setVisible(true);
					
					JPanel panel_3 = new JPanel();
					panel_1.add(panel_3, "cell 0 0,grow");
				
					JPanel panel_4 = new JPanel();
					panel_1.add(panel_4, "cell 0 1,grow");
					panel_4.setLayout(new MigLayout("", "[grow]", "[]"));
					
					this.LocalWrite = new JButton("Write");
					this.LocalWrite.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
					panel_4.add(LocalWrite, "cell 0 0");
					panel_3.setLayout(new MigLayout("", "[grow][grow]", "[grow][grow][grow][grow][grow][grow]"));
					
					JLabel lblLocalFileName = new JLabel("File Name:");
					lblLocalFileName.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
					panel_3.add(lblLocalFileName, "cell 0 0,alignx trailing");
					
					txtLocalFilename = new JTextField(local_file.getFileName());
					txtLocalFilename.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
					panel_3.add(txtLocalFilename, "cell 1 0,growx");
					
					txtLocalFilename.setColumns(10);
					
					JLabel lblRemoteSizeder = new JLabel("Size:");
					lblRemoteSizeder.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
					panel_3.add(lblRemoteSizeder, "cell 0 1,alignx trailing");
					
					txtLocalSize = new JTextField(Long.toString(local_file.getSize()));
					txtLocalSize.setText("size");
					txtLocalSize.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
					panel_3.add(txtLocalSize, "cell 1 1,growx");
					txtLocalSize.setColumns(10);
					
					JLabel lblLastModifiedder = new JLabel("Last Modified:");
					lblLastModifiedder.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
					panel_3.add(lblLastModifiedder, "cell 0 2,alignx trailing");
					
					txtLocalLastmodified = new JTextField(local_file.getLastModifiedTime().toString());
					txtLocalLastmodified.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
					panel_3.add(txtLocalLastmodified, "cell 1 2,growx");
					txtLocalLastmodified.setColumns(10);
					
					JLabel lblLastAccessTiemeder = new JLabel("Last Access Tieme:");
					lblLastAccessTiemeder.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
					panel_3.add(lblLastAccessTiemeder, "cell 0 3,alignx trailing");
					
					txtLocalLastaccesstime = new JTextField(local_file.getLastAccessTime().toString());
					txtLocalLastaccesstime.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
					panel_3.add(txtLocalLastaccesstime, "cell 1 3,growx");
					txtLocalLastaccesstime.setColumns(10);
					
					JLabel lblCreationder = new JLabel("Creation Time:");
					lblCreationder.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
					panel_3.add(lblCreationder, "cell 0 4,alignx trailing");
					
					txtLocalCreationDate = new JTextField(local_file.getCreationDate().toString());
					txtLocalCreationDate.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
					panel_3.add(txtLocalCreationDate, "cell 1 4,growx");
					txtLocalCreationDate.setColumns(10);
					
					JLabel lblStatusder = new JLabel("Status:");
					lblStatusder.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
					panel_3.add(lblStatusder, "cell 0 5,alignx trailing");
					
					txtLocalStatus = new JTextField(local_file.getStatus());
					txtLocalStatus.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
					txtLocalStatus.setText("status");
					panel_3.add(txtLocalStatus, "cell 1 5,growx");
					txtLocalStatus.setColumns(10);
					LocalWrite.addActionListener(this.controller);
					LocalWrite.setActionCommand("lclWrite");
					
					SingleRemoteFileController srfc = new SingleRemoteFileController(this.controller.getModel());
					
					
				}
	}

	void closez(){
		setModal(false);
		this.dispose();
		System.out.println("Method Done");
		
	}
}
