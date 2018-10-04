package baselayout;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Main.Main;
import Main.MainController;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class FooterPanel extends JPanel{
	private JTextField txtLogueado;
	private JTextField txtAutenticado;
	private JTextField txtArchivoslocales;
	private JTextField txtArchivosremotos;
	
	private MainController controller;
	
	public FooterPanel (MainController controller) {
		
		this.controller = controller;
		
		this.setBackground(SystemColor.window);
		setLayout(new MigLayout("", "[grow][grow][grow][grow][grow]", "[grow][grow]"));
		this.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
		
		JLabel lblEstadoDeConexion = new JLabel("Estado de Conexion:");
		lblEstadoDeConexion.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
		add(lblEstadoDeConexion, "cell 0 0,alignx trailing");
		
		txtLogueado = new JTextField();
		if (this.controller.isUserLoggedIn()) {
			txtLogueado.setText("conectado");
		} else {
			txtLogueado.setText("desconectado");
		}
		txtLogueado.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
		add(txtLogueado, "cell 1 0,growx");
		txtLogueado.setColumns(10);
		
		JLabel lblAutenticado = new JLabel("Autenticado:");
		lblAutenticado.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
		add(lblAutenticado, "cell 2 0,alignx trailing");
		
		txtAutenticado = new JTextField();
		if (this.controller.getStatus())
			txtAutenticado.setText("Autenticado");
		else
			txtAutenticado.setText("No autenticado");
		txtAutenticado.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
		add(txtAutenticado, "cell 3 0,growx");
		txtAutenticado.setColumns(10);
		
		JLabel lblArchivosLocalesAbiertos = new JLabel("Archivos Locales Abiertos:");
		lblArchivosLocalesAbiertos.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
		add(lblArchivosLocalesAbiertos, "cell 0 1,alignx trailing");
		
		txtArchivoslocales = new JTextField();
		txtArchivoslocales.setText("archivos_locales");
		txtArchivoslocales.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
		add(txtArchivoslocales, "cell 1 1,growx");
		txtArchivoslocales.setColumns(10);
		
		JLabel lblArchivosRemotosAbiertos = new JLabel("Archivos Remotos Abiertos:");
		lblArchivosRemotosAbiertos.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
		add(lblArchivosRemotosAbiertos, "cell 2 1,alignx trailing");
		
		txtArchivosremotos = new JTextField();
		txtArchivosremotos.setText("archivos_remotos");
		txtArchivosremotos.setFont(new Font("DejaVu Sans", Font.PLAIN, 16));
		add(txtArchivosremotos, "cell 3 1,growx");
		txtArchivosremotos.setColumns(10);
	}
}
