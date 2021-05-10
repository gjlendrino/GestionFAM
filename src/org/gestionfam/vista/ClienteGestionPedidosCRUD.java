package org.gestionfam.vista;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.gestionfam.modelo.Mueble;

public class ClienteGestionPedidosCRUD extends BasicFrame {

	JTree tree;
	
	/**
	 * Create the frame.
	 */
	public ClienteGestionPedidosCRUD() {
		
		setGrid(1, 4);
		gbl_contentPane.rowWeights = new double[]{10.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};

		tree = new JTree();
		DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Tipos de Muebles");
		root.add(new DefaultMutableTreeNode(Mueble.Type.MesaDeCafeDeCristal.toString()));
		root.add(new DefaultMutableTreeNode(Mueble.Type.MesaDeCafeDeMadera.toString()));
		root.add(new DefaultMutableTreeNode(Mueble.Type.MesaDeComedor.toString()));
		root.add(new DefaultMutableTreeNode(Mueble.Type.MesaDeDormitorio.toString()));
		root.add(new DefaultMutableTreeNode(Mueble.Type.SillaDeCocina.toString()));
		root.add(new DefaultMutableTreeNode(Mueble.Type.SillaDeOficinaConRuedas.toString()));
		root.add(new DefaultMutableTreeNode(Mueble.Type.SillaDeOficinaSinRuedas.toString()));
		root.add(new DefaultMutableTreeNode(Mueble.Type.SillaPlegable.toString()));
		model.setRoot(root);
	    contentPanel.add(tree, setPosition(0, 0, GridBagConstraints.BOTH));
		
	    JButton buttonCancel = addCancelButton(this);
	    contentPanel.add(buttonCancel, setPosition(0, 3));
	}

}
