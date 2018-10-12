package banco;

import java.sql.SQLException;
import java.util.Scanner;

import javax.transaction.xa.XAException;

public class Main {
	
	static boolean confirma(String msg, Scanner scanner) {
		int res = -1;
		while( res != 0 && res != 1) {
			System.out.println(msg + "1 para confirmar, 0 para rechazar");
			res = Integer.parseInt(scanner.nextLine());
			System.out.println(res);
		}
		
		if (res == 0)
			return false;
		else
			return true;
		
	} 
	public static void main(String args[]) {
		
		 Scanner scanner = new Scanner(System.in);
		 int origen; 
		 int destino;
		 float importe; 
	
		 do {

			 System.out.println("Ingrese importe a depositar");
			 importe = Float.parseFloat(scanner.nextLine());
			 System.out.println("Ingrese la cuenta de origen");
			 origen = Integer.parseInt(scanner.nextLine());
			 System.out.println("Ingrese la cuenta destino");
			 destino = Integer.parseInt(scanner.nextLine());
				 
			 Banco banco1 = new Banco("172.17.0.2", "banco1", "jtatest", "jtatest");
			 Banco banco2 = new Banco("172.17.0.2", "banco2", "jtatest", "jtatest");
			 
		 	 try {
				Cuenta cuentaOrigen = banco1.getCuenta(origen);
				
				if (cuentaOrigen.isBloqueada()) {				
					System.out.println("La cuenta de origen se encuentra bloqueada.");				
				} else {
					
					Cuenta cuentaDestino = banco2.getCuenta(destino);
					cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - importe);
					cuentaDestino.setSaldo(cuentaDestino.getSaldo() + importe);
					
					if (cuentaOrigen.update() && cuentaDestino.update()) {
						System.out.println("Valores resultantes de la operacion");
						System.out.println(String.format("CUENTA ORIGEN: %d, SALDO: %f", cuentaOrigen.getId(), cuentaOrigen.getSaldo()));
						System.out.println(String.format("CUENTA DESTINO: %d, SALDO: %f", cuentaDestino.getId(), cuentaDestino.getSaldo()));
						
						if (confirma("Confirma la operacion? ", scanner)) {
							cuentaOrigen.commit();
							cuentaDestino.commit();
							System.out.println("Se han actualizado los saldos exitosamente");
						} else {
							cuentaOrigen.rollback();
							cuentaDestino.rollback();
							System.out.println("Se ha abortado la transacción");

						}
						
						
					}
					
					banco1.closeConn(); 
					banco2.closeConn();
				}
				
				
				
			} catch (UnknownAccountExeption e) {
				
				e.printStackTrace();
		
			} catch (UnknownTransactionException e) {
				
				e.printStackTrace();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			
			} catch (XAException e) {
				
				e.printStackTrace();
			
			}			 
			 
		} while (confirma("Desea realizar otra operación?", scanner));
		 
	}
	
}
