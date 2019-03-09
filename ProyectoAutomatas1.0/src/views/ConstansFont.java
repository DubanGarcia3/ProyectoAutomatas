package views;

import java.awt.Font;

public class ConstansFont {
	
	static Font fontregular = new Font("Century Gothic", 1, 14);
	static Font fontTitle = new Font("Century Gothic", 1, 20);
	static Font fontTitle1 = new Font("Century Gothic", 1, 18);
	
	public static Font getFontregular() {
		return fontregular;
	}
	public void setFontregular(Font fontregular) {
		this.fontregular = fontregular;
	}
	public static Font getFontTitle() {
		return fontTitle;
	}
	public void setFontTitle(Font fontTitle) {
		this.fontTitle = fontTitle;
	}
	
	

}
