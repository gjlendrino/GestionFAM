package org.gestionfam.vista;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.gestionfam.controlador.Main;
import org.gestionfam.modelo.Persona;
import org.gestionfam.modelo.db.ClienteBD;

public class Login extends VistaBase {

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Login() throws SQLException {
		super(VistaBase.Size.SMALL);
		
		rejilla(2, 3);
		
		JLabel lbl1 = new JLabel("Tipo de usuario:");
	    contentPane.add(lbl1, posicion(0, 0));
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("");
		comboBox.addItem("Jefe");
		comboBox.addItem("Cliente");
		comboBox.addItem("Artesano");
		comboBox.addItem("Comercial");
	    contentPane.add(comboBox, posicion(1, 0));
		
		JLabel lbl2 = new JLabel("Nombre:");
	    contentPane.add(lbl2, posicion(0, 1));
		
		JTextField textName = new JTextField();
		textName.setColumns(10);
	    contentPane.add(textName, posicion(1, 1));
		
		JButton buttonOK = new JButton("Entrar");
		buttonOK.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					boolean exists = ClienteBD.getClienteBD().existeUsuario(comboBox.getSelectedItem().toString(), textName.getText());
					if (exists && comboBox.getSelectedItem().toString().equalsIgnoreCase(Persona.Tipo.Jefe.toString())) {
						VistaBase frame = new JefeGestionUsuarios();
						frame.setVisible(true);
						setVisible(false);
					}
					/*else if (existe && comboBox.getSelectedItem().toString().equalsIgnoreCase(Persona.Tipo.Cliente.toString())) {
						ClientePedidos frameClientePedidos = new ClientePedidos();
						frameClientePedidos.setVisible(true);
						setVisible(false);
					}*/
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	    contentPane.add(buttonOK, posicion(0, 2));
		
		JButton buttonExit = addExitButton();
	    contentPane.add(buttonExit, posicion(1, 2));
	}

}
