package org.gestionfam.vista;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.gestionfam.modelo.Persona;
import org.gestionfam.modelo.db.DBClient;

public class JefeGestionUsuariosCRUD extends BasicFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3022589496077731771L;

	protected enum Type {
		ADD_CLIENT,
		UPDATE_CLIENT,
		REMOVE_CLIENT,
		ADD_EMPLOYEE,
		UPDATE_EMPLOYEE,
		REMOVE_EMPLOYEE
	}
	
	Type type;
	JTree tree;
	JTextField text;

	/**
	 * Create the frame.
	 */
	public JefeGestionUsuariosCRUD(Type type) {
		this.type = type;
		
		setGrid(1, 4);
		gbl_contentPane.rowWeights = new double[]{10.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};

		tree = new JTree();
		String buttonText = "";
		switch (type) {
		case ADD_CLIENT:
		{
			buttonText = "Añadir Cliente";
			DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
			DefaultMutableTreeNode root = new DefaultMutableTreeNode("Tipos de Clientes");
			root.add(new DefaultMutableTreeNode(Persona.Type.Particular.toString()));
			root.add(new DefaultMutableTreeNode(Persona.Type.Empresa.toString()));
			model.setRoot(root);
			break;
		}
		case ADD_EMPLOYEE:
		{
			buttonText = "Añadir Empleado";
			DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
			DefaultMutableTreeNode root = new DefaultMutableTreeNode("Tipos de Empleados");
			root.add(new DefaultMutableTreeNode(Persona.Type.Jefe.toString()));
			root.add(new DefaultMutableTreeNode(Persona.Type.Comercial.toString()));
			root.add(new DefaultMutableTreeNode(Persona.Type.ArtesanoEnPlantilla.toString()));
			root.add(new DefaultMutableTreeNode(Persona.Type.ArtesanoConContratoPorHoras.toString()));
			model.setRoot(root);
			break;
		}
		case UPDATE_CLIENT:
			buttonText = "Actualizar Cliente";
			refreshClients();
			break;
		case UPDATE_EMPLOYEE:
			buttonText = "Actualizar Empleado";
			refreshEmployees();
			break;
		case REMOVE_CLIENT:
			buttonText = "Borrar Cliente";
			refreshClients();
			break;
		case REMOVE_EMPLOYEE:
			buttonText = "Borrar Empleado";
			refreshEmployees();
			break;
		}
	    contentPanel.add(tree, setPosition(0, 0, GridBagConstraints.BOTH));
	 
	    text = new JTextField();
	    text.setColumns(10);
	    contentPanel.add(text, setPosition(0, 1));
	    
		switch (type) {
		case UPDATE_CLIENT:
		case UPDATE_EMPLOYEE:
		case REMOVE_CLIENT:
		case REMOVE_EMPLOYEE:
			text.setText(Persona.Empty);
			tree.addTreeSelectionListener(new TreeSelectionListener() {
				@Override
				public void valueChanged(TreeSelectionEvent event) {
					String value = Persona.Empty;
					TreePath pt = event.getNewLeadSelectionPath();
					if (pt !=  null) {
						DefaultMutableTreeNode nodeSelected = (DefaultMutableTreeNode)pt.getLastPathComponent();
						if (nodeSelected != null && nodeSelected.isLeaf()) {
							String id_nombre = nodeSelected.getUserObject().toString();
							value = id_nombre.substring(id_nombre.indexOf('_') + 1);
						}
					}
					text.setText(value);
				}
			});
			break;
		default:
			break;	
		}
	 
	    JButton buttonOK = new JButton(buttonText);
	    buttonOK.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (type) {
				case ADD_CLIENT:
				case ADD_EMPLOYEE:
					addUser();
					break;
				case UPDATE_CLIENT:
				case UPDATE_EMPLOYEE:
					updatePerson();
					break;
				case REMOVE_CLIENT:
				case REMOVE_EMPLOYEE:
					removePerson();
					break;
				}
			}
		});
	    contentPanel.add(buttonOK, setPosition(0, 2));
	 
	    JButton buttonCancel = addCancelButton(this);
	    contentPanel.add(buttonCancel, setPosition(0, 3));
	}
	
	protected void addUser() {
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
		if (text.getText().equalsIgnoreCase(Persona.Empty)) {
			return;
		}
		String personas_tipo = (String)node.getUserObject();
		DBClient.getClient().addPerson(personas_tipo, text.getText());
	}
	
	protected DefaultMutableTreeNode getTreeNode(Persona.Type type) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(type.toString());
		ArrayList<String> users = DBClient.getClient().getUsers(type.toString());
		if (users.size() == 0) {
			node.add(new DefaultMutableTreeNode(Persona.Empty));
		}
		else {
			for (String user : users) {
				node.add(new DefaultMutableTreeNode(user));
			}
		}
		return node;
	}
	
	protected void refreshClients() {
		DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Tipos de Clientes");
		root.removeAllChildren();
		root.add(getTreeNode(Persona.Type.Particular));
		root.add(getTreeNode(Persona.Type.Empresa));
		model.setRoot(root);
	}
	
	protected void refreshEmployees() {
		DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Tipos de Empleados");
		root.removeAllChildren();
		root.add(getTreeNode(Persona.Type.Jefe));
		root.add(getTreeNode(Persona.Type.Comercial));
		root.add(getTreeNode(Persona.Type.ArtesanoEnPlantilla));
		root.add(getTreeNode(Persona.Type.ArtesanoEnPlantilla));
		model.setRoot(root);
	}
	
	protected void updatePerson() {
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
		if (text.getText().equalsIgnoreCase(Persona.Empty)) {
			return;
		}
		String id_nombre = node.getUserObject().toString();
		String personas_id = id_nombre.substring(0, id_nombre.indexOf('_'));
		DBClient.getClient().updatePerson(personas_id, text.getText());
		switch (type) {
		case UPDATE_CLIENT:
			refreshClients();
			break;		
		case UPDATE_EMPLOYEE:
			refreshEmployees();
			break;				
		}
	}
	
	protected void removePerson() {
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
		if (text.getText().equalsIgnoreCase(Persona.Empty)) {
			return;
		}
		String id_nombre = node.getUserObject().toString();
		String personas_id = id_nombre.substring(0, id_nombre.indexOf('_'));
		DBClient.getClient().removePerson(personas_id);
		switch (type) {
		case UPDATE_CLIENT:
			refreshClients();
			break;		
		case UPDATE_EMPLOYEE:
			refreshEmployees();
			break;				
		}
	}

}
