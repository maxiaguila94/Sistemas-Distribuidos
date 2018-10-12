package banco;

import java.sql.SQLException;

import javax.transaction.xa.XAException;

public class Cuenta extends Model{
	
	public int id; 
	public String titular;
	public boolean bloqueada;
	public float saldo;
	public Manager manager;
	
	public Cuenta (int id, String titular, boolean bloqueada, float saldo, Conexion conexion) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		super();
		this.id = id; 
		this.titular = titular; 
		this.bloqueada = bloqueada; 
		this.saldo = saldo;
		this.manager = new Manager(conexion);
		
	}
	
	
	public boolean update() throws SQLException, XAException {
		return this.manager.update(this);
	}
	
	public void commit() throws UnknownTransactionException, XAException, SQLException {
		this.manager.commit();
	}	
	
	public void rollback() throws UnknownTransactionException, XAException, SQLException {
		this.manager.rollback();
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
