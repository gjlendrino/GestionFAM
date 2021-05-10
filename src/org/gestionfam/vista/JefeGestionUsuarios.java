package org.gestionfam.vista;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class JefeGestionUsuarios extends BasicFrame {

	/**
	 * Create the frame.
	 */
	public JefeGestionUsuarios() {
		super(BasicFrame.Size.SMALL);
		
		setGrid(1, 7);
	 
	    JButton buttonAddClient = new JButton("Añadir Cliente");
	    buttonAddClient.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				BasicFrame frame = new JefeGestionUsuariosCRUD(JefeGestionUsuariosCRUD.Type.ADD_CLIENT);
				frame.setVisible(true);
			}
		});
	    contentPanel.add(buttonAddClient, setPosition(0, 0));
	 
	    JButton buttonUpdateClient = new JButton("Actualizar Cliente");
	    buttonUpdateClient.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				BasicFrame frame = new JefeGestionUsuariosCRUD(JefeGestionUsuariosCRUD.Type.UPDATE_CLIENT);
				frame.setVisible(true);
			}
		});
	    contentPanel.add(buttonUpdateClient, setPosition(0, 1));
	 
	    JButton buttonRemoveClient = new JButton("Borrar Cliente");
	    buttonRemoveClient.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				BasicFrame frame = new JefeGestionUsuariosCRUD(JefeGestionUsuariosCRUD.Type.REMOVE_CLIENT);
				frame.setVisible(true);
			}
		});
	    contentPanel.add(buttonRemoveClient, setPosition(0, 2));
	    
	    JButton buttonAddEmployee = new JButton("Añadir Empleado");
	    buttonAddEmployee.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				BasicFrame frame = new JefeGestionUsuariosCRUD(JefeGestionUsuariosCRUD.Type.ADD_EMPLOYEE);
				frame.setVisible(true);
			}
		});
	    contentPanel.add(buttonAddEmployee, setPosition(0, 3));
	 
	    JButton buttonUpdateEmployee = new JButton("Actualizar Empleado");
	    buttonUpdateEmployee.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				BasicFrame frame = new JefeGestionUsuariosCRUD(JefeGestionUsuariosCRUD.Type.UPDATE_EMPLOYEE);
				frame.setVisible(true);
			}
		});
	    contentPanel.add(buttonUpdateEmployee, setPosition(0, 4));
	 
	    JButton buttonRemoveEmployee = new JButton("Borrar Empleado");
	    buttonRemoveEmployee.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				BasicFrame frame = new JefeGestionUsuariosCRUD(JefeGestionUsuariosCRUD.Type.REMOVE_EMPLOYEE);
				frame.setVisible(true);
			}
		});
	    contentPanel.add(buttonRemoveEmployee, setPosition(0, 5));

		JButton buttonExit = addExitButton();
	    contentPanel.add(buttonExit, setPosition(0, 6));
	}

}
