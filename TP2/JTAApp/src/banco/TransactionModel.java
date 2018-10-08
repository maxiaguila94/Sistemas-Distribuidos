package banco;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;

public class TransactionModel {

	MyXid xid;
	Conexion conexion;
	String query;
	
	public TransactionModel(Conexion conexion, String query, MyXid xid) {
	
		this.xid = xid;
		this.conexion = conexion;
		this.query = query;
	
	}
	
	public boolean prepare() throws SQLException, XAException {
		
		XAResource xares = this.conexion.getXaRes();
		Connection conn = this.conexion.getConn();
		Statement stmt = conn.createStatement();
		
		xares.start(this.xid, XAResource.TMNOFLAGS);
		stmt.executeUpdate(this.query);
		xares.end(this.xid, XAResource.TMSUCCESS);
		
		boolean status = (xares.prepare(this.xid) == XAResource.XA_OK) ? true : false;
		stmt.close();
		return status;
		
		
	}
	
	public void commit () throws XAException, SQLException {
		this.conexion.getXaRes().commit(this.xid, false);
	}
	
	public void rollback() throws XAException, SQLException {
		this.conexion.getXaRes().rollback(this.xid);
	}
	
}
