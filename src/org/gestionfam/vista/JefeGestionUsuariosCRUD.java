package org.gestionfam.vista;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class JefeGestionUsuariosCRUD extends VistaBase {
	
	protected enum Type {
		ADD_CLIENT
	}

	/**
	 * Create the frame.
	 */
	public JefeGestionUsuariosCRUD(Type type) {
		rejilla(1, 4);
		gbl_contentPane.rowWeights = new double[]{10.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		
		DefaultMutableTreeNode root = null;
		String buttonText = "";
		switch (type) {
		case ADD_CLIENT:
			buttonText = "Añadir Cliente";
			root = new DefaultMutableTreeNode("Tipos de clientes");
			root.add(new DefaultMutableTreeNode("Particular"));
			root.add(new DefaultMutableTreeNode("Empresa"));
			break;
		}
		
		JTree tree = new JTree(root);
	    contentPane.add(tree, posicion(0, 0, GridBagConstraints.BOTH));
	 
	    JTextField text = new JTextField();
	    text.setColumns(10);
	    contentPane.add(text, posicion(0, 1));
	 
	    JButton buttonOK = new JButton(buttonText);
	    buttonOK.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				VistaBase frame = new JefeGestionUsuariosCRUD(JefeGestionUsuariosCRUD.Type.ADD_CLIENT);
				frame.setVisible(true);
			}
		});
	    contentPane.add(buttonOK, posicion(0, 2));
	 
	    JButton buttonCancel = addCancelButton(this);
	    contentPane.add(buttonCancel, posicion(0, 3));
	}

}
