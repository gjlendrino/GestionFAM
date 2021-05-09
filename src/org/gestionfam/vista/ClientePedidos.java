package org.gestionfam.vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;

import org.gestionfam.controlador.Main;

public class ClientePedidos extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ClientePedidos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		setContentPane(contentPane);
		
		JTree tree = new JTree();
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnNewButton = new JButton("Nuevo Pedido");
		panel.add(btnNewButton);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.salir();
			}
		});
		panel.add(btnSalir);
		
		contentPane.add(tree);
		contentPane.add(panel);
	}

}
