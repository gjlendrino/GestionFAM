package org.gestionfam.vista;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import org.gestionfam.modelo.db.DBClient;

public class JefeGestionUsuariosCRUD extends BasicFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3022589496077731771L;

	protected enum Type {
		ADD_CLIENT
	}
	
	JTree tree;
	JTextField text;

	/**
	 * Create the frame.
	 */
	public JefeGestionUsuariosCRUD(Type type) {
		setGrid(1, 4);
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
		
		tree = new JTree(root);
	    contentPanel.add(tree, setPosition(0, 0, GridBagConstraints.BOTH));
	 
	    text = new JTextField();
	    text.setColumns(10);
	    contentPanel.add(text, setPosition(0, 1));
	 
	    JButton buttonOK = new JButton(buttonText);
	    buttonOK.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (type) {
				case ADD_CLIENT:
					addClient();
					break;
				}
			}
		});
	    contentPanel.add(buttonOK, setPosition(0, 2));
	 
	    JButton buttonCancel = addCancelButton(this);
	    contentPanel.add(buttonCancel, setPosition(0, 3));
	}
	
	protected void addClient() {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		if (node == null) {
		    return;
		}
		if (node.isLeaf() == false) {
			return;
		}
		if (text.getText().equalsIgnoreCase("")) {
			return;
		}
		Object userObject = node.getUserObject();
		String personas_tipo = (String)userObject;
		DBClient.getClient().addClient(personas_tipo, text.getText());
	}

}
