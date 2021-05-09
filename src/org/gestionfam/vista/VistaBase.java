package org.gestionfam.vista;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.gestionfam.controlador.Main;

public class VistaBase extends JFrame {
	
	public enum Size {
		NORMAL,
		SMALL
	}

	protected JPanel contentPane;
	protected GridBagLayout gbl_contentPane;

	/**
	 * Create the frame.
	 */
	public VistaBase(VistaBase.Size size) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (size == Size.SMALL) {
			setBounds(100, 100, 400, 300);
		}
		else {
			setBounds(100, 100, 800, 600);
		}
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		setContentPane(contentPane);
	}

	public VistaBase() {
		this(VistaBase.Size.NORMAL);
	}
	
	protected void rejilla(int columnas, int filas) {
		switch (columnas) {
		case 1:
			gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			break;
		case 2:
			gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
			break;
		case 3:
			gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
			break;
		case 4:
			gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
			break;
		}
		switch (filas) {
		case 1:
			gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
			break;
		case 2:
			gbl_contentPane.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
			break;
		case 3:
			gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
			break;
		case 4:
			gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
			break;
		}
	}
	
	protected GridBagConstraints posicion(int gridx, int gridy, int fill) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = fill;
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		return gbc;
	}
	
	protected GridBagConstraints posicion(int gridx, int gridy) {
		return posicion(gridx, gridy, GridBagConstraints.HORIZONTAL);
	}
	
	protected JButton addExitButton() {
		JButton buttonExit = new JButton("Salir");
		buttonExit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.salir();
			}
		});
		return buttonExit;
	}
	
	protected JButton addCancelButton(VistaBase frame) {
		JButton buttonCancel = new JButton("Volver");
	    buttonCancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		return buttonCancel;
	}

}
