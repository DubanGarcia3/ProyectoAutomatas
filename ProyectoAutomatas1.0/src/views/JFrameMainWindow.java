package views;

import javax.swing.JFrame;


public class JFrameMainWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	
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
//		jTreePerson = new JTreePerson();
//		jToolBarOptions = new JToolBarOptions();
//		this.add(jToolBarOptions, BorderLayout.NORTH);
//		this.add(jTreePerson,BorderLayout.CENTER);
	}

}