package banco;

public class Cuenta {
	
	private int id; 
	private String titular;
	private boolean bloqueada;
	private float saldo;
	
	public Cuenta (int id, String titular, boolean bloqueada, float saldo) {
		this.id = id; 
		this.titular = titular; 
		this.bloqueada = bloqueada; 
		this.saldo = saldo;
	}
		
	public boolean isDebitable(float importe) {
		return this.saldo - importe < 0;			
	}
	
	public int getId() {
		return id;
	}

	public String getTitular() {
		return titular;
	}

	public boolean isBloqueada() {
		return bloqueada;
	}

	public void setBloqueada(boolean bloqueada) {
		this.bloqueada = bloqueada;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	
	
	
	
	
}
