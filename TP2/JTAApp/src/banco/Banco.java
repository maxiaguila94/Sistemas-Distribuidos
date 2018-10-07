package banco;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.XAConnection;
import javax.sql.XADataSource;
import javax.transaction.xa.XAResource;

import org.postgresql.xa.PGXADataSource; 

public class Banco {

	private String db_host;
	private String db_name;
	private String db_user;
	public PGXADataSource data_source;
	
	public XADataSource xaDS;
	public XAConnection xaCon;
	public XAResource xaRes;
	
	public Connection con;
	public Statement stmt;
	
	public Banco(String host, String name, String user) {
		this.db_host = host;
		this.db_name = name;
		this.db_user = user;
		this.xaRes = null;
		this.data_source = null;
		this.con = null;
		this.stmt = null;
	}
	
	private PGXADataSource _getDataSource() throws SQLException {
		
		if (this.data_source == null)
			this.data_source = new PGXADataSource();

		this.data_source.setServerName(this.getDBHost());
		this.data_source.setDatabaseName(this.getDBName());		
		this.data_source.setUser(this.getDBUser());				
		return this.data_source;
	
	}
	
	
	public XAResource getXARes(String db_user, String db_pass) throws SQLException {
		

		if (this.xaRes == null) {				
			this.xaDS = this._getDataSource();
			this.xaCon = this.xaDS.getXAConnection(db_user, db_pass);
			this.xaRes = this.xaCon.getXAResource();
		}
		return this.xaRes;

	}
	
	public Statement getStatement() throws SQLException {
		
		if (this.con == null)
			this.con = this.xaCon.getConnection();
		if (this.stmt == null)
			this.stmt = this.con.createStatement();
		return this.stmt;
	}
	
	public boolean closeConn() {
		try {
			this.stmt.close();
			this.con.close();
			this.xaCon.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	
	public String getDBHost() {
		return this.db_host;
	}
	
	public String getDBName() {
		return this.db_name;
	}
	
	public String getDBUser() {
		return this.db_user;
	}
}
