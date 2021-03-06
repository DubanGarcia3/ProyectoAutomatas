package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.ActionCommand;
import controller.Controller;
import models.Automaton;
import models.State;
import models.Transition;

public class JDialogDataAutomaton extends JDialog implements MouseListener{

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
	private JButton btnAddNewFuncion, jButtonContinue;
	
	private int indexFuncion = -1;

	private Character[] alphabet;

	private State[] states;
	
	//constructor
	public JDialogDataAutomaton(JFrameMainWindow frameMainWindow) {
		super(frameMainWindow);
		this.setLayout(new BorderLayout());
		this.setSize(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 - (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/11,
				(int) ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-Toolkit.getDefaultToolkit().getScreenSize().getHeight()/15)));
		this.setLocationRelativeTo(frameMainWindow);
		this.setResizable(true);
		this.setBackground(Color.WHITE);
		init();
	}

	private void init() {
		JPanel jPanelMain= new JPanel();
		jPanelMain.setBackground(Color.WHITE);
		jPanelMain.setLayout(new GridLayout(2,1));
		
		JPanel jPanelsup = new JPanel();
		jPanelsup.setBackground(Color.WHITE);
		jPanelsup.setLayout(new GridLayout(5,1));
		
		JPanel jPanelInf = new JPanel();
		jPanelInf.setLayout(new BorderLayout());
		jPanelInf.setBackground(Color.red);
		
		JPanel jPaneltitle = new JPanel();
		lbTitleDialog = new JLabel("Componentes del Automata:",JLabel.CENTER);
		lbTitleDialog.setFont(ConstansFont.fontTitle);
		jPaneltitle.add(lbTitleDialog);
		jPaneltitle.setBackground(Color.WHITE);
		this.add(jPaneltitle,BorderLayout.NORTH);
		
		JPanel panelAlphabet = new JPanel(new GridLayout(1,1));
		jtextAlphabet = new MyJTextField("Ingrese el Alfabeto separado por comas, ejemplo: a,b,c");
		jtextAlphabet.setFont(ConstansFont.fontTitle1);
		panelAlphabet.add(jtextAlphabet);
		panelAlphabet.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		panelAlphabet.setBackground(Color.WHITE);
		jPanelsup.add(panelAlphabet);
		
		JPanel panelStateList = new JPanel(new GridLayout(1,1));
		jTextStateList = new MyJTextField("Ingrese los estados separados por comas, ejemplo: q0,q1,q2;");
		
		jTextStateList.setFont(ConstansFont.fontTitle1);
		panelStateList.add(jTextStateList);
		panelStateList.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		panelStateList.setBackground(Color.WHITE);
		jPanelsup.add(panelStateList);
		
		JPanel jPanelConfirm = new JPanel();
		jButtonContinue = new JButton("Confirmar");
		jButtonContinue.setBackground(Color.decode("#E95420"));
		jButtonContinue.setForeground(Color.WHITE);
		jButtonContinue.addActionListener(Controller.getInstance());
		jButtonContinue.setActionCommand(ActionCommand.CHARGE_STATES.name());
		jButtonContinue.setPreferredSize(new Dimension(this.getWidth()/2, this.getHeight()/15));
		jButtonContinue.setFont(ConstansFont.fontTitle1);
		jPanelConfirm.setBackground(Color.WHITE);
		jPanelConfirm.add(jButtonContinue, BorderLayout.CENTER);
		jPanelsup.add(jPanelConfirm);
		
		JPanel panelInitialState = new JPanel(new GridLayout(1,2));
		JLabel lbInitialState = new JLabel("Selecciona el estado inicial");
		lbInitialState.setFont(ConstansFont.fontregular);
		jComboBoxInitialState = new JComboBox<State>();
		jComboBoxInitialState.setEnabled(false);
		jComboBoxInitialState.setFont(ConstansFont.fontregular);
		panelInitialState.add(lbInitialState);
		panelInitialState.add(jComboBoxInitialState);
		panelInitialState.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		panelInitialState.setBackground(Color.WHITE);
		jPanelsup.add(panelInitialState);
		
		JPanel panelStatesAcceptable = new JPanel(new BorderLayout()); 
		panelStatesAcceptable.setPreferredSize(new Dimension(0, 500));
		JLabel lbStateAcceptable = new JLabel("Selecciona el(los) estado(s) aceptable(s)");
		lbStateAcceptable.setFont(ConstansFont.fontregular);
		panelCheckBox = new JPanel(new GridLayout(1, 3));
		panelCheckBox.setFont(ConstansFont.fontregular);
		panelCheckBox.setBackground(Color.WHITE);
		panelStatesAcceptable.add(lbStateAcceptable,BorderLayout.PAGE_START);
		panelStatesAcceptable.setBackground(Color.WHITE);
		JScrollPane jScrollPane = new JScrollPane(panelCheckBox);
		jScrollPane.setBackground(Color.white);
		panelStatesAcceptable.add(jScrollPane,BorderLayout.CENTER);
		panelStatesAcceptable.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		jPanelsup.add(panelStatesAcceptable);
		
		JPanel jPanelFuncions = new JPanel(new BorderLayout());
		JLabel lbFuncionsTransitions = new JLabel("Ingresa las funciones de transicion");
		lbFuncionsTransitions.setFont(ConstansFont.fontregular);
		jPanelFuncionsTransitions = new JPanel(new GridLayout(1,1,5,5));
		
		btnAddNewFuncion = new JButton("Nueva Funcion");
		btnAddNewFuncion.setName("NewFuncion");
		btnAddNewFuncion.setFont(ConstansFont.fontregular);
		btnAddNewFuncion.addActionListener(Controller.getInstance());
		btnAddNewFuncion.setActionCommand(ActionCommand.ADD_PANEL_NEW_FUNCION.name());
		btnAddNewFuncion.addMouseListener(this);
		btnAddNewFuncion.setEnabled(false);
		btnAddNewFuncion.setFont(ConstansFont.fontregular);
		
		jPanelFuncions.add(btnAddNewFuncion,BorderLayout.LINE_END);
		jPanelFuncions.add(lbFuncionsTransitions,BorderLayout.PAGE_START);
		jPanelFuncions.add(jPanelFuncionsTransitions,BorderLayout.CENTER);
		jPanelFuncions.setBackground(Color.WHITE);
		JScrollPane paneFuncions = new JScrollPane(jPanelFuncions);
		paneFuncions.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		jPanelInf.add(paneFuncions);
		
		JPanel jPanelBtn = new JPanel(new BorderLayout());
		btnAccept = new JButton("Aceptar");
		btnAccept.setFont(ConstansFont.fontTitle1);
		btnAccept.addActionListener(Controller.getInstance());
		btnAccept.setEnabled(false);
		btnAccept.setActionCommand(ActionCommand.ADD_AUTOMATON_BY_FUNTIONS_TRANSITIONS.name());
		jPanelBtn.add(btnAccept);
		jPanelBtn.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		this.add(jPanelBtn,BorderLayout.SOUTH);
		
		jPanelMain.add(jPanelsup);
		jPanelMain.add(jPanelInf);
		this.add(jPanelMain,BorderLayout.CENTER);
	}

	public void createFuncionsTransition(Character[] alphabet, State[] states) {
		indexFuncion++;
		JPanel panel = new JPanel(new GridLayout(1,7));
		panel.setName("Panel" + indexFuncion);
		JLabel lbF = new JLabel("F(",JLabel.CENTER);
		lbF.setFont(ConstansFont.fontTitle1);
		panel.add(lbF);
		DefaultComboBoxModel<State> stateModelFrom = new DefaultComboBoxModel<State>(states);
		JComboBox<State> from = new JComboBox<State>(stateModelFrom);
		from.setName("from");
		from.setFont(ConstansFont.fontregular);
		from.setBackground(Color.decode("#CFB4C2"));
		panel.add(from);
		JLabel lbcoma = new JLabel(" , ",JLabel.CENTER);
		lbcoma.setFont(ConstansFont.fontTitle1);
		panel.add(lbcoma);
		DefaultComboBoxModel<Character > alphabetModel = new DefaultComboBoxModel<Character>(alphabet);
		JComboBox<Character> character = new JComboBox<Character>(alphabetModel);
		character.setFont(ConstansFont.fontregular);
		character.setBackground(Color.decode("#CFB4C2"));
		character.setName("character");
		panel.add(character);
		JLabel lbFinFuncion = new JLabel(" ) =" ,JLabel.CENTER);
		lbFinFuncion.setFont(ConstansFont.fontTitle1);
		panel.add(lbFinFuncion);
		DefaultComboBoxModel<State> stateModelTo = new DefaultComboBoxModel<State>(states);
		JComboBox<State> to = new JComboBox<State>(stateModelTo);
		to.setFont(ConstansFont.fontregular);
		to.setName("to");
		to.setBackground(Color.decode("#CFB4C2"));
		to.addMouseListener(this);
		panel.add(to);
		GridLayout gridLayout = (GridLayout) jPanelFuncionsTransitions.getLayout();
		gridLayout.setRows(gridLayout.getRows()+1);
		
		if (indexFuncion > 0) {
			JButton btnRemoveFuncion = new JButton("Eliminar");
			btnRemoveFuncion.setName("btn"+indexFuncion);
			btnRemoveFuncion.setFont(ConstansFont.fontregular);
			btnRemoveFuncion.addMouseListener(this);
			btnRemoveFuncion.setBackground(Color.decode("#84377D"));
			btnRemoveFuncion.setForeground(Color.WHITE);
			panel.add(btnRemoveFuncion);
		}
		
		jPanelFuncionsTransitions.add(panel);
		jPanelFuncionsTransitions.repaint();
		jPanelFuncionsTransitions.revalidate();
	}

	private Character[] getAlphabetList() {
		String[] alphabet = jtextAlphabet.getText().replaceAll(";","").split(",");
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
			checkBox.setFont(ConstansFont.fontregular);
			panelCheckBox.add(checkBox);
			repaint();
		}
	}

	private State[] getStatesList() {
		String[] statesList = jTextStateList.getText().replaceAll(";","").split(",");
		State[] states = new State[statesList.length];
		for (int i = 0; i < statesList.length; i++) {
			states[i] = new State(false,statesList[i]);
		}
		return states;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		if (event.getComponent().getName().contains("btn")) {
			removeFuncion(Integer.parseInt(event.getComponent().getName().replaceAll("btn", "")));
		}
		if (!getFinalStates().isEmpty()) {
			btnAccept.setEnabled(true);
			btnAccept.setBackground(Color.decode("#77216F"));
			btnAccept.setForeground(Color.WHITE);
			btnAddNewFuncion.setBackground(Color.decode("#E95420"));
			btnAddNewFuncion.setForeground(Color.WHITE);
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
		ArrayList<State> statesAccepted = new ArrayList<State>();
		for (int i = 0; i < states.length; i++) {
			JCheckBox box = (JCheckBox) panelCheckBox.getComponents()[i];
			if (box.isSelected()) {
				states[i].setAccept(true);
				statesAccepted.add(states[i]);
				btnAccept.setBackground(Color.decode("#77216F"));
			}
		}
		return statesAccepted;
	}
	
	
	private ArrayList<Transition> getTransitionList() {
		ArrayList<Transition> transitionList = new ArrayList<Transition>();
		Component[] components = jPanelFuncionsTransitions.getComponents();
		for (int i = 0; i < components.length; i++) {
			JPanel panel = (JPanel) components[i];
			transitionList.add(getTransition(panel));
		}
		return transitionList;
	}

	@SuppressWarnings("unchecked")
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
		ArrayList<State> statesList = new ArrayList<State>(Arrays.asList(this.states));
		ArrayList<Character> alphabetList = new ArrayList<Character>(Arrays.asList(this.alphabet));
		Automaton automaton = new Automaton(statesList, 
				(State) jComboBoxInitialState.getSelectedItem(),
				getFinalStates(),
				getTransitionList(),
				alphabetList); 
		automaton.showMatrixTransitions();
		return automaton;
	}
	
	public void clearFuncions() {
		jPanelFuncionsTransitions.removeAll();
	}

	public void chargerStates() {
		clearFuncions();
		alphabet = getAlphabetList();
		states = getStatesList();
		DefaultComboBoxModel<State> boxModel = new DefaultComboBoxModel<State>(states);
		jComboBoxInitialState.setEnabled(true);
		jComboBoxInitialState.setModel(boxModel);
		createCheckBoxAcceptable(states);
		createFuncionsTransition(alphabet,states);
		btnAddNewFuncion.setEnabled(true);
		btnAddNewFuncion.setBackground(Color.decode("#E95420"));
		btnAddNewFuncion.setForeground(Color.WHITE);
	}

	public void addPanelNewFuncion() {
		createFuncionsTransition(alphabet, states);
	}
	
	public void clear() {
		clearFuncions();
		alphabet = new Character[0];
		states = new State[0];
		jtextAlphabet.setText("");
		jtextAlphabet.setEditable(true);
		jTextStateList.setText("");
		jTextStateList.setEditable(true);
		DefaultComboBoxModel<State> boxModel = new DefaultComboBoxModel<State>();
		jComboBoxInitialState.setModel(boxModel);
		jComboBoxInitialState.setEnabled(false);
		panelCheckBox.removeAll();
		btnAddNewFuncion.setEnabled(false);
		btnAddNewFuncion.setBackground(Color.gray);
		btnAccept.setEnabled(false);
		
	}
	
}