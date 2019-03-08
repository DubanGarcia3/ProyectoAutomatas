package views;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import controller.ActionCommand;
import controller.Controller;
import models.Automaton;


public class JFrameMainWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private JDialogDataAutomaton jDialogDataAutomaton;
	private JDialogInitial jDialogInitial;
	private JPanel panelAutomatonDraw ;
	private JPanelTable panelTable;

	
	public JFrameMainWindow() {
		this.setTitle("Automatas");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setSize(700, 500);
//		this.setIconImage(new ImageIcon("src/img/logo.png").getImage());
		this.setLocationRelativeTo(null);
		initComponents();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void initComponents() {
		JMenuBar jMenuBar = new JMenuBar();
		JToolBar jToolBar = new JToolBar();
		
		jToolBar.setFloatable(false);
		JButton btnAddNewAutomaton = new JButton("Nuevos Automata");
		btnAddNewAutomaton.addActionListener(Controller.getInstance());
		btnAddNewAutomaton.setActionCommand(ActionCommand.SHOW_DIALOG_ADD_TRANSITION_FUNCION.name());
		jToolBar.add(btnAddNewAutomaton);
		
		JButton btnEvaluateWord = new JButton("Validar Palabra");
		btnEvaluateWord.addActionListener(Controller.getInstance());
		btnEvaluateWord.setActionCommand(ActionCommand.SHOW_EVALUATE_WORD.name());
		jToolBar.add(btnEvaluateWord);
		
		this.add(jToolBar, BorderLayout.PAGE_START);
		
		JPanel panelCenter = new JPanel(new BorderLayout());
		
		panelTable = new JPanelTable();
		panelCenter.add(panelTable,BorderLayout.LINE_START);
		
		
		panelAutomatonDraw = new JPanel(new BorderLayout());
		panelAutomatonDraw.setBackground(Color.WHITE);
		panelCenter.add(panelAutomatonDraw,BorderLayout.CENTER);
		
		this.add(panelCenter,BorderLayout.CENTER);
		
		jDialogDataAutomaton = new JDialogDataAutomaton(this);
		jDialogInitial = new JDialogInitial(this);
	}
	
	public void setVisibleJDialogDataAutomaton(boolean b) {
		jDialogDataAutomaton.setVisible(b);
	}

	public void setVisibleJDialogInitial(boolean b) {
		jDialogInitial.setVisible(b);
	}
	
	public Automaton getAutomaton() {
		return jDialogDataAutomaton.getAutomaton();
	}
	
	public void setAutomaton() {
		JLabel label = new JLabel(new ImageIcon("grafo1.jpg"));
		panelAutomatonDraw.add(label);
		revalidate();
	}
	 
//	public void update(String[][] matriz) {
//		panelTable.updateTable(matriz);
//		repaint();
//	}
	
}