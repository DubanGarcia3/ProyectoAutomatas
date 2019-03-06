package test;

import views.JDialogDataAutomaton;
import views.JDialogInitial;
import views.JFrameMainWindow;

public class TestViews {
	
	public static void main(String[] args) {
		JFrameMainWindow frameMainWindow = new JFrameMainWindow();
		
//		JDialogInitial dialogInitial = new JDialogInitial(frameMainWindow);
//		dialogInitial.setVisible(true);
		
		JDialogDataAutomaton dataAutomaton = new JDialogDataAutomaton(frameMainWindow);
		dataAutomaton.setVisible(true);
	}

}