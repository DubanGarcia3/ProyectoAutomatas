package models;

import java.util.ArrayList;

public class Automaton {

	private ArrayList<State> stateList;
	private State initialState;
	private ArrayList<State> finalStateList;
	private ArrayList<Transition> transitionList;
	private ArrayList<Character> alphabet;
	String [][] transitionTable;

	public Automaton(ArrayList<State> stateList, State initialState, ArrayList<State> finalState,
			ArrayList<Transition> transitionlist, ArrayList<Character> alphabet,String[][] transitionTable) {
		this.stateList = stateList;
		this.initialState = initialState;
		this.finalStateList = finalState;
		this.transitionList = transitionlist;
		this.alphabet = alphabet;
		this.transitionTable= transitionTable;
	}

	public Automaton(ArrayList<State> stateList, State initialState, ArrayList<State> finalState,
			ArrayList<Transition> transitionList, ArrayList<Character> alphabet) {
		this.stateList = stateList;
		this.initialState = initialState;
		this.finalStateList = finalState;
		this.transitionList = transitionList;
		this.alphabet = alphabet;
		this.transitionTable= generateTransitionsTable();
	}
	
	public void loadAutomatonFromJSON(ArrayList<State> stateList, State initialState, ArrayList<State> finalState,
			ArrayList<Transition> transitionList, ArrayList<Character> alphabet) {
		this.stateList = stateList;
		this.initialState = initialState;
		this.finalStateList = finalState;
		this.transitionList = transitionList;
		this.alphabet = alphabet;
		this.transitionTable= new String[stateList.size()][alphabet.size()];
	}
	
	public String[][] generateTransitionsTable(){
		
		String [][] transitionTable = new String[stateList.size()+1][alphabet.size()+1];
		int indexState = 0;
		int indexSymbol = 0;
		
		addHeadersTransitionTable(transitionTable);
		for (int i = 0; i < transitionList.size(); i++) {
			indexState = stateList.indexOf(transitionList.get(i).getFrom())+1;
			indexSymbol = alphabet.indexOf(transitionList.get(i).getCharacter())+1;
			transitionTable[indexState][indexSymbol] =  transitionList.get(i).getTo().getName();
//			System.out.println();
		}
		return transitionTable;
	}

	private String[][]  addHeadersTransitionTable(String[][] transitionTable) {
		for (int i = 1, j=0; i < stateList.size()+1 ; i++,j++) {
			String name ="";
			if (initialState.equals(stateList.get(j))) {
				name  += "->";	
			}if(finalStateList.contains(stateList.get(j))) {
				name  += "*";
			}
			name += stateList.get(j).getName(); 
			transitionTable[i][0] = name;	
//			System.out.println("Estados->"+name);
		}
		
		for (int i = 1, j=0; i < alphabet.size()+1 ; i++,j++) {
			transitionTable[0][i] = ""+alphabet.get(j);
//			System.out.println("Caracter->"+alphabet.get(j));
		}
		return transitionTable;
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
		return finalStateList;
	}

	public void setFinalState(ArrayList<State> finalState) {
		this.finalStateList = finalState;
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

	public void showMatrixTransitions() {
		String[][]  strings = generateTransitionsTable();
		for (int i = 0; i < strings.length; i++) {
			for (int j = 0; j < strings[i].length; j++) {
				System.out.print(strings[i][j]+" , ");
			}
			System.out.println("");
		}
	}

	public boolean validateword(String word){
		for (int i = 0; i < word.length(); i++) {
			if (!alphabet.contains(word.charAt(i))) {
				return false;
			}
		}
		return true;
	}


	public String evaluateWordVProIngViviana(String word) {
		State auxState = initialState;
		for (int i = 0; i < word.length(); i++) {
			char letter = word.charAt(i);
			System.out.println(auxState.getName());
			System.out.println(letter);
			if (searchTransition(auxState, letter) == null) {
				return "La palabra no pertenece al lenguaje del autómata";
			}
			auxState = searchTransition(auxState, letter);
		}
		return finalStateList.contains(auxState) ? "Existe: " + "Sigma(" + initialState.getName()
		+ ", " + word + ") = " + auxState.getName() : "El estado final no es aceptable";
	}

	public State searchTransition(State stateFrom, Character character){
		for (Transition transition : transitionList) {
			if (transition.getFrom().equals(stateFrom) && character == transition.getCharacter()) {
				return transition.getTo();
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Automaton [stateList=" + stateList + ", initialState=" + initialState + ", finalState=" + finalStateList
				+ ", alphabet=" + alphabet + "]";
	}	
}
