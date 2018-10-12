package banco;

import java.sql.SQLException;

import javax.transaction.xa.XAException;

public class Manager {

	Conexion conexion;
	TransactionModel transaction;
	
	public Manager (Conexion conexion) {
		this.conexion = conexion;
	}
	
	
	
	public boolean update(Model model) throws SQLException, XAException {
	
		try {
	
			this.transaction = new TransactionModel(this.conexion, model.getUpdateQuery());
			return this.transaction.prepare(); 
		
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
	
			e.printStackTrace();
			return false;
		}
		
	}
	
	public void commit() throws UnknownTransactionException, XAException, SQLException {
		if (this.transaction == null) 
			throw new UnknownTransactionException("No hay ninguna transacción");
		this.transaction.commit();
		this.transaction = null;
	}
	
	public void rollback() throws UnknownTransactionException, XAException, SQLException {
		if (this.transaction == null) 
			throw new UnknownTransactionException("No hay ninguna transacción");
		this.transaction.rollback();
		this.transaction = null;	
	}
	
	
	
}
