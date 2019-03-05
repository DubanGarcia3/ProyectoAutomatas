package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import controller.ActionCommand;
import controller.Controller;
import models.State;

public class JDialogDataAutomaton extends JDialog implements KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lbTitleDialog;
	private JTextField jtextAlphabet;
	private JTextField jTextStateList;
	private JComboBox<State> jComboBoxInitialState;
	private JTextPane jTextPaneFuncionsTransitions;
	private JButton btnAccept;

	private JPanel panelCheckBox;
	
	public JDialogDataAutomaton(JFrameMainWindow frameMainWindow) {
		super(frameMainWindow);
		this.setBackground(new Color(217, 227, 229));
		this.setLayout(new GridLayout(7, 1, 10, 10));
		this.setSize(600, 500);
		this.setLocationRelativeTo(frameMainWindow);
		this.setResizable(true);
		init();	
	}

	private void init() {
		lbTitleDialog = new JLabel("Componentes del Automata:",JLabel.CENTER);
		this.add(lbTitleDialog);
		
		JPanel panelAlphabet = new JPanel(new GridLayout(1,2));
		JLabel lbAlphabet = new JLabel("Ingrese el Alfabeto separado por comas, ejemplo(a,b,c) ");
		jtextAlphabet = new JTextField();
		panelAlphabet.add(lbAlphabet);
		panelAlphabet.add(jtextAlphabet);
		this.add(panelAlphabet);
		
		JPanel panelStateList = new JPanel(new GridLayout(1,2));
		JLabel lbStateList = new JLabel("Ingrese los estados separados por comas, ejemplo(q0,q1,q2) ");
		jTextStateList = new JTextField();
		jTextStateList.addKeyListener(this);
		panelStateList.add(lbStateList);
		panelStateList.add(jTextStateList);
		this.add(panelStateList);
		
		JPanel panelInitialState = new JPanel(new GridLayout(1,2));
		JLabel lbInitialState = new JLabel("Selecciona el estado inicial");
		jComboBoxInitialState = new JComboBox<State>();
		jComboBoxInitialState.setEnabled(false);
		panelInitialState.add(lbInitialState);
		panelInitialState.add(jComboBoxInitialState);
		this.add(panelInitialState);
		
		JPanel panelStatesAcceptable = new JPanel(new BorderLayout()); 
		JLabel lbStateAcceptable = new JLabel("Selecciona el(los) estado(s) aceptable(s)");
		panelCheckBox = new JPanel(new GridLayout(1, 3));
		panelStatesAcceptable.add(lbStateAcceptable,BorderLayout.PAGE_START);
		JScrollPane jScrollPane = new JScrollPane(panelCheckBox);
		panelStatesAcceptable.add(jScrollPane,BorderLayout.CENTER);
		
		this.add(panelStatesAcceptable);
		
		JPanel jPanelFuncions = new JPanel(new BorderLayout());
		JLabel lbFuncionsTransitions = new JLabel("Ingresa las funciones de transicion");
			
//		JPanel	jPanelFuncionsTransitions = new JPanel(new GridLayout)
			
		this.add(jTextPaneFuncionsTransitions);
		
		btnAccept = new JButton("Aceptar");
		btnAccept.addActionListener(Controller.getInstance());
		btnAccept.setEnabled(false);
		btnAccept.setActionCommand(ActionCommand.ADD_AUTOMATON_BY_FUNTIONS_TRANSITIONS.name());
		this.add(btnAccept);
	}

	@Override
	public void keyPressed(KeyEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent event) {
		if (jTextStateList.isFocusable()) {
			switch (event.getKeyChar()) {
			case ';':
				State[] states = getStatesList(jTextStateList.getText());
//				jTextStateList.setFocusable(false);
				DefaultComboBoxModel<State> boxModel = new DefaultComboBoxModel<State>(states);
				jComboBoxInitialState.setEnabled(true);
				jComboBoxInitialState.setModel(boxModel);
				createCheckBoxAcceptable(states);
				jTextPaneFuncionsTransitions.setEditable(true);
			break;
			}
		}
	}

	private void createCheckBoxAcceptable(State[] states) {
		panelCheckBox.removeAll();
		panelCheckBox.setLayout(new GridLayout(states.length/4,4));
		for (State state : states) {
			panelCheckBox.add(new JCheckBox(state.toString()));
			repaint();
		}
	}

	private State[] getStatesList(String text) {
		String[] statesList = text.replaceAll(";","").split(",");
		State[] states = new State[statesList.length];
		for (int i = 0; i < statesList.length; i++) {
			states[i] = new State(false,statesList[i]);
		}
		return states;
	}


	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}