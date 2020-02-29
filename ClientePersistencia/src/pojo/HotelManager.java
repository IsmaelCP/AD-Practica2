package pojo;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HotelManager {

	public static void main(String[] args) {
		/*
		// Crear un nuevo registro
		Clientes cliente = new Clientes("Ismael", "Carracedo", "exfacia@gmail.com", "11111111B", "SE-567");
		createCliente(cliente);
		 */
		/*
		// Leer un registro
		readCliente();
		*/
		/*
		// Actualizar un registro
		Clientes actualizarCliente = new Clientes(1, "Ramón", "García", "rgarcia@gmail.com", "22222222B", "SE-444");
		updateCliente(actualizarCliente);
		 */
		
		// Eliminar un registro
		Clientes cliente10 = new Clientes(4);
		deleteCliente(cliente10);
		
	}

	private static SessionFactory getSessionFactory() {
		SessionFactory sessionFactory = new Configuration().addAnnotatedClass(Clientes.class).configure().buildSessionFactory();
		return sessionFactory;
	}

	public static void createCliente(Clientes cliente) {
		Session sessionObj = getSessionFactory().openSession();
		Transaction transObj = sessionObj.beginTransaction();
		sessionObj.save(cliente);
		transObj.commit();
		sessionObj.close();
		System.out.println("Cliente " + cliente.getIdCliente() + "creado correctamente");
	}

	public static List readCliente() {
		Session sessionObj = getSessionFactory().openSession();
		String c = "FROM Clientes";
		List resultado = sessionObj.createQuery(c).list();
		Iterator clienteIterador = resultado.iterator();
		while(clienteIterador.hasNext()) {
			Clientes c2 = (Clientes) clienteIterador.next();
			System.out.println(" - " + c2.getNombre() + " - " + c2.getApellidos() + " - " + c2.getEmail() + " - " + c2.getDni() + " - " + c2.getClave());
		}
		sessionObj.close();
		return resultado;
	}

	public static void updateCliente(Clientes cliente) {
		Session sessionObj = getSessionFactory().openSession();
		Transaction transObj = sessionObj.beginTransaction();
		Clientes clienteBD = (Clientes) sessionObj.load(Clientes.class, cliente.getIdCliente());
		// Modificamos los atributos del registro de la BD
		clienteBD.setNombre(cliente.getNombre());
		clienteBD.setApellidos(cliente.getApellidos());
		clienteBD.setEmail(cliente.getEmail());
		clienteBD.setDni(cliente.getDni());
		clienteBD.setClave(cliente.getClave());
		
		transObj.commit();
		sessionObj.close();
		System.out.println("Registro actualizado correctamente");
	}

	public static void deleteCliente(Clientes cliente) {
		Session sessionObj = getSessionFactory().openSession();
		Transaction transObj = sessionObj.beginTransaction();
		Clientes clienteBD = (Clientes) sessionObj.load(Clientes.class, cliente.getIdCliente());
		sessionObj.delete(clienteBD);
		sessionObj.close();
		System.out.println("Registro eliminado correctamente");
	}
}
