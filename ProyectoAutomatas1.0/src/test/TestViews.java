package test;


import views.JFrameMainWindow;

public class TestViews {
	
	public static void main(String[] args) {
		JFrameMainWindow frameMainWindow = new JFrameMainWindow();
		frameMainWindow.setVisible(true);
//		DrawingAutomaton drawing = new DrawingAutomaton();
//		JDialogInitial dialogInitial = new JDialogInitial(frameMainWindow);
//		dialogInitial.setVisible(true);
		
//		JDialogDataAutomaton dataAutomaton = new JDialogDataAutomaton(frameMainWindow);
//		dataAutomaton.setVisible(true);
///	dataAutomaton.getAutomaton();
//		State state0 = new State(false, "q0");
//		State state1 = new State(false, "q1");
//		State state2 = new State(false, "q2");
//		ArrayList<State> stateList = new ArrayList<>();
//		stateList.add(state0);
//		stateList.add(state1);
//		stateList.add(state2);
//		
//		ArrayList<State> stateFinalList = new ArrayList<>();
//		stateFinalList.add(state2);
//		
//		ArrayList<Character> alphabeth = new ArrayList<>(); 
//		alphabeth.add('a');
//		alphabeth.add('b');
//		Transition transition0 = new Transition(state0,state0,'a');
//		Transition transition1 = new Transition(state0,state1,'b');
//		Transition transition2 = new Transition(state1,state0,'a');
//		Transition transition3 = new Transition(state1,state2,'b');
//		
//		ArrayList<Transition> transitionlist = new ArrayList<>();
//		transitionlist.add(transition0);
//		transitionlist.add(transition1);
//		transitionlist.add(transition2);
//		transitionlist.add(transition3);
//		
//		String[][] transitionTable = new String[stateList.size()+1][alphabeth.size()+1];
//		
//		Automaton automaton = new Automaton(stateList, state0,stateFinalList 
//				, transitionlist, alphabeth,transitionTable);
//		drawing.createCodigoDrawing(automaton);
//		drawing.generar();
	}

}