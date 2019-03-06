package views;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import models.Automaton;


public class JFrameMainWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private JDialogDataAutomaton jDialogDataAutomaton;
	private JDialogInitial jDialogInitial;
	
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
		
		
		add(jMenuBar);
//		jTreePerson = new JTreePerson();
//		jToolBarOptions = new JToolBarOptions();
//		this.add(jToolBarOptions, BorderLayout.NORTH);
//		this.add(jTreePerson,BorderLayout.CENTER);
		
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
}