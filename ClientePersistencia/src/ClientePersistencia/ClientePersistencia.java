package ClientePersistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientePersistencia {

	static final String CONNECTOR = "com.mysql.jdbc.Driver";
	static final String URL = "jdbc:mysql://localhost:3306/hotel?autoReconnect=true&useSSL=false";
	static final String LOGIN = "root";
	static final String PASS = "";

	public static void createTableCliente() {
		/* En MySQL Workbench hemos creado la tabla "hotel". Desde eclipse vamos a crear la tabla Cliente, aunque también lo podríamos crear desde MySQL Workbench. */

		String sentenciaCrearBD = "CREATE TABLE IF NOT EXISTS hotel.clientes (idCliente INT NOT NULL AUTO_INCREMENT, nombre VARCHAR(20) NOT NULL, apellidos VARCHAR(40) NOT NULL, email VARCHAR(40) NOT NULL, dni VARCHAR(9) NOT NULL, clave VARCHAR(20) NOT NULL, PRIMARY KEY (idCliente));";
		try {
			Class.forName(CONNECTOR);
			Connection connect = DriverManager.getConnection(URL, LOGIN, PASS);
			connect.createStatement().executeUpdate(sentenciaCrearBD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int createCliente (String nombre, String apellidos, String email, String dni, String clave) {
		/* Devuelve el id del nuevo cliente */

		String sentenciaCrearCliente = "INSERT INTO clientes (nombre, apellidos, email, dni, clave) VALUES('" + nombre + "', '" + apellidos + "', '" + email + "', '" + dni + "', '" + clave + "');";
		String sentenciaRecuperarId = "SELECT LAST_INSERT_ID();";
		Integer idCliente = -1;

		try {
			Class.forName(CONNECTOR);
			Connection connect = DriverManager.getConnection(URL, LOGIN, PASS);
			Statement stm = connect.createStatement();
			stm.executeUpdate(sentenciaCrearCliente);
			ResultSet rs = stm.executeQuery(sentenciaRecuperarId);
			rs.next();
			idCliente = rs.getInt(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return idCliente;
	}

	public static String readCliente (int idCliente, String campo) {
		/* Devuelve el valor de la columna "campo" del cliente identificado por "idCliente" */

		String sentenciaRecuperarCampo = "SELECT "+ campo +" FROM clientes WHERE idCliente = '" + idCliente + "';";
		String valor = "";

		try {
			Class.forName(CONNECTOR);
			Connection connect = DriverManager.getConnection(URL, LOGIN, PASS);
			Statement stm = connect.createStatement();
			ResultSet rs = stm.executeQuery(sentenciaRecuperarCampo);
			rs.next();
			valor = rs.getString(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Los datos introducidos no corresponden a ningún cliente");
		}

		return valor;
	}

	public static boolean updateCliente (int idCliente, String campo, String nuevoValor) {
		/* Actualiza el valor de la columna "campo" del cliente identificado por "idCliente". Devuelve true si se ha logrado actulizar. */

		String sentenciaModificarCampo = "UPDATE clientes SET " + campo +"='"+nuevoValor+"' WHERE idCliente = '" + idCliente + "';";
		Boolean correcto = false;

		try {
			Class.forName(CONNECTOR);
			Connection connect = DriverManager.getConnection(URL, LOGIN, PASS);
			connect.createStatement().executeUpdate(sentenciaModificarCampo);
			correcto = true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return correcto;
	}

	public static boolean deleteCliente(int idCliente) {
		/* Elimina el cliente identificado por "idCliente". Devuelve true si se ha logrado eliminar. */

		String sentenciaEliminarCliente = "DELETE FROM clientes WHERE (idCliente ='"+idCliente+"');";
		Boolean correcto = false;

		try {
			Class.forName(CONNECTOR);
			Connection connect = DriverManager.getConnection(URL, LOGIN, PASS);
			connect.createStatement().executeUpdate(sentenciaEliminarCliente);
			correcto = true; 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return correcto;
	}
}
