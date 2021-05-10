package org.gestionfam.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class JefeGestionUsuarios extends BasicFrame {

	/**
	 * Create the frame.
	 */
	public JefeGestionUsuarios() {
		super(BasicFrame.Size.SMALL);
		
		setGrid(1, 2);
	 
	    JButton buttonAddClient = new JButton("Alta de Cliente");
	    buttonAddClient.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				BasicFrame frame = new JefeGestionUsuariosCRUD(JefeGestionUsuariosCRUD.Type.ADD_CLIENT);
				frame.setVisible(true);
			}
		});
	    contentPanel.add(buttonAddClient, setPosition(0, 0));

		JButton buttonExit = addExitButton();
	    contentPanel.add(buttonExit, setPosition(0, 1));
	}

}
