package org.gestionfam.modelo.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DBClient {
	
	private static DBClient client = null;
	HSQLDB db = null;

	private DBClient() {
        try {
            db = new HSQLDB("hsqldb_file");
        } catch (Exception ex1) {
            ex1.printStackTrace();
            return;
        }
	}
	
	public static DBClient getClient() {
		if (DBClient.client == null) {
			DBClient.client = new DBClient();
		}
		return DBClient.client;
	}
	
	public void dbCreate() {
		db.crud("DROP TABLE IF EXISTS personas CASCADE");
		db.crud("CREATE TABLE personas ("
                + "personas_id INTEGER IDENTITY," 
                + "personas_tipo VARCHAR(256),"
                + "personas_nombre VARCHAR(256))");

		db.crud("DROP TABLE IF EXISTS pedidos CASCADE");
		db.crud("CREATE TABLE pedidos (" 
                + "pedidos_id INTEGER IDENTITY,"
                + "personas_id INTEGER)"); 
		db.crud("ALTER TABLE pedidos ADD FOREIGN KEY (personas_id) REFERENCES personas(personas_id)");
		

		db.crud("DROP TABLE IF EXISTS sample_table CASCADE");
		db.crud("CREATE TABLE sample_table ( id INTEGER IDENTITY, str_col VARCHAR(256), num_col INTEGER)");
	}
	
	public void dbPopulate() {
		db.crud("INSERT INTO personas (personas_tipo, personas_nombre) VALUES ('Jefe', 'Jefe1')");
		db.crud("INSERT INTO personas (personas_tipo, personas_nombre) VALUES ('Comercial', 'Comercial1')");
		db.crud("INSERT INTO personas (personas_tipo, personas_nombre) VALUES ('Artesano En Plantilla', 'ArtesanoEnPlantilla1')");
		db.crud("INSERT INTO personas (personas_tipo, personas_nombre) VALUES ('Artesano Con Contrato Por Hora', 'ArtesanoConContratoPorHora1')");
		db.crud("INSERT INTO personas (personas_tipo, personas_nombre) VALUES ('Empresa', 'Empresa1')");
		db.crud("INSERT INTO personas (personas_tipo, personas_nombre) VALUES ('Particular', 'Particular1')");
	}
	
	public void dbShutdown() throws SQLException {
		db.shutdown();
	}
	
	public boolean userExists(String personas_tipo, String personas_nombre) throws SQLException {
		ResultSet rs = db.query("SELECT * FROM personas WHERE personas_tipo = '" + personas_tipo + "' AND personas_nombre = '" + personas_nombre + "'");
		if (rs.next()) {
			return true;
		}
		return false;
	}
	
	public boolean addPerson(String personas_tipo, String personas_nombre) {
		return db.crud("INSERT INTO personas (personas_tipo, personas_nombre) VALUES ('" + personas_tipo + "', '" + personas_nombre + "')");
	}
	
	public ArrayList<String> getUsers(String personas_tipo) {
		ArrayList<String> users = new ArrayList<String>();
		ResultSet rs = db.query("SELECT * FROM personas WHERE personas_tipo = '" + personas_tipo + "'");
		if (rs != null) {
			try {
				for (; rs.next(); ) {
					String personas_id = rs.getObject(1).toString();
					String personas_nombre = rs.getObject(3).toString();
					users.add(personas_id + "_" + personas_nombre);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return users;
	}
	
	public boolean updatePerson(String personas_id, String personas_nombre) {
		return db.crud("UPDATE personas SET personas_nombre = '" + personas_nombre + "' WHERE personas_id = " + personas_id);
	}
	
	public boolean removePerson(String personas_id) {
		return db.crud("DELETE FROM personas WHERE personas_id = " + personas_id);
	}

}
