import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;



public class Ventana {
	private JFrame frmSistemasDistribuidos;
	
	//Barra de Menus
	JMenuBar barra = new JMenuBar();
	JMenu menu = new JMenu("Menu");
	JMenuItem open = new JMenuItem("Open");
	JMenuItem read = new JMenuItem("Read");
	JMenuItem write = new JMenuItem("Write");
	JMenuItem close = new JMenuItem("Close");
	
	
	// Elementos de la caja de conexiones
	private JTextField text_ip;
	private JTextField text_puerto;
	private JButton btnConectar;
	private JButton btnDesconectar;
	private JButton btnLogin;
	private JButton btnReg;
	private JTextField text_pass;
	private JTextField text_login;
	private JTextField text_user;
	private JTextField text_contra;
	
	
	// Elementos de la caja de acciones
	private JTextPane text_texto;
	
	
	public Ventana() {
		initialize();
	}
	
	private void initialize() {
		frmSistemasDistribuidos = new JFrame();
		frmSistemasDistribuidos.setTitle("Sistemas Distribuidos - Cliente RPC");
		frmSistemasDistribuidos.setBounds(100, 100, 727, 540);
		frmSistemasDistribuidos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemasDistribuidos.getContentPane().setLayout(null);
		
		// Panel de Conexion
		JPanel panel_conexion = new JPanel();
		panel_conexion.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		panel_conexion.setBounds(15, 11, 697, 36);
		frmSistemasDistribuidos.getContentPane().add(panel_conexion);
		panel_conexion.setLayout(null);
		
		JLabel lblDireccinIp = new JLabel("Direcci\u00F3n IP:");
		lblDireccinIp.setBounds(10, 10, 109, 14);
		panel_conexion.add(lblDireccinIp);
		
		text_ip = new JTextField();
		text_ip.setBounds(108, 10, 97, 20);
		panel_conexion.add(text_ip);
		text_ip.setColumns(10);
		
		JLabel lblPuerto = new JLabel("Puerto:");
		lblPuerto.setBounds(230, 10, 66, 14);
		panel_conexion.add(lblPuerto);
		
		text_puerto = new JTextField();
		text_puerto.setBounds(288, 10, 68, 20);
		panel_conexion.add(text_puerto);
		text_puerto.setColumns(10);
		
		JLabel lblStatus = new JLabel("Desconectado"); 
		lblStatus.setBounds(368,10,100,14);
		panel_conexion.add(lblStatus);
		
		btnDesconectar = new JButton("Desconectar");
		btnDesconectar.setBounds(558, 7, 129, 23);
		panel_conexion.add(btnDesconectar);
		ActionListener desconectar = null;
		btnDesconectar.addActionListener(desconectar);
		
		btnConectar = new JButton("Conectar");
		btnConectar.setBounds(460, 7, 90, 23);
		panel_conexion.add(btnConectar);
		ActionListener conectar = null;
		btnConectar.addActionListener(conectar);
		
		//Panel Login
		JPanel panel_login = new JPanel();
		panel_login.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		panel_login.setBounds(15, 40, 697, 36);
		frmSistemasDistribuidos.getContentPane().add(panel_login);
		panel_login.setLayout(null);
		
		JLabel lblLogin = new JLabel("Usuario:");
		lblLogin.setBounds(15, 10, 109, 14);
		panel_login.add(lblLogin);
		
		text_login = new JTextField();
		text_login.setBounds(80, 10, 100, 20);
		panel_login.add(text_login);
		
		JLabel lblPass = new JLabel("Contraseña:");
		lblPass.setBounds(230, 10, 109, 14);
		panel_login.add(lblPass);
		
		text_pass = new JTextField();
		text_pass.setBounds(310, 10, 100, 20);
		panel_login.add(text_pass);
		text_pass.setColumns(10);
		
		btnLogin = new JButton("Ingresar");
		btnLogin.setBounds(500, 7, 129, 23);
		panel_login.add(btnLogin);
		ActionListener ingresar = null;
		btnLogin.addActionListener(ingresar);
		
		
		// Panel Registro
		
		JPanel panel_registro = new JPanel();
		panel_registro.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		panel_registro.setBounds(15, 69, 697, 36);
		frmSistemasDistribuidos.getContentPane().add(panel_registro);
		panel_registro.setLayout(null);
		
		JLabel lbluser = new JLabel("Usuario:");
		lbluser.setBounds(15, 10, 109, 14);
		panel_registro.add(lbluser);
		
		text_user = new JTextField();
		text_user.setBounds(80, 10, 100, 20);
		panel_registro.add(text_user);
		text_user.setColumns(10);
		
		JLabel lblcontra = new JLabel("Contraseña:");
		lblcontra.setBounds(230, 10, 109, 14);
		panel_registro.add(lblcontra);
		
		text_contra = new JTextField();
		text_contra.setBounds(310, 10, 100, 20);
		panel_registro.add(text_contra);
		text_contra.setColumns(10);
		
		btnReg = new JButton("Registrar");
		btnReg.setBounds(500, 7, 129, 23);
		panel_registro.add(btnReg);
		ActionListener registrar = null;
		btnReg.addActionListener(registrar);

		// Events de Botones
		BotonListener bl = new BotonListener();
		btnConectar.addActionListener(bl);
		btnDesconectar.addActionListener(bl);
		btnLogin.addActionListener(bl);
		btnReg.addActionListener(bl);
		
		
		
		
		// Barra de menus
		frmSistemasDistribuidos.setJMenuBar(barra);
		
		barra.add(menu);
		menu.add(open);
		menu.add(read);
		menu.add(write);
		menu.add(close);
		
		MenuListener ml = new MenuListener();
		open.addActionListener(ml);
		read.addActionListener(ml);
		write.addActionListener(ml);
		close.addActionListener(ml);	
		
		JPanel panel_acciones = new JPanel();
		panel_acciones.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		panel_acciones.setBounds(15, 100, 697, 375);
		frmSistemasDistribuidos.getContentPane().add(panel_acciones);
		panel_acciones.setLayout(null);
		
		JLabel lblLeerescribirArchivos = new JLabel("Lector/Editor");
		lblLeerescribirArchivos.setHorizontalAlignment(SwingConstants.CENTER);
		lblLeerescribirArchivos.setBounds(185, 11, 343, 14);
		panel_acciones.add(lblLeerescribirArchivos);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 680, 320);
		panel_acciones.add(scrollPane);
		
		text_texto = new JTextPane();
		scrollPane.setViewportView(text_texto);
		
			
		
	}
	
	// Clase MenuListener
	public class MenuListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			int seleccion;
			
			// TODO Auto-generated method stub
			if(e.getSource().equals(open)) {
				JFileChooser abrirArchivo = new JFileChooser();
				abrirArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				seleccion = abrirArchivo.showOpenDialog(null);
				
				if(seleccion == JFileChooser.APPROVE_OPTION){
					File f = abrirArchivo.getSelectedFile();
					
					String nombre = f.getName();
					String path = f.getAbsolutePath();
					
					//Seguir con algo aca
				}
					
			} else if (e.getSource().equals(read)) {
				JFileChooser leerArchivo = new JFileChooser();
				leerArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				seleccion = leerArchivo.showOpenDialog(null);
				
				if(seleccion == JFileChooser.APPROVE_OPTION){
					File f = leerArchivo.getSelectedFile();
					
					String nombre = f.getName();
					String path = f.getAbsolutePath();
					
					//Seguir con algo aca
				}	
				
			}else if (e.getSource().equals(write)) {
				JFileChooser escribirArchivo = new JFileChooser();
				escribirArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				seleccion = escribirArchivo.showOpenDialog(null);
				
				if(seleccion == JFileChooser.APPROVE_OPTION){
					File f = escribirArchivo.getSelectedFile();
					
					String nombre = f.getName();
					String path = f.getAbsolutePath();
					
					//Seguir con algo aca
					
				}
				
			}else if (e.getSource().equals(close)) {
				
				//Hacer algo aca
				
			}
			
			

		}	

	}
	
	// Clase BotonListener
	public class BotonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource().equals(btnConectar)) {
			
			
				//lblStatus.setText("Conectado");
				JOptionPane.showMessageDialog(null, "Ya estas Conectado !");

			}else if (e.getSource().equals(btnDesconectar)) {
				
			}else if (e.getSource().equals(btnLogin)) {
				
				JOptionPane.showMessageDialog(null, "Ya estas Logueado !");
				
			}else if (e.getSource().equals(btnReg)) {
				
				JOptionPane.showMessageDialog(null, "El usuario ya esta Registrad !");
				
			}
			
		}
		
	}
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
					window.frmSistemasDistribuidos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}


