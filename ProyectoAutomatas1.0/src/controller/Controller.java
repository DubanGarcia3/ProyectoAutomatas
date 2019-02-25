package controller;

import views.JFrameMainWindow;

public class Controller {

	public static Controller controller = null;
	public JFrameMainWindow jFrameMainWindow;

	private Controller() {
	}
	
	public static Controller getInstance() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}
	
	public void initApp() {
		jFrameMainWindow.setVisible(true);
	}

	public JFrameMainWindow getjFrameMainWindow() {
		return jFrameMainWindow;
	}

	public void setjFrameMainWindow(JFrameMainWindow jFrameMainWindow) {
		this.jFrameMainWindow = jFrameMainWindow;
	}


	
}
