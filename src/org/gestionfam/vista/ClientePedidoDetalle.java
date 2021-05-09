package org.gestionfam.vista;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;

public class ClientePedidoDetalle extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ClientePedidoDetalle() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		JTree tree = new JTree();
		contentPane.add(tree);
		
		JButton btnNewButton = new JButton("A\u00F1adir Mueble");
		contentPane.add(btnNewButton);
		
		JTree tree_1 = new JTree();
		contentPane.add(tree_1);
		
		JButton btnNewButton_1 = new JButton("Quitar Mueble");
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Guardar Pedido");
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Volver");
		contentPane.add(btnNewButton_3);
	}

}
