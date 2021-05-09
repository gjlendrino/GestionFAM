package org.gestionfam.modelo.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteBD {
	
	private static ClienteBD cliente = null;
	HSQLDB db = null;

	private ClienteBD() {
        try {
            db = new HSQLDB("db_file");
            crearBaseDeDatos();
        } catch (Exception ex1) {
            ex1.printStackTrace();
            return;
        }
	}
	
	public static ClienteBD getClienteBD() {
		if (cliente == null) {
			cliente = new ClienteBD();
		}
		return cliente;
	}
	
	public void crearBaseDeDatos() throws SQLException {
		db.crud("DROP TABLE IF EXISTS personas CASCADE");
		db.crud("CREATE TABLE personas ("
                + "personas_id INTEGER IDENTITY," 
                + "personas_tipo VARCHAR(256),"
                + "personas_nombre VARCHAR(256))");
		db.crud("INSERT INTO personas (personas_id, personas_tipo, personas_nombre) VALUES (1, 'Jefe', '')");
		db.crud("INSERT INTO personas (personas_id, personas_tipo, personas_nombre) VALUES (2, 'Cliente', '')");

		db.crud("DROP TABLE IF EXISTS pedidos CASCADE");
		db.crud("CREATE TABLE pedidos (" 
                + "pedidos_id INTEGER IDENTITY,"
                + "personas_id INTEGER)"); 
		db.crud("ALTER TABLE pedidos ADD FOREIGN KEY (personas_id) REFERENCES personas(personas_id)");
	}
	
	public void pararBaseDeDatos() throws SQLException {
		db.parar();
	}
	
	public boolean existeUsuario(String tipo, String nombre) throws SQLException {
		ResultSet rs = db.query("SELECT * FROM personas WHERE personas_tipo = '" + tipo + "' AND personas_nombre ='" + nombre + "'");
		if (rs.next()) {
			return true;
		}
		return false;
	}

}
