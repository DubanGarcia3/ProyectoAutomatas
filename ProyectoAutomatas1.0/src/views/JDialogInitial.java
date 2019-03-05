package views;

import java.awt.Color;
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
		
		init();	
	}

	private void init() {
		
		lbTitleDialog = new JLabel("¿Cómo desea ingrear el automata?",JLabel.CENTER);	
		this.add(lbTitleDialog);
		
		JPanel panelbtns = new JPanel();
		panelbtns.setLayout(new GridLayout(1, 3, 10, 5));
		panelbtns.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	
		btnDrawing = new JButton("Dibujando");
		btnDrawing.addActionListener(Controller.getInstance());
		btnDrawing.setActionCommand(ActionCommand.SHOW_WINDOW_DRAWING.name());
		panelbtns.add(btnDrawing);
		
		btnTransitionFunctions = new JButton("Funciones de Transición");
		btnTransitionFunctions.addActionListener(Controller.getInstance());
		btnTransitionFunctions.setActionCommand(ActionCommand.SHOW_DIALOG_ADD_TRANSITION_FUNCION.name());
		panelbtns.add(btnTransitionFunctions);
		
//		btnTransitionMatrix = new JButton("Matriz de transiciones");
//		btnTransitionMatrix.addActionListener(Controller.getInstance());
//		btnTransitionMatrix.setActionCommand(ActionCommand.SHOW_DIALOG_ADD_TRANSITION_TABLE.name());
//		panelbtns.add(btnTransitionMatrix);
		
		this.add(panelbtns);
	}
}