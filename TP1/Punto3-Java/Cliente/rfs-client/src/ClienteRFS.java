import java.util.Scanner;

public class ClienteRFS {
	
		
	public static void main(String[] args) {
		ClientStub stub;
		
		Scanner sc = new Scanner(System.in); 
		System.out.println("Ingrese el nombre del archivo a leer");
		String file_name = sc.nextLine();
		
		stub = new ClientStub("localhost", 7896);
		try {
			stub.rfs_open(file_name);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}
	
}


