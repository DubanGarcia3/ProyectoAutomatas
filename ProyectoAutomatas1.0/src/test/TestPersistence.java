package test;

import java.util.ArrayList;

import models.Automaton;
import models.State;
import models.Transition;
import persistence.Persistence;

public class TestPersistence {
	
	public static void main(String[] args) {
		State state0 = new State(false, "q0");
		State state1 = new State(false, "q1");
		
		ArrayList<State> stateList = new ArrayList<>();
		stateList.add(state0);
		stateList.add(state1);
		
		ArrayList<Character> alphabeth = new ArrayList<>(); 
		alphabeth.add('a');
		alphabeth.add('b');
		
		Transition transition0 = new Transition(state0,state1,'a');
		Transition transition1 = new Transition(state1,state0,'b');
		
		ArrayList<Transition> transitionlist = new ArrayList<>();
		transitionlist.add(transition0);
		transitionlist.add(transition1);
		
		ArrayList<State> acceptList = new ArrayList<>();
		acceptList.add(state0);
		
		Automaton automaton = new Automaton(stateList, state0, acceptList, 
				transitionlist, alphabeth);
		Persistence persistence = new Persistence();
		persistence.escribirJson("C:\\Users\\Viviana\\Documents\\automataPrueba.xml", automaton);
		persistence.leerJson("C:\\Users\\Viviana\\Documents\\automataPrueba.xml");
	}

}
