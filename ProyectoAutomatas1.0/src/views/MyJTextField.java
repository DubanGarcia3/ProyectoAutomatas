package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.FocusManager;
import javax.swing.JTextField;

public class MyJTextField extends JTextField{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String string;
	
	public MyJTextField(String string) {
		this.string = string;
	}

	@Override
	protected void paintComponent(java.awt.Graphics g) {
		super.paintComponent(g);
		if (getText().isEmpty() && !(FocusManager.getCurrentKeyboardFocusManager().getFocusOwner() == this)) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setColor(Color.decode("#AEA79F"));
			g2.setFont(new Font("Century Gothic", 1, 14));
			g2.drawString(string, 10, this.getHeight()/2 +5); // figure out x, y from font'sFontMetrics and size ofcomponent.
			g2.dispose();
		}
	}
}