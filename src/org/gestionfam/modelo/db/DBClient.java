package org.gestionfam.modelo.db;

import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	public void dbCreate() throws SQLException {
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
	
	public void dbPopulate() throws SQLException {
		db.crud("INSERT INTO personas (personas_id, personas_tipo, personas_nombre) VALUES (1, 'Jefe', '')");
		db.crud("INSERT INTO personas (personas_id, personas_tipo, personas_nombre) VALUES (2, 'Cliente', '')");
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
	
	public boolean addClient(String personas_tipo, String personas_nombre) {
		return db.crud("INSERT INTO personas (personas_tipo, personas_nombre) VALUES ('" + personas_tipo + "', '" + personas_nombre + "')");
	}

}
