package banco;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Banco {

	
	
	Conexion conexion; 
	TransactionModel transaction; 

	public Banco(String host, String name, String user, String pass) {
		
		this.conexion = new Conexion(host, name, user, pass);
		
	}
	
	public Cuenta getCuenta(int id) throws UnknownAccountExeption, SQLException {
		
		Connection conn = this.conexion.getConn();
		Statement stmt = conn.createStatement();
		
		String query = "select * from cuentas where id="+id+";";
		
		ResultSet rs = stmt.executeQuery(query);
		Cuenta cuenta = null;


		rs.next();
		try {
			cuenta = new Cuenta(
					rs.getInt("id"), 
					rs.getString("titular"), 
					rs.getBoolean("bloqueada"), 
					rs.getFloat("saldo"),
					this.conexion);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		stmt.close();
		if (cuenta == null)
			throw new UnknownAccountExeption("No se encontró la cuenta "+id);
		
		return cuenta;
			
	}
	
	
	public void closeConn() throws SQLException {
		this.conexion.closeConn();
	}
}
