package Main;

import java.awt.EventQueue;
import java.io.IOException;

import client.RFSClient;
import controllers.MainController;
import ui.MainUI;

public class Main {

	private static MainUI view;
	private static MainController controller;
	private static RFSClient model;
	
	public static MainController getController(RFSClient model) {
		if (controller == null)
			controller = new MainController(model);
		return controller;
	}
	
	public static RFSClient getModel() {
		if (model == null)
			try {
				model = new RFSClient();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return model;
	}
	
	public static MainUI getUI(MainController controller) {
		if (view == null) {
			view = new MainUI(controller);
		}
		return view;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RFSClient model = getModel();
					MainController controller = getController(model); 
					MainUI window = getUI(controller);
					controller.setUI(window);
//					controller.setModel(model);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
