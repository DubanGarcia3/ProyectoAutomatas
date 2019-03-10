package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import models.Automaton;
import models.State;
import persistence.Persistence;
import views.DrawingAutomaton;
import views.JFrameMainWindow;

public class Controller implements ActionListener{

	public static Controller controller = null;
	public JFrameMainWindow jFrameMainWindow;
	public DrawingAutomaton drawingAutomaton = new DrawingAutomaton();
	public Automaton automaton;
	private Persistence persistence;
	
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
		case SHOW_EVALUATE_WORD:
			showEvaluateWord();
			break;
		case CHARGE_STATES:
			jFrameMainWindow.chargerStates();
			break;
		case ADD_PANEL_NEW_FUNCION:
			jFrameMainWindow.addPanelNewFuncion();
			break;
		case REMOVE_FUNCION:
			break;
		case EXPORT_AUTOMATON_JSON:
			exportAutomaton();
			break;
		case EXIT:
			
			break;
		case EXPORT_AUTOMATON_IMG:
			exportImage();
			break;
		case IMPORT_AUTOMATON:
			importAutomaton();
			break;
		}
	}
	
	private void exportAutomaton() {
		JFileChooser guardar = new JFileChooser();
	    guardar.showSaveDialog(null);
	    guardar.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	    File archivo = guardar.getSelectedFile();
		persistence = new Persistence();
		try {
			persistence.writeJson(archivo.getPath(),automaton.getStateList(), 
					automaton.getInitialState(), 
					automaton.getFinalState(), 
					automaton.getTransitionlist(), 
					automaton.getAlphabet());
		} catch (IOException e) {
//			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(jFrameMainWindow, "Automata guardado satisfactoriamente");
		
	}

	private void importAutomaton() {
		 JFileChooser guardar = new JFileChooser();
		 	FileNameExtensionFilter filtroImagen=new FileNameExtensionFilter("json","JSON");
			guardar.setFileFilter(filtroImagen);
		    guardar.showSaveDialog(null);
		    guardar.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    persistence = new Persistence();
		File archivo = guardar.getSelectedFile();
		try {
			String path = archivo.getPath();
			ArrayList<State> states = persistence.loadStateList(path);
			automaton = new Automaton(states, 
					persistence.loadInitialState(path,states),
					persistence.loadFinalStateList(path,states),
					persistence.loadTransitionlist(path,states),
					persistence.loadAlphabet(path));
			updateAutomaton();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void exportImage() {
		 JFileChooser guardar = new JFileChooser();
		    guardar.showSaveDialog(null);
		    guardar.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		    File archivo = guardar.getSelectedFile();
		    drawingAutomaton.generar(archivo.getPath());
		    JOptionPane.showMessageDialog(jFrameMainWindow, "La imagen a sido exportada correctamente");
	}

	
	private void showEvaluateWord() {
		String word = JOptionPane.showInputDialog(jFrameMainWindow,
				"Ingresa la palabra que deseas validar");
		JOptionPane.showMessageDialog(jFrameMainWindow, automaton.evaluateWordVProIngViviana(word));
	}

	private void showDialogAddTransitionFuncion() {
		jFrameMainWindow.setVisibleJDialogDataAutomaton(true);
		jFrameMainWindow.setVisibleJDialogInitial(false);
	}

	private void addAutomatonByFuntionsTransitions() {
		automaton = jFrameMainWindow.getAutomaton();
		updateAutomaton();
	}

	private void updateAutomaton() {
		drawingAutomaton.createCodigoDrawing(automaton);
		drawingAutomaton.generar("grafo1.jpg");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jFrameMainWindow.setVisible(true);
		jFrameMainWindow.setAutomaton();
		jFrameMainWindow.update(automaton.generateTransitionsTable());
		jFrameMainWindow.setVisibleJDialogDataAutomaton(false);
		jFrameMainWindow.setVisibleJDialogInitial(false);
	}

	public void initApp() {
		jFrameMainWindow.getDialogSplash().setVisible(true);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		jFrameMainWindow.getDialogSplash().setVisible(false);
		jFrameMainWindow.setVisibleJDialogInitial(true);
		
	}

	public JFrameMainWindow getjFrameMainWindow() {
		return jFrameMainWindow;
	}

	public void setjFrameMainWindow(JFrameMainWindow jFrameMainWindow) {
		this.jFrameMainWindow = jFrameMainWindow;
	}
	
}