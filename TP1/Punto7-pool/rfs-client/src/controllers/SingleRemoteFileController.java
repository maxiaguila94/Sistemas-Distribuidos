package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import client.RFSClient;
import remoteobjects.FileMetadata;
import ui.SingleRemoteFilePanel;

public class SingleRemoteFileController implements ActionListener {
	private RFSClient model;
	private SingleRemoteFilePanel view;
	
	public SingleRemoteFileController (RFSClient model) {
		this.model = model;
	}
	
	public RFSClient getModel() {
		return this.model;
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

	public void setView(SingleRemoteFilePanel view) {
		this.view = view;
	}
	
	public FileMetadata lookUpLocalCopy(String file_name) {
		String[] f = file_name.split("/");
		FileMetadata file = new FileMetadata(f[2]);
		if (file.getFileName() == null)
			return null;
		return file;
	}
	
	//Eventos Remotos
	public void rmtOpen() {
		System.out.println("HICE UN OPEN REMOTO");
	}
	public void rmtRead() {
		System.out.println("HICE UN READ REMOTO");
	}
	public void rmtWrite() {
		System.out.println("HICE UN WRITE REMOTO");
	}
	public void rmtClose() {
		System.out.println("HICE UN CLOSE REMOTO");
	}
	//Eventos Locales
	public void lclOpen() {
		System.out.println("HICE UN OPEN LOCAL");
	}
	public void lclRead() {
		System.out.println("HICE UN READ LOCAL");
	}
	public void lclWrite() {
		System.out.println("HICE UN WRITE LOCAL");
	}
	public void lclClose() {
		System.out.println("HICE UN CLOSE LOCAL");

	}
}