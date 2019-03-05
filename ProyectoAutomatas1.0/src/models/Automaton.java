package models;

import java.util.ArrayList;

public class Automaton {

	private ArrayList<State> stateList;
	private State initialState;
	private ArrayList<State>finalState;
	private ArrayList<Transition> transitionList;
	private ArrayList<Character> alphabet;
	String [][] transitionTable;
	
	
	
	public Automaton(ArrayList<State> stateList, State initialState, ArrayList<State> finalState,
			ArrayList<Transition> transitionlist, ArrayList<Character> alphabet,String[][] transitionTable) {
		this.stateList = stateList;
		this.initialState = initialState;
		this.finalState = finalState;
		this.transitionList = transitionlist;
		this.alphabet = alphabet;
		this.transitionTable= transitionTable;
	}

	//	private String[][] generateTransitionsTable(ArrayList<Character>alphabeth){
	//		
	//		for (Transition transition : transitionlist) {
	//			
	//		}
	//		
	//	}
	
	public void setHeadersTransitionTable(){
		int count = 1;
		for (State state : stateList) {
			transitionTable[count ][0] = state.getName();
			count ++;
		}
		count = 1;
		for (Character letter : alphabet) {
			transitionTable[0][count] = ""+letter;
			count ++;
		}
		for (Transition transition : transitionList) {
			System.out.println("char ="+transition.getCharacter()+" "+transition.getTo().getName());
			transitionTable[getIndexOfStateTable(transition.getFrom().getName())]
					[getIndexOfCharTable(transition.getCharacter().toString())] = transition.getTo().getName();
		}
		printMatriz(transitionTable);
	}
	
	public int getIndexOfStateTable(String nameState){
		for (int i = 1; i < transitionTable[0].length; i++) {
			if (transitionTable[i][0].equalsIgnoreCase(nameState)) {
				System.out.println(nameState);
				return i;
			}
		}
		return -1;
	}
	
	public int getIndexOfCharTable(String nameChar){
		System.out.println(nameChar);
		for (int i = 1; i < transitionTable.length; i++) {
			if (transitionTable[0][i].equalsIgnoreCase(nameChar)) {
				return i;
			}
		}
		return -1;
	}
	
	public void printMatriz(String[][] matrix){
		for (int i = 0; i < matrix.length; i++) {
			String aux = "";
			for (int j = 0; j < matrix[0].length; j++) {
				aux += matrix[i][j]+"	";
			}
			System.out.println(aux+"\n");
		}
	}
	
	public void fillMatrixZeros(String[][] matrix){
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				matrix[i][j]="0";
			}
		}
	}
	
	public ArrayList<State> getStateList() {
		return stateList;
	}
	
	public void setStateList(ArrayList<State> stateList) {
		this.stateList = stateList;
	}

	public State getInitialState() {
		return initialState;
	}

	public void setInitialState(State initialState) {
		this.initialState = initialState;
	}

	public ArrayList<State> getFinalState() {
		return finalState;
	}

	public void setFinalState(ArrayList<State> finalState) {
		this.finalState = finalState;
	}

	public ArrayList<Transition> getTransitionlist() {
		return transitionList;
	}

	public void setTransitionlist(ArrayList<Transition> transitionlist) {
		this.transitionList = transitionlist;
	}

	public ArrayList<Character> getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(ArrayList<Character> alphabet) {
		this.alphabet = alphabet;
	}

	public ArrayList<Transition> getTransitionList() {
		return transitionList;
	}

	public void setTransitionList(ArrayList<Transition> transitionList) {
		this.transitionList = transitionList;
	}

	public String[][] getTransitionTable() {
		return transitionTable;
	}

	public void setTransitionTable(String[][] transitionTable) {
		this.transitionTable = transitionTable;
	}

	
	
}
