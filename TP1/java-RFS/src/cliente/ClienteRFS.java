package cliente;

import java.util.Scanner;

public class ClienteRFS {
	
	public static ClientStub stub;
		
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in); 
		System.out.println("Ingrese el nombre del archivo a leer");
		String file_name = sc.nextLine();
		
		stub = new ClientStub("localhost", 7896);
		stub.rfs_open(file_name);

	
	}
	
}
