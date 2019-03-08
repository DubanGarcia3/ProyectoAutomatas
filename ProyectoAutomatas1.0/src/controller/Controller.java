package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.Automaton;
import views.DrawingAutomaton;
import views.JFrameMainWindow;

public class Controller implements ActionListener{

	public static Controller controller = null;
	public JFrameMainWindow jFrameMainWindow;
	public DrawingAutomaton drawingAutomaton = new DrawingAutomaton();
	public Automaton automaton;
	
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
			//No se har� esta funcion
			break;
		case SHOW_WINDOW_DRAWING:
			
			break;
		case ADD_AUTOMATON_BY_FUNTIONS_TRANSITIONS:
			addAutomatonByFuntionsTransitions();
			break;
		case SHOW_EVALUATE_WORD:
			showEvaluateWord();
			break;
		}
	}

	
	private void showEvaluateWord() {
		
		
	}

	private void showDialogAddTransitionFuncion() {
		jFrameMainWindow.setVisibleJDialogDataAutomaton(true);
		jFrameMainWindow.setVisibleJDialogInitial(false);
	}

	private void addAutomatonByFuntionsTransitions() {
		automaton = jFrameMainWindow.getAutomaton();
		drawingAutomaton.createCodigoDrawing(automaton);
		drawingAutomaton.generar();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jFrameMainWindow.setVisible(true);
		jFrameMainWindow.setAutomaton();
//		jFrameMainWindow.update(automaton.generateTransitionsTable());
		jFrameMainWindow.setVisibleJDialogDataAutomaton(false);
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