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
		conn = DriverManager.getConnection("jdbc:hsqldb:" + db_file_name_prefix,    // filenames
                "sa",                     // username
                "");                      // password
        st = conn.createStatement();    // statements
    }
	
	public void shutdown() throws SQLException {
        st.execute("SHUTDOWN");
        st.close();
        conn.close();
	}
	
	public synchronized boolean crud(String expression) {
		boolean ret = true;
		try {
			int i = st.executeUpdate(expression);    // run the query
	        if (i == -1) {
	            System.out.println("db error : " + expression);
	            ret = false;
	        }
		} catch (SQLException e) {
			e.printStackTrace();
            ret = false;
		}
        return ret;
    }
	
	public synchronized ResultSet query(String expression) {
		ResultSet result = null;
		try {
			result = st.executeQuery(expression);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return result;
	}

}
