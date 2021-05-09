package org.gestionfam.modelo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HSQLDB {
	
	Connection conn;
    Statement st = null;

	public HSQLDB(String db_file_name_prefix) throws ClassNotFoundException, SQLException {
		Class.forName("org.hsqldb.jdbcDriver");
		conn = DriverManager.getConnection("jdbc:hsqldb:"
                + db_file_name_prefix,    // filenames
                "sa",                     // username
                "");                      // password
        st = conn.createStatement();    // statements
    }
	
	public void parar() throws SQLException {
        st.execute("SHUTDOWN");
        st.close();
        conn.close();
	}
	
	public synchronized void crud(String expression) throws SQLException {
        int i = st.executeUpdate(expression);    // run the query
        if (i == -1) {
            System.out.println("db error : " + expression);
        }
    }
	
	public synchronized ResultSet query(String expression) throws SQLException {
        return st.executeQuery(expression);
	}

}
