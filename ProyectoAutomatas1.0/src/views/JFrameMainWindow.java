package views;

import java.awt.BorderLayout;

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

	
	public JFrameMainWindow() {
		this.setTitle("Automatas");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setSize(700, 500);
//		this.setIconImage(new ImageIcon("src/img/logo.png").getImage());
		this.setLocationRelativeTo(null);
		initComponents();
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
		
		
		panelAutomatonDraw = new JPanel(new BorderLayout());
		
		
		add(jMenuBar);

		this.add(panelAutomatonDraw);
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
}