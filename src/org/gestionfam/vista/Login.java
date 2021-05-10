package org.gestionfam.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.gestionfam.modelo.Persona;
import org.gestionfam.modelo.db.DBClient;

public class Login extends BasicFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1185834276523038033L;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Login() throws SQLException {
		super(BasicFrame.Size.SMALL);
		
		setGrid(2, 3);
		
		JLabel lbl1 = new JLabel("Tipo de usuario:");
	    contentPanel.add(lbl1, setPosition(0, 0));
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("");
		comboBox.addItem("Jefe");
		comboBox.addItem("Cliente");
		comboBox.addItem("Artesano");
		comboBox.addItem("Comercial");
	    contentPanel.add(comboBox, setPosition(1, 0));
		
		JLabel lbl2 = new JLabel("Nombre:");
	    contentPanel.add(lbl2, setPosition(0, 1));
		
		JTextField textName = new JTextField();
		textName.setColumns(10);
	    contentPanel.add(textName, setPosition(1, 1));
		
		JButton buttonOK = new JButton("Entrar");
		buttonOK.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					boolean exists = DBClient.getClient().userExists(comboBox.getSelectedItem().toString(), textName.getText());
					if (exists && comboBox.getSelectedItem().toString().equalsIgnoreCase(Persona.Tipo.Jefe.toString())) {
						BasicFrame frame = new JefeGestionUsuarios();
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
	    contentPanel.add(buttonOK, setPosition(0, 2));
		
		JButton buttonExit = addExitButton();
	    contentPanel.add(buttonExit, setPosition(1, 2));
	}

}
