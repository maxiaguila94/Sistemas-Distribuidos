import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class Ventana {

	private JFrame frame;
	private JTextField txtFilename_2;
	private JTextField txtSize_2;
	private JTextField txtLastmodified_2;
	private JTextField txtLastaccesstime;
	private JTextField txtCreationtime_2;
	private JTextField txtStatus_2;
	private JTextField txtFilename_3;
	private JTextField txtSize_3;
	private JTextField txtLastmodified_3;
	private JTextField txtLastaccesstime_1;
	private JTextField txtCreationtime_3;
	private JTextField txtStatus_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ventana() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(400, 400, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[grow][grow]", "[grow]"));
		
		//Panel Izquierdo
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, "cell 1 0,grow");
		panel_2.setLayout(new MigLayout("", "[grow][][][grow]", "[grow][grow][][][][][][][][][][][]"));
		panel_2.setVisible(true);
		
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5, "cell 0 0,grow");
		panel_5.setLayout(new MigLayout("", "[grow][grow]", "[grow][grow][grow][grow][grow][grow]"));
		
		JLabel lblFileName = new JLabel("File Name:");
		panel_5.add(lblFileName, "cell 0 0,alignx trailing");
		
		txtFilename_3 = new JTextField();
		txtFilename_3.setText("file_name");
		panel_5.add(txtFilename_3, "cell 1 0,growx");
		txtFilename_3.setColumns(10);
		
		JLabel lblSize = new JLabel("Size:");
		panel_5.add(lblSize, "cell 0 1,alignx trailing");
		
		txtSize_3 = new JTextField();
		txtSize_3.setText("size");
		panel_5.add(txtSize_3, "cell 1 1,growx");
		txtSize_3.setColumns(10);
		
		JLabel lblLastModified = new JLabel("Last Modified:");
		panel_5.add(lblLastModified, "cell 0 2,alignx trailing");
		
		txtLastmodified_3 = new JTextField();
		txtLastmodified_3.setText("last_modified");
		panel_5.add(txtLastmodified_3, "cell 1 2,growx");
		txtLastmodified_3.setColumns(10);
		
		JLabel lblLastAccessTime = new JLabel("Last Access Time:");
		panel_5.add(lblLastAccessTime, "cell 0 3,alignx trailing");
		
		txtLastaccesstime_1 = new JTextField();
		txtLastaccesstime_1.setText("last_access_time");
		panel_5.add(txtLastaccesstime_1, "cell 1 3,growx");
		txtLastaccesstime_1.setColumns(10);
		
		JLabel lblCreationTime = new JLabel("Creation Time:");
		panel_5.add(lblCreationTime, "cell 0 4,alignx trailing");
		
		txtCreationtime_3 = new JTextField();
		txtCreationtime_3.setText("creation_time");
		panel_5.add(txtCreationtime_3, "cell 1 4,growx");
		txtCreationtime_3.setColumns(10);
		
		JLabel lblStatus = new JLabel("Status:");
		panel_5.add(lblStatus, "cell 0 5,alignx trailing");
		
		txtStatus_3 = new JTextField();
		txtStatus_3.setText("status");
		panel_5.add(txtStatus_3, "cell 1 5,growx");
		txtStatus_3.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6, "cell 0 1,grow");
		panel_6.setLayout(new MigLayout("", "[][][][]", "[]"));
		
		JButton btnOpen = new JButton("Open");
		panel_6.add(btnOpen, "cell 0 0");
		
		JButton btnWrite = new JButton("Write");
		panel_6.add(btnWrite, "cell 1 0");
		
		JButton btnRead = new JButton("Read");
		panel_6.add(btnRead, "cell 2 0");
		
		JButton btnClose = new JButton("Close");
		panel_6.add(btnClose, "cell 3 0");
		
		
		
		//Panel Derecho
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "cell 1 0,grow");
		panel_1.setLayout(new MigLayout("", "[grow][][][grow]", "[grow][grow][][][][][][][][][][][]"));
		panel_1.setVisible(true);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, "cell 0 0,grow");
	
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4, "cell 0 1,grow");
		panel_4.setLayout(new MigLayout("", "[][][][][]", "[]"));
		
		JButton btnOpender = new JButton("Open");
		panel_4.add(btnOpender, "cell 0 0");
		
		JButton btnWriteder = new JButton("Write");
		panel_4.add(btnWriteder, "cell 1 0");
		
		JButton btnReadder = new JButton("Read");
		panel_4.add(btnReadder, "cell 2 0");
		
		JButton btnCloseder = new JButton("Close");
		panel_4.add(btnCloseder, "cell 3 0");
		panel_3.setLayout(new MigLayout("", "[grow][grow]", "[grow][grow][grow][grow][grow][grow]"));
		
		JLabel lblFileNameder = new JLabel("File Name:");
		panel_3.add(lblFileNameder, "cell 0 0,alignx trailing");
		
		txtFilename_2 = new JTextField();
		txtFilename_2.setText("file_name");
		panel_3.add(txtFilename_2, "cell 1 0,growx");
		txtFilename_2.setColumns(10);
		
		JLabel lblSizeder = new JLabel("Size:");
		panel_3.add(lblSizeder, "cell 0 1,alignx trailing");
		
		txtSize_2 = new JTextField();
		txtSize_2.setText("size");
		panel_3.add(txtSize_2, "cell 1 1,growx");
		txtSize_2.setColumns(10);
		
		JLabel lblLastModifiedder = new JLabel("Last Modified:");
		panel_3.add(lblLastModifiedder, "cell 0 2,alignx trailing");
		
		txtLastmodified_2 = new JTextField();
		txtLastmodified_2.setText("last_modified");
		panel_3.add(txtLastmodified_2, "cell 1 2,growx");
		txtLastmodified_2.setColumns(10);
		
		JLabel lblLastAccessTiemeder = new JLabel("Last Access Tieme:");
		panel_3.add(lblLastAccessTiemeder, "cell 0 3,alignx trailing");
		
		txtLastaccesstime = new JTextField();
		txtLastaccesstime.setText("last_access_time");
		panel_3.add(txtLastaccesstime, "cell 1 3,growx");
		txtLastaccesstime.setColumns(10);
		
		JLabel lblCreationder = new JLabel("Creation Time:");
		panel_3.add(lblCreationder, "cell 0 4,alignx trailing");
		
		txtCreationtime_2 = new JTextField();
		txtCreationtime_2.setText("creation_time");
		panel_3.add(txtCreationtime_2, "cell 1 4,growx");
		txtCreationtime_2.setColumns(10);
		
		JLabel lblStatusder = new JLabel("Status:");
		panel_3.add(lblStatusder, "cell 0 5,alignx trailing");
		
		txtStatus_2 = new JTextField();
		txtStatus_2.setText("status");
		panel_3.add(txtStatus_2, "cell 1 5,growx");
		txtStatus_2.setColumns(10);
		
		
		
		
	}
}
