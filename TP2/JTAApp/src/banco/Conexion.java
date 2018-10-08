package banco;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.XAConnection;
import javax.sql.XADataSource;
import javax.transaction.xa.XAResource;

import org.postgresql.xa.PGXADataSource;

public class Conexion {
	private String db_host;
	private String db_name;
	private String db_user;
	private String db_pass;
	public PGXADataSource data_source;
	
	private XADataSource xaDS;
	private XAConnection xaCon;
	private XAResource xaRes;
	
	private Connection con;
	
	public Conexion(String host, String name, String user, String pass) {
		this.db_host = host;
		this.db_name = name;
		this.db_user = user;
		this.db_pass = pass; 
	}
	
	
	private PGXADataSource _getDataSource() throws SQLException {
		
		if (this.data_source == null)
			this.data_source = new PGXADataSource();

		this.data_source.setServerName(this.db_host);
		this.data_source.setDatabaseName(this.db_name);		
		this.data_source.setUser(this.db_user);				
		return this.data_source;
	
	}
	
	
	public XAResource getXaRes () throws SQLException {

		if (this.xaRes == null) {				
			this.xaDS = this._getDataSource();
			this.xaCon = this.xaDS.getXAConnection(this.db_user, this.db_pass);
			this.xaRes = this.xaCon.getXAResource();
		}
		
		return this.xaRes;
		
	}

	public Connection getConn() throws SQLException {
		
		if (this.con == null)
			this.getXaRes();
			this.con = this.xaCon.getConnection();
		
		return this.con;

	}
	
	public void closeConn() throws SQLException {
		this.con.close();
		this.xaCon.close();
	}


	
}
