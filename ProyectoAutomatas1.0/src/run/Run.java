package run;

import controller.Controller;
import views.JFrameMainWindow;

public class Run {

	public static void main(String[] args) {
		JFrameMainWindow jFrameMainWindow = new JFrameMainWindow();
		Controller controller = Controller.getInstance();
		controller.setjFrameMainWindow(jFrameMainWindow);
		controller.initApp();
	}

}
