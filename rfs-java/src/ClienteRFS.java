import java.util.Scanner;

public class ClienteRFS {
	
	public ClientStub stub;
	
	public ClienteRFS() {
		this.stub = new ClientStub("localhost", 7896);
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in); 
		System.out.println("Ingrese el nombre del archivo a leer");
		String file_name = sc.nextLine();
		
		
	
	
	}
	
}
