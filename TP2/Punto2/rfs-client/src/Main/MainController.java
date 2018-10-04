package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.RFSClient;
import conection.ConnectionController;
import conection.ConnectionPanel;
import localfiles.LocalFilesController;
import localfiles.LocalFilesPanel;
import login.LoginController;
import login.LoginPanel;
import remotefiles.RemoteFilesController;
import remotefiles.RemoteFilesPanel;
import signup.SignUpController;
import signup.SignUpPanel;

public class MainController implements ActionListener{
	
	MainUI ui; 
	RFSClient client_model;
	private JTextField txtFiletext;
	private JButton btnTransferirArchivos;
	
	
	public MainController(RFSClient model) {
		this.client_model = model;
	}
	
	public JTextField gettxtFiletext() {
		return this.txtFiletext;
	}
	
	public void setModel(RFSClient model) {
		this.client_model = model;
	}
	
	public RFSClient getModel() {
		return this.client_model;
	}
	
	public void setUI(MainUI view) {
		this.ui = view;
	}
	
	public MainUI getView() {
		return this.ui;
	}
	
	public boolean isUserLoggedIn() {
		return this.getModel().getUserToken() != null;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String methodName = e.getActionCommand();
		
        Method method;
		try {
			method = this.getClass().getMethod(methodName);
			method.invoke(this);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void showConnectionPanel() {
		ConnectionController controller = new ConnectionController(this.getModel());
		ConnectionPanel connectionPanel = new ConnectionPanel(controller);
		controller.setView(connectionPanel);
		
		this.ui.getContentPanel().removeAll();
		this.ui.getContentPanel().add(connectionPanel, "cell 1 0,grow");
		this.ui.getContentPanel().revalidate();
	}
	
	public void showSignUpPanel() {
		SignUpController controller = new SignUpController(this.getModel());
		SignUpPanel signUpPanel = new SignUpPanel(controller);
		controller.setView(signUpPanel);
		
		this.ui.getContentPanel().removeAll();
		this.ui.getContentPanel().add(signUpPanel, "cell 1 0,grow");
		this.ui.getContentPanel().revalidate();
	}

	public void showLoginPanel() {
		
		LoginController controller = new LoginController(this.getModel());
		LoginPanel loginPanel = new LoginPanel(controller);
		controller.setView(loginPanel);
		
		this.ui.getContentPanel().removeAll();
		this.ui.getContentPanel().add(loginPanel, "cell 1 0,grow");
		this.ui.getContentPanel().revalidate();
	}
	
	
	public void showRemoteFilesPanel() {
		
		this.ui.getContentPanel().removeAll();

		LocalFilesController filesController = new LocalFilesController(this.getModel());
		LocalFilesPanel filesPanel = new LocalFilesPanel(filesController);
		filesController.setView(filesPanel);
		
		RemoteFilesController remoteFilesController = new RemoteFilesController(this.getModel());
		RemoteFilesPanel remoteFilesPanel = new RemoteFilesPanel(remoteFilesController); 
		remoteFilesController.setView(remoteFilesPanel);
	
		this.getView().getContentPanel().add(filesPanel, "cell 1 0,grow");
		this.getView().getContentPanel().add(remoteFilesPanel, "cell 1 1,grow");
		this.ui.getContentPanel().revalidate();	
	}
	
//	//Agregado del boton ERROR
//	public void botonError() {
//		JOptionPane.showMessageDialog(null, "Hola... Soy un ERROR ");
//	}
	
//	//Boton del Chooser
//	public void showJFC() {
//		JFileChooser jfc = new JFileChooser();
//		
//
//		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//		int evento = jfc.showOpenDialog(this.ui.getContentPanel());
//		System.out.println(evento);
//		if(evento == JFileChooser.APPROVE_OPTION) {
//			System.out.println("ACEPTAR");
//			File fichero = jfc.getSelectedFile();
//			String file_name = jfc.getName(fichero);
//			System.out.println("Archivo: "+file_name+"   "+"Ruta: "+fichero);
//			this.ui.gettxtFiletext().setText(fichero.getAbsolutePath());
//		}else {
//			System.out.println("CANCELAR");
//		}
//	}

	public boolean getStatus() {
		// TODO Auto-generated method stub
		return this.getModel().getStatus();
	}
	
}
