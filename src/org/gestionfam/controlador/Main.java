package org.gestionfam.controlador;

import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import org.gestionfam.modelo.Persona;
import org.gestionfam.modelo.db.DBClient;
import org.gestionfam.modelo.db.HSQLDB;
import org.gestionfam.vista.JefeGestionUsuarios;
import org.gestionfam.vista.Login;
import org.gestionfam.vista.BasicFrame;

public class Main {

	/**
	 * Launch the application.
	 * @throws InterruptedException 
	 * @throws InvocationTargetException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws InvocationTargetException, InterruptedException, SQLException {

		DBClient.getClient().dbCreate();
		DBClient.getClient().dbPopulate();
		
		EventQueue.invokeAndWait(new Runnable() {
			public void run() {
				try {
					BasicFrame frame = new Login();
//					JefeGestionUsuarios frame = new JefeGestionUsuarios();
//					Test frame = new Test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void salir() {
		try {
			DBClient.getClient().dbShutdown();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.exit(0);		
	}

}
