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

public class ClienteGestionPedidos extends BasicFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ClienteGestionPedidos() {
		super(BasicFrame.Size.SMALL);
		
		setGrid(1, 2);
	 
	    JButton buttonAddOrder = new JButton("Añadir Pedido");
	    buttonAddOrder.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				BasicFrame frame = new ClienteGestionPedidosCRUD();
				frame.setVisible(true);
			}
		});
	    contentPanel.add(buttonAddOrder, setPosition(0, 0));

		JButton buttonExit = addExitButton();
	    contentPanel.add(buttonExit, setPosition(0, 2));
	}

}
