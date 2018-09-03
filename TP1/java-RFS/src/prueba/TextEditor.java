
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

public class TextEditor{
	
	/*Barra menu*/
	JMenuBar barra = new JMenuBar();
	JMenu menu = new JMenu("Menu");
	JMenuItem cargar = new JMenuItem("Abrir");
	JMenuItem guardar = new JMenuItem("Guardar");
	JMenuItem salir = new JMenuItem("Salir");
	
	JFrame v = new JFrame();
	JPanel contenedor = new JPanel();
	
	JTextArea editor = new JTextArea();
	JButton save = new JButton();
	
	public TextEditor(){
		v.setBounds(0,0,400,400);
		v.setTitle("Editor de Texto");
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.setResizable(false);
		
		v.setJMenuBar(barra);
		
		barra.add(menu);
		menu.add(cargar);
		menu.add(guardar);
		menu.add(new JSeparator());
		menu.add(salir);
		
		menuListener l = new menuListener();
		cargar.addActionListener(l);
		guardar.addActionListener(l);
		salir.addActionListener(l);
		
		
		editor.setBounds(0,0,400,350);
		JScrollPane j = new JScrollPane(editor);

		contenedor.add(editor);
		contenedor.add(j);
		contenedor.setLayout(null);
		
		v.getContentPane().add(contenedor);
		v.setVisible(true);
	}
	
	private class menuListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(cargar)){
				JFileChooser abrirArchivo = new JFileChooser();
				abrirArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				int seleccion = abrirArchivo.showOpenDialog(null);
				
				if(seleccion == JFileChooser.APPROVE_OPTION){
					File f = abrirArchivo.getSelectedFile();
					
					String nombre = f.getName();
					String path = f.getAbsolutePath();
					
					String contenido = getContenido(path);
					
					v.setTitle(nombre + " [" + path + "]");
					editor.setText(contenido);
				}
			}
			if(e.getSource().equals(guardar)){
				JFileChooser guardarArchivo = new JFileChooser();
				guardarArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				int seleccion = guardarArchivo.showSaveDialog(null);
				
				if(seleccion == JFileChooser.APPROVE_OPTION){
					File f = guardarArchivo.getSelectedFile();
					
					String path = f.getAbsolutePath();
					
					if(f.exists()){
						if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(contenedor,"El fichero existe,deseas reemplazarlo?","Sobreescribir Archivo",JOptionPane.YES_NO_OPTION)){
							salvarArchivo(path);
						}
					}else{
						try {
							f.createNewFile();
							salvarArchivo(path);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}if(e.getSource().equals(salir)){
				System.exit(0);
			}
		}
		
		public String getContenido(String path){
			FileReader fr;
			BufferedReader br = null;
			String contenido = "";
			try {
				fr = new FileReader(path);
				br = new BufferedReader(fr);
				
				
				String linea;
				while((linea=br.readLine())!=null){
					contenido += linea + "\n";
					
				}
				return contenido;
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try{
					br.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			return contenido;
		}
		
		public void salvarArchivo(String path){
			FileWriter fw;
			BufferedWriter bw = null ;
			String contenido="";
			try {
				fw = new FileWriter(path);
				bw = new BufferedWriter(fw);
				
				BufferedReader reader = new BufferedReader(
						  new StringReader(editor.getText()));
				
				String linea;
				while((linea=reader.readLine())!=null){
					contenido += linea + "\n";
				}
				
				bw.write(contenido);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TextEditor r = new TextEditor();

	}
	
}