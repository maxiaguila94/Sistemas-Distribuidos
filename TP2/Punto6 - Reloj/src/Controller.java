import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Controller implements ActionListener {
    private Cliente modelo;
	private Vista vista;

	public Controller(Vista vista, Cliente modelo) {
		this.vista = vista;
		this.modelo = modelo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Actualizar") {
			this.actualizar_hora();
		}else {
			this.vista.dialogo("Comando no reconocido!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void mostrarHora(int horas, int minutos, int segundos){
		this.vista.setHoraMostrada(horas, minutos, segundos);
	}

	public void  actualizar_hora(){
		this.modelo.ajustaClock(this.modelo.getObjetoRemoto());
	}

}