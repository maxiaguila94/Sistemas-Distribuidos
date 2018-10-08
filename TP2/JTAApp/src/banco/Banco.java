package banco;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.transaction.xa.XAException;

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
		cuenta = new Cuenta(
				rs.getInt("id"), 
				rs.getString("titular"), 
				rs.getBoolean("bloqueada"), rs.getFloat("saldo"));

		
//		while (rs.next()) {
//			
//			cuenta = new Cuenta(
//					rs.getInt("id"), 
//					rs.getString("titular"), 
//					rs.getBoolean("bloqueada"), rs.getFloat("saldo"));
//			
//			break;
//		} 

		stmt.close();
		if (cuenta == null)
			throw new UnknownAccountExeption("No se encontró la cuenta "+id);
		
		return cuenta;
			
	}
	
	public boolean prepareUpdateSaldo(Cuenta cuenta, MyXid xid) throws SQLException, XAException {
		
		String query = "update cuentas set saldo="+cuenta.getSaldo() + "where id="+cuenta.getId();
		this.transaction = new TransactionModel(this.conexion, query, xid);
		return this.transaction.prepare(); 
		
	}
	
	public void commitTransaction() throws UnknownTransactionException, XAException, SQLException {
		
		if (this.transaction == null) 
			throw new UnknownTransactionException("No hay ninguna transacción");
		this.transaction.commit();
		this.transaction = null;
		
	}
	
	public void rollbackTransaction() throws UnknownTransactionException, XAException, SQLException {
		if (this.transaction == null) 
			throw new UnknownTransactionException("No hay ninguna transacción");
		this.transaction.rollback();
		this.transaction = null;
	}
	
	
	public void closeConn() throws SQLException {
		this.conexion.closeConn();
	}
	

		
	
//	private String db_host;
//	private String db_name;
//	private String db_user;
//	public PGXADataSource data_source;
//	
//	public XADataSource xaDS;
//	public XAconexion xaCon;
//	public XAResource xaRes;
//	
//	public conexion con;
//	public Statement stmt;
//	
//	public Banco(String host, String name, String user) {
//		this.db_host = host;
//		this.db_name = name;
//		this.db_user = user;
//		this.xaRes = null;
//		this.data_source = null;
//		this.con = null;
//		this.stmt = null;
//	}
	
//	private PGXADataSource _getDataSource() throws SQLException {
//		
//		if (this.data_source == null)
//			this.data_source = new PGXADataSource();
//
//		this.data_source.setServerName(this.getDBHost());
//		this.data_source.setDatabaseName(this.getDBName());		
//		this.data_source.setUser(this.getDBUser());				
//		return this.data_source;
//	
//	}
//	
//	
//	public XAResource getXARes() throws SQLException {			
//		return this.xaRes;
//	}
//	
//	private void _setXARes(String db_user, String db_pass) throws SQLException {
//
//		if (this.xaRes == null) {				
//			this.xaDS = this._getDataSource();
//			this.xaCon = this.xaDS.getXAconexion(db_user, db_pass);
//			this.xaRes = this.xaCon.getXAResource();
//		}
//		
//	}
//	
//	public boolean prepare(String db_user, String db_pass, String query, MyXid xid) throws SQLException, XAException {
//		
//		this._setXARes(db_user, db_pass);
//				
//		this.getXARes().start(xid, XAResource.TMNOFLAGS);
//		this.getStatement().executeUpdate(query);
//		this.getXARes().end(xid, XAResource.TMSUCCESS);
//		
//		if (this.getXARes().prepare(xid) == XAResource.XA_OK)
//			return true;
//	
//		return false;
//		
//	}
//	
//	public void commit (MyXid xid) throws XAException, SQLException {
//		
//		this.getXARes().commit(xid, false);
//		
//	}
//	
//	public void rollback (MyXid xid) throws XAException, SQLException {
//		
//		this.getXARes().rollback(xid);
//		
//	}
//	
//	public Statement getStatement() throws SQLException {
//		
//		if (this.con == null)
//			this.con = this.xaCon.getconexion();
//		if (this.stmt == null)
//			this.stmt = this.con.createStatement();
//		return this.stmt;
//	
//	}
//	
//	public boolean closeConn() {
//		try {
//			this.stmt.close();
//			this.con.close();
//			this.xaCon.close();
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
//	
//	public String getDBHost() {
//		return this.db_host;
//	}
//	
//	public String getDBName() {
//		return this.db_name;
//	}
//	
//	public String getDBUser() {
//		return this.db_user;
//	}
}
