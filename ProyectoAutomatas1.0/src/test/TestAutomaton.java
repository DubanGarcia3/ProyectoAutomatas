package test;

import java.util.ArrayList;

import models.Automaton;
import models.State;
import models.Transition;

public class TestAutomaton {

	public static void main(String[] args) {
		State state0 = new State(false, "q0");
		State state1 = new State(false, "q1");
//		State state2 = new State(false, "q2");
//		
		ArrayList<State> stateList = new ArrayList<>();
		stateList.add(state0);
		stateList.add(state1);
//		stateList.add(state2);
//		
		ArrayList<Character> alphabeth = new ArrayList<>(); 
		alphabeth.add('a');
		alphabeth.add('b');
//		
		Transition transition0 = new Transition(state0,state1,'a');
		Transition transition1 = new Transition(state1,state0,'b');
//		Transition transition2 = new Transition(state1,state0,'a');
//		Transition transition3 = new Transition(state1,state2,'b');
//		Transition transition4 = new Transition(state2,state2,'a');
//		Transition transition5 = new Transition(state2,state1,'b');
//		
		ArrayList<Transition> transitionlist = new ArrayList<>();
		transitionlist.add(transition0);
		transitionlist.add(transition1);
//		transitionlist.add(transition2);
//		transitionlist.add(transition3);
//		transitionlist.add(transition4);
//		transitionlist.add(transition5);
//		
//		ArrayList<State> stateFinal = new ArrayList<>();
//		stateFinal.add(state1);
//	
//		Automaton automaton = new Automaton(stateList, state0, stateFinal, transitionlist, alphabeth);
////		automaton.fillMatrixZeros(transitionTable);
////		automaton.setHeadersTransitionTable();

		
//		State state0 = new State(true, "q0");
//		State state1 = new State(false, "q1");
//		State state2 = new State(false, "q2");
//		State state3 = new State(false, "q3");
//		ArrayList<State> stateList = new ArrayList<>();
//		stateList.add(state0);
//		stateList.add(state1);
//		stateList.add(state2);
//		stateList.add(state3);
//		ArrayList<Character> alphabeth = new ArrayList<>(); 
//		alphabeth.add('0');
//		alphabeth.add('1');
//		Transition transition0 = new Transition(state0,state1,'1');
//		Transition transition1 = new Transition(state0,state2,'0');
//		Transition transition2 = new Transition(state1,state0,'1');
//		Transition transition3 = new Transition(state1,state3,'0');
//		Transition transition4 = new Transition(state2,state0,'0');
//		Transition transition5 = new Transition(state2,state3,'1');
//		Transition transition6 = new Transition(state3,state1,'0');
//		Transition transition7 = new Transition(state3,state2,'1');
//		
		ArrayList<State> acceptList = new ArrayList<>();
		acceptList.add(state1);
//		ArrayList<Transition> transitionlist = new ArrayList<>();
//		transitionlist.add(transition7);
//		transitionlist.add(transition0);
//		transitionlist.add(transition1);
//		transitionlist.add(transition2);
//		transitionlist.add(transition3);
//		transitionlist.add(transition4);
//		transitionlist.add(transition5);
//		transitionlist.add(transition6);
//	
		Automaton automaton = new Automaton(stateList, state0, acceptList, 
				transitionlist, alphabeth);
		automaton.setInitialState(state0);
//		automaton.fillMatrixZeros(transitionTable);
//		automaton.setHeadersTransitionTable();
		
		automaton.generateTransitionsTable();
		automaton.showMatrixTransitions();
		System.out.println(automaton.evaluateWordVProIngViviana("a"));
	
	}

}
