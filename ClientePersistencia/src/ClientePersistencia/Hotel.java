package ClientePersistencia;

public class Hotel {

	public static void main(String[] args) {
		int id = ClientePersistencia.createCliente("Mar�a Jos�", "Mart�nez", "mjmartinez@grupostudium.com", "12345678A", "Studium2018");
		System.out.println(ClientePersistencia.readCliente(id, "apellidos"));
		
		/*
		ClientePersistencia.updateCliente(id, "apellidos", "Mart�nez XXXXXXX");
		System.out.println(ClientePersistencia.readCliente(id, "apellidos"));
		
		ClientePersistencia.deleteCliente(id);
		System.out.println(ClientePersistencia.readCliente(id, "apellidos"));
	*/
	}

}
