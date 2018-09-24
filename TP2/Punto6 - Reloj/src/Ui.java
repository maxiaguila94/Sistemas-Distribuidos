import java.awt.Frame;

import javax.swing.JFrame;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class Ui extends Frame {

	private JFrame frmReloj;
	private JLabel lblHora;
	

    public Ui(String host) { 
		initialize();
    }

	public void dialogo(String texto, String titulo, int flag) {
		JOptionPane.showMessageDialog(new JFrame(), texto, titulo, flag);
	}
	
    public void initialize(){
        //Frame
        this.frmReloj = new JFrame();
        frmReloj.setTitle("Reloj - RMI");
        frmReloj.getContentPane().setFont(new Font("DejaVu Sans Condensed", Font.PLAIN, 20));
		frmReloj.setBounds(100, 100, 627, 180);
		frmReloj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmReloj.getContentPane().setLayout(null);
    
        JPanel panelHora = new JPanel();
        panelHora.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        panelHora.setBounds(12, 12, 598, 119);
		frmReloj.getContentPane().add(panelHora);
        panelHora.setLayout(null);

        this.lblHora = new JLabel("00:00:00");
		lblHora.setBounds(145, 27, 161, 60);
		lblHora.setFont(new Font("DejaVu Sans Condensed", Font.PLAIN, 30));
		panelHora.add(lblHora);

        JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(380, 44, 168, 27);
		btnActualizar.setFont(new Font("DejaVu Sans Condensed", Font.PLAIN, 20));
		panelHora.add(btnActualizar);
	}
	
	public void setHoraMostrada(int horas, int minutos, int segundos){
		this.lblHora.setText(horas + ":" + minutos + ":" + segundos);
	}

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String host = (args.length < 1) ? null : args[0];							
					Ui window = new Ui(host);
					window.frmReloj.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
}