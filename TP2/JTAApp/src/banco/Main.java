package banco;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;

import banco.MyXid;

public class Main {

	public static void main(String args[]) {
		
		Scanner scanner = new Scanner(System.in);
		 int origen; 
		 int destino;
		 float importe; 

		 System.out.println("Ingrese importe a depositar");
		 importe = Float.parseFloat(scanner.nextLine());
		 System.out.println("Ingrese la cuenta de origen");
		 origen = Integer.parseInt(scanner.nextLine());
		 System.out.println("Ingrese la cuenta destino");
		 destino = Integer.parseInt(scanner.nextLine());

		 String debitarEnOrigenQuery = "update cuentas set saldo=saldo - "+ importe + " where id=" + origen + ";";
		 String acreditarEnDestinoQuery = "update cuentas set saldo=saldo + "+ importe + " where id=" + destino + ";";
		 
		 // String host, String name, String user
		 Banco banco1 = new Banco("172.17.0.2", "banco1", "postgres");
		 Banco banco2 = new Banco("172.17.0.2", "banco2", "postgres");
		 
	 	 MyXid xid1 = new MyXid(101, new byte[]{0x01}, new byte[]{0x02});
	 	 MyXid xid2 = new MyXid(101, new byte[]{0x01}, new byte[]{0x02});
		 
   	 	 
   	 	 
	   	 try {
	   		 XAResource banco1Res = banco1.getXARes("jtatest", "jtatest");
	   		 Statement banco1stmt = banco1.getStatement();
	   		 
	   		 XAResource banco2Res = banco2.getXARes("jtatest", "jtatest");
	   		 Statement banco2stmt = banco2.getStatement();
	   		 banco1Res.start(xid1, XAResource.TMNOFLAGS);
			 banco1stmt.executeUpdate(debitarEnOrigenQuery);
			 banco1Res.end(xid1, XAResource.TMSUCCESS);
			
			 banco2Res.start(xid2, XAResource.TMNOFLAGS);
			 banco2stmt.executeUpdate(acreditarEnDestinoQuery);
			 banco2Res.end(xid2, XAResource.TMSUCCESS);
			
			 int ret1 = banco1Res.prepare(xid1);
			 if (ret1 == XAResource.XA_OK) {
				/*ret2 = xaRes2.prepare(xid);*/
				
				 banco1Res.commit(xid1, false);
			 } 
			
			 int ret2 = banco2Res.prepare(xid2);
			 if (ret2 == XAResource.XA_OK) {
				/*ret2 = xaRes2.prepare(xid);*/
				
			 	 banco2Res.commit(xid2, false);
			 }
			
			 banco1.closeConn();
			 banco2.closeConn(); 
			
		} catch (XAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
