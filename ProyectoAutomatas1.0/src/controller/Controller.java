package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.JFrameMainWindow;

public class Controller implements ActionListener{

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
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		switch (ActionCommand.valueOf(actionEvent.getActionCommand())) {
		case SHOW_DIALOG_ADD_TRANSITION_FUNCION:
			showDialogAddTransitionFuncion();
			break;
		case SHOW_DIALOG_ADD_TRANSITION_TABLE:
			//No se hará esta funcion
			break;
		case SHOW_WINDOW_DRAWING:
			
			break;
		case ADD_AUTOMATON_BY_FUNTIONS_TRANSITIONS:
			addAutomatonByFuntionsTransitions();
			break;
		default:
			break;
		
		}
	}

	
	private void showDialogAddTransitionFuncion() {
		jFrameMainWindow.setVisibleJDialogDataAutomaton(true);
		jFrameMainWindow.setVisibleJDialogInitial(false);
	}

	private void addAutomatonByFuntionsTransitions() {
		jFrameMainWindow.setVisible(true);
		jFrameMainWindow.setVisibleJDialogDataAutomaton(false);
		jFrameMainWindow.getAutomaton();
	}

	public void initApp() {
		jFrameMainWindow.setVisibleJDialogInitial(true);
	}

	public JFrameMainWindow getjFrameMainWindow() {
		return jFrameMainWindow;
	}

	public void setjFrameMainWindow(JFrameMainWindow jFrameMainWindow) {
		this.jFrameMainWindow = jFrameMainWindow;
	}
	
}
