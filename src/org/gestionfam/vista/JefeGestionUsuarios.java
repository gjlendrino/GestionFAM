package org.gestionfam.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class JefeGestionUsuarios extends VistaBase {

	/**
	 * Create the frame.
	 */
	public JefeGestionUsuarios() {
		super(VistaBase.Size.SMALL);
		
		rejilla(1, 2);
	 
	    JButton buttonAddClient = new JButton("Alta de Cliente");
	    buttonAddClient.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				VistaBase frame = new JefeGestionUsuariosCRUD(JefeGestionUsuariosCRUD.Type.ADD_CLIENT);
				frame.setVisible(true);
			}
		});
	    contentPane.add(buttonAddClient, posicion(0, 0));

		JButton buttonExit = addExitButton();
	    contentPane.add(buttonExit, posicion(0, 1));
	}

}
