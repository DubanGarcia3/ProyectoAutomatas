package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.ActionCommand;
import controller.Controller;

public class JDialogInitial extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lbTitleDialog;
	private JButton btnDrawing;
	private JButton btnTransitionFunctions;
	
	public JDialogInitial(JFrameMainWindow frameMainWindow) {
		super(frameMainWindow);
		this.setBackground(new Color(217, 227, 229));
		this.setLayout(new GridLayout(2, 1, 10, 5));
		this.setSize(600, 200);
		this.setLocationRelativeTo(frameMainWindow);
		this.setResizable(true);
		this.setBackground(Color.WHITE);
		init();	
	}

	private void init() {
		Font font = new Font("Century Gothic", 1, 14);
		lbTitleDialog = new JLabel("�C�mo desea ingresar el automata?",JLabel.CENTER);
		lbTitleDialog.setFont(font);
		lbTitleDialog.setBackground(Color.WHITE);
		this.add(lbTitleDialog);
		
		JPanel panelbtns = new JPanel();
		panelbtns.setLayout(new GridLayout(1, 3, 10, 5));
		panelbtns.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panelbtns.setBackground(Color.WHITE);
	
		btnDrawing = new JButton("Dibujando");
		btnDrawing.addActionListener(Controller.getInstance());
		btnDrawing.setActionCommand(ActionCommand.SHOW_WINDOW_DRAWING.name());
		btnDrawing.setFont(font);
		btnDrawing.setBackground(Color.decode("#F5B29B"));
//		panelbtns.add(btnDrawing);
		
		btnTransitionFunctions = new JButton("Funciones de Transici�n");
		btnTransitionFunctions.addActionListener(Controller.getInstance());
		btnTransitionFunctions.setActionCommand(ActionCommand.SHOW_DIALOG_ADD_TRANSITION_FUNCION.name());
		btnTransitionFunctions.setFont(font);
		btnTransitionFunctions.setBackground(Color.decode("#C19EB1"));
		panelbtns.add(btnTransitionFunctions);
		
//		btnTransitionMatrix = new JButton("Matriz de transiciones");
//		btnTransitionMatrix.addActionListener(Controller.getInstance());
//		btnTransitionMatrix.setActionCommand(ActionCommand.SHOW_DIALOG_ADD_TRANSITION_TABLE.name());
//		panelbtns.add(btnTransitionMatrix);
		
		this.add(panelbtns);
	}
}