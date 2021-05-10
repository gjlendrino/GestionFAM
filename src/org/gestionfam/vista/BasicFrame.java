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

public class BasicFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4443385630336080959L;

	public enum Size {
		NORMAL,
		SMALL
	}

	protected JPanel contentPanel;
	protected GridBagLayout gbl_contentPane;

	/**
	 * Create the frame.
	 */
	public BasicFrame(BasicFrame.Size size) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (size == Size.SMALL) {
			setBounds(100, 100, 400, 300);
		}
		else {
			setBounds(100, 100, 800, 600);
		}
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPane);
		setContentPane(contentPanel);
	}

	public BasicFrame() {
		this(BasicFrame.Size.NORMAL);
	}
	
	protected void setGrid(int cols, int rows) {
		switch (cols) {
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
		switch (rows) {
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
	
	protected GridBagConstraints setPosition(int gridx, int gridy, int fill) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = fill;
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		return gbc;
	}
	
	protected GridBagConstraints setPosition(int gridx, int gridy) {
		return setPosition(gridx, gridy, GridBagConstraints.HORIZONTAL);
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
	
	protected JButton addCancelButton(BasicFrame frame) {
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
