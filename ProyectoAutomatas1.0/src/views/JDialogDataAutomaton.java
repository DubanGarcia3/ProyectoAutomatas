package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controller.ActionCommand;
import controller.Controller;
import models.Automaton;
import models.State;
import models.Transition;

public class JDialogDataAutomaton extends JDialog implements KeyListener,MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lbTitleDialog;
	private MyJTextField jtextAlphabet;
	private MyJTextField jTextStateList;
	private JComboBox<State> jComboBoxInitialState;
	private JButton btnAccept;
	private JPanel panelCheckBox;
	private JPanel jPanelFuncionsTransitions;
	private JButton btnAddNewFuncion;
	
	private int indexFuncion = -1;

	private Component[] components;
	
	public JDialogDataAutomaton(JFrameMainWindow frameMainWindow) {
		super(frameMainWindow);
		this.setBackground(new Color(217, 227, 229));
		this.setLayout(new GridLayout(7, 1, 10, 10));
		this.setSize(500, 700);
		this.setLocationRelativeTo(frameMainWindow);
		this.setResizable(true);
		init();	
	}

	private void init() {
		lbTitleDialog = new JLabel("Componentes del Automata:",JLabel.CENTER);
		this.add(lbTitleDialog);
		
		JPanel panelAlphabet = new JPanel(new GridLayout(1,2));
		jtextAlphabet = new MyJTextField("Ingrese el Alfabeto separado por comas, ejemplo(a,b,c)");
		panelAlphabet.add(jtextAlphabet);
		this.add(panelAlphabet);
		
		JPanel panelStateList = new JPanel(new GridLayout(1,2));
		jTextStateList = new MyJTextField("Ingrese los estados separados por comas, ejemplo(q0,q1,q2) ");
		jTextStateList.addKeyListener(this);
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
		jPanelFuncionsTransitions = new JPanel(new GridLayout(1,1,5,5));
		
		btnAddNewFuncion = new JButton("Nueva Funcion");
		btnAddNewFuncion.setName("NewFuncion");
		btnAddNewFuncion.addMouseListener(this);
		btnAddNewFuncion.setEnabled(false);
		
		jPanelFuncions.add(btnAddNewFuncion,BorderLayout.LINE_END);
		jPanelFuncions.add(lbFuncionsTransitions,BorderLayout.PAGE_START);
		jPanelFuncions.add(jPanelFuncionsTransitions,BorderLayout.CENTER);
		JScrollPane paneFuncions = new 		JScrollPane(jPanelFuncions,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,jScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(paneFuncions);
		
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
				Character[] alphabet = getAlphabetList(jtextAlphabet.getText());
				State[] states = getStatesList(jTextStateList.getText());
//				jTextStateList.setFocusable(false);
				DefaultComboBoxModel<State> boxModel = new DefaultComboBoxModel<State>(states);
				jComboBoxInitialState.setEnabled(true);
				jComboBoxInitialState.setModel(boxModel);
				createCheckBoxAcceptable(states);
				createFuncionsTransition(alphabet,states);
				btnAddNewFuncion.setEnabled(true);
			break;
			}
		}
	}

	private void createFuncionsTransition(Character[] alphabet, State[] states) {
		indexFuncion++;
		JPanel panel = new JPanel(new GridLayout(1,7));
		panel.setName("Panel" + indexFuncion);
		JLabel lbF = new JLabel("F(",JLabel.CENTER);
		panel.add(lbF);
		DefaultComboBoxModel<State> stateModelFrom = new DefaultComboBoxModel<State>(states);
		JComboBox<State> from = new JComboBox<State>(stateModelFrom);
		from.setName("from");
		panel.add(from);
		JLabel lbcoma = new JLabel(" , ",JLabel.CENTER);
		panel.add(lbcoma);
		DefaultComboBoxModel<Character > alphabetModel = new DefaultComboBoxModel<Character>(alphabet);
		JComboBox<Character> character = new JComboBox<Character>(alphabetModel);
		character.setName("character");
		panel.add(character);
		JLabel lbFinFuncion = new JLabel(" ) =" ,JLabel.CENTER);
		panel.add(lbFinFuncion);
		DefaultComboBoxModel<State> stateModelTo = new DefaultComboBoxModel<State>(states);
		JComboBox<State> to = new JComboBox<State>(stateModelTo);
		to.setName("to");
		to.addMouseListener(this);
		panel.add(to);
		GridLayout gridLayout = (GridLayout) jPanelFuncionsTransitions.getLayout();
		gridLayout.setRows(gridLayout.getRows()+1);
		
		if (indexFuncion > 0) {
			JButton btnRemoveFuncion = new JButton("Eliminar");
			btnRemoveFuncion.setName("btn"+indexFuncion);
			btnRemoveFuncion.addMouseListener(this);
			panel.add(btnRemoveFuncion);
		}
		
		jPanelFuncionsTransitions.add(panel);
		jPanelFuncionsTransitions.repaint();
		jPanelFuncionsTransitions.revalidate();
	}

	private Character[] getAlphabetList(String text) {
		
		String[] alphabet = text.replaceAll(";","").split(",");
		Character[] alphabetList = new Character[alphabet.length];
		for (int i = 0; i < alphabet.length; i++) {
			alphabetList[i] = (Character) alphabet[i].charAt(0);
		}
		return alphabetList;
	}

	private void createCheckBoxAcceptable(State[] states) {
		panelCheckBox.removeAll();
		panelCheckBox.setLayout(new GridLayout(states.length/4,4));
		for (State state : states) {
			JCheckBox checkBox = new JCheckBox(state.toString());
			checkBox.setName(state.getName());
			checkBox.addMouseListener(this);
			panelCheckBox.add(checkBox);
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

	@Override
	public void mouseClicked(MouseEvent event) {
		if (event.getComponent().getName().contains("btn")) {
			removeFuncion(Integer.parseInt(event.getComponent().getName().replaceAll("btn", "")));
		}
		if (event.getComponent().getName().equals("NewFuncion")) {
			Character[] alphabet = getAlphabetList(jtextAlphabet.getText());
			State[] states = getStatesList(jTextStateList.getText());
			createFuncionsTransition(alphabet, states);
		}
		if (!getFinalStates().isEmpty()) {
			btnAccept.setEnabled(true);
		}else {
			btnAccept.setEnabled(false);
		}
		
	}

	private void removeFuncion(int parseInt) {
		Component[] components = jPanelFuncionsTransitions.getComponents();
		for (int i = 0; i < components.length; i++) {
			if (parseInt == Integer.parseInt(components[i].getName().replace("Panel", ""))) {
				jPanelFuncionsTransitions.remove(i);
				GridLayout gridLayout = (GridLayout) jPanelFuncionsTransitions.getLayout();
				gridLayout.setRows(gridLayout.getRows()-1);
				jPanelFuncionsTransitions.repaint();
			}
		}
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (!getFinalStates().isEmpty()) {
			btnAccept.setEnabled(true);
		}else {
			btnAccept.setEnabled(false);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<State> getFinalStates(){
		State[] states = getStatesList(jTextStateList.getText());
		ArrayList<State> statesAccepted = new ArrayList<State>();
		for (int i = 0; i < states.length; i++) {
			JCheckBox box = (JCheckBox) panelCheckBox.getComponents()[i];
			if (box.isSelected()) {
				states[i].setAccept(true);
				statesAccepted.add(states[i]);
			}
		}
		return statesAccepted;
	}
	
	
	private ArrayList<Transition> getTransitionList() {
		ArrayList<Transition> transitionList = new ArrayList<Transition>();
		Component[] components = jPanelFuncionsTransitions.getComponents();
		for (int i = 0; i < components.length; i++) {
			JPanel panel = (JPanel) components[i];
			getTransition(panel);
		}
		return transitionList;
	}

	private Transition getTransition(JPanel panel) {
		State fromState = new State(false, "")  ;
		Character characterTransition = ' ' ;
		State toState = new State(false, "")  ;
		Component[] components = panel.getComponents();
		
		for (int i = 0; i < components.length; i++) {
			if (components[i] != null && components[i].getName() != null) {
				if (components[i].getName().equalsIgnoreCase("from")) {
					JComboBox<State> from =  (JComboBox<State>) components[i];
					 fromState = (State) from.getSelectedItem();
				}
				if (components[i].getName().equalsIgnoreCase("character")) {
					JComboBox<String> character =  (JComboBox<String>) components[i];
					 characterTransition = (Character) character.getSelectedItem();
				}
				if (components[i].getName().equalsIgnoreCase("to")) {
					JComboBox<State> to =  (JComboBox<State>) components[i];
					toState = (State) to.getSelectedItem();
				}
			}
			
		}
	
		return new Transition(fromState, toState, characterTransition);
	}
	
	
	public Automaton getAutomaton() {
		ArrayList<State> states = new ArrayList<State>(Arrays.asList(getStatesList(jTextStateList.getText())));
		ArrayList<Character> alphabet = new ArrayList<Character>(Arrays.asList(getAlphabetList(jtextAlphabet.getText())));
		Automaton automaton = new Automaton(states, 
				(State) jComboBoxInitialState.getSelectedItem(),
				getFinalStates(),
				getTransitionList(),
				alphabet,
				new String[1][1]); 
		System.out.println(automaton.toString());
		return automaton;
	}

}