package persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import models.State;
import models.Transition;

public class Persistence {


	//Leer la lista de estados y subirla
	public ArrayList<State> loadStateList(String path) throws IOException {
		ArrayList<State> stateList = new ArrayList<>();
		//		BufferedReader bufferedReader = new BufferedReader(new FileReader("data/automaton.json"));
		BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
		Gson gson = new Gson();
		JsonObject automatonJSON = gson.fromJson(bufferedReader, JsonObject.class);
		JsonArray stateListJSONArray = automatonJSON.get("Lista de estados").getAsJsonArray();
		for (JsonElement jsonObjectState : stateListJSONArray) {
			JsonObject stateJSON = jsonObjectState.getAsJsonObject();
			stateList.add(new State(stateJSON.get("isAccept").getAsBoolean(),
					stateJSON.get("Nombre del estado").getAsString()));
		}
		return stateList;
	}

	//	Leer el estado incial
	public State loadInitialState(String path, ArrayList<State> states) throws IOException{
		BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
		Gson gson = new Gson();
		JsonObject automatonJSON = gson.fromJson(bufferedReader, JsonObject.class);
		JsonObject initalStateJSON = automatonJSON.get("Estado inicial").getAsJsonObject();
		String name = initalStateJSON.get("Nombre del estado").getAsString();
		return searchState(states, name);
	}

	private State searchState(ArrayList<State> states, String name) {
		for (State state : states) {
			if (state.getName().equals(name)) {
				return state;
			}
		}
		return null;
	}

	//	Leer la lista de estados aceptables
	public ArrayList<State> loadFinalStateList(String path,ArrayList<State> states) throws FileNotFoundException {
		ArrayList<State> finalStateList = new ArrayList<>();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
		Gson gson = new Gson();
		JsonObject automatonJSON = gson.fromJson(bufferedReader, JsonObject.class);
		JsonArray finalStateListJSONArray = automatonJSON.get("Lista de estados aceptables").getAsJsonArray();
		for (JsonElement jsonObjectFinalState : finalStateListJSONArray) {
			JsonObject stateJSON = jsonObjectFinalState.getAsJsonObject();
			String name = stateJSON.get("Nombre del estado").getAsString();
			finalStateList.add(searchState(states, name));
		}
		return finalStateList;
	}


	//	Leer la lista de transiciones
	public ArrayList<Transition> loadTransitionlist(String path, ArrayList<State> states) throws FileNotFoundException {
		ArrayList<Transition> transitionList = new ArrayList<>();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
		Gson gson = new Gson();
		JsonObject automatonJSON = gson.fromJson(bufferedReader, JsonObject.class);
		JsonArray transitionListJSONArray = automatonJSON.get("Lista de transiciones").getAsJsonArray();
		for (JsonElement jsonObjectTransition : transitionListJSONArray) {
			JsonObject transitionJSON = jsonObjectTransition.getAsJsonObject();
			String nameStateFrom = transitionJSON.get("From").getAsJsonObject().get("Name").getAsString();

			String nameStateTo = transitionJSON.get("To").getAsJsonObject().get("Name").getAsString();
			Character letter = transitionJSON.get("Character").getAsCharacter();
			transitionList.add(new Transition(searchState(states, nameStateFrom), 
					searchState(states, nameStateTo), letter));
		}
		return transitionList;
	}

	//	Leer el alfabeto
	public ArrayList<Character> loadAlphabet(String path) throws FileNotFoundException {
		ArrayList<Character> alphabet = new ArrayList<>();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
		Gson gson = new Gson();
		JsonObject automatonJSON = gson.fromJson(bufferedReader, JsonObject.class);
		JsonArray alphabetJSONArray = automatonJSON.get("Alfabeto").getAsJsonArray();
		int count = 1;
		for (JsonElement jsonObjectAlphabet : alphabetJSONArray) {
			JsonObject alphabetJSON = jsonObjectAlphabet.getAsJsonObject();
			alphabet.add(alphabetJSON.get(count+"").getAsCharacter());
			count++;
		}
		return alphabet;
	}

	public void writeJson(String path,ArrayList<State> stateList, State initialState, ArrayList<State> finalState,
			ArrayList<Transition> transitionlist, ArrayList<Character> alphabet) throws IOException {
		File fileReport = new File(path);
		FileWriter file = new FileWriter(fileReport, false);
		JsonObject automatonJSON = new JsonObject();
		//Agrega la lista de estados
		JsonArray stateListJSON = new JsonArray();
		for (State stateToAdd : stateList) {
			JsonObject stateJSON = new JsonObject();
			stateJSON.addProperty("isAccept", stateToAdd.isAccept());
			stateJSON.addProperty("Nombre del estado", stateToAdd.getName());	
			stateListJSON.add(stateJSON);
		}
		automatonJSON.add("Lista de estados", stateListJSON);

		//		Agrega el estado inicial
		JsonObject initialStateJSON = new JsonObject(); 
		initialStateJSON.addProperty("isAccept", initialState.isAccept());
		initialStateJSON.addProperty("Nombre del estado", initialState.getName());
		automatonJSON.add("Estado inicial", initialStateJSON);

		//		Agrega la lista de estados aceptables
		JsonArray finalStateListJSON = new JsonArray();
		for (State finalStateToAdd : finalState) {
			JsonObject finalStateJSON1 = new JsonObject();
			finalStateJSON1.addProperty("isAccept", finalStateToAdd.isAccept());
			finalStateJSON1.addProperty("Nombre del estado", finalStateToAdd.getName());	
			finalStateListJSON.add(finalStateJSON1);
		}
		automatonJSON.add("Lista de estados aceptables", finalStateListJSON);

		//		Agrega la lista de transiciones
		JsonArray transitionlistJSON = new JsonArray();
		for (Transition transitionToAdd : transitionlist) {
			JsonObject transitionJSON = new JsonObject();
			JsonObject transitionStateFromJSON = new JsonObject();
			transitionStateFromJSON.addProperty("isAccept", transitionToAdd.getFrom().isAccept());
			transitionStateFromJSON.addProperty("Name", transitionToAdd.getFrom().getName());
			transitionJSON.add("From", transitionStateFromJSON);

			JsonObject transitionStateToJSON = new JsonObject();
			transitionStateToJSON.addProperty("isAccept", transitionToAdd.getTo().isAccept());
			transitionStateToJSON.addProperty("Name", transitionToAdd.getTo().getName());
			transitionJSON.add("To", transitionStateToJSON);	

			transitionJSON.addProperty("Character", transitionToAdd.getCharacter());	
			transitionlistJSON.add(transitionJSON);
		}
		automatonJSON.add("Lista de transiciones", transitionlistJSON);

		//		Agrega el alfabeto
		JsonArray alphabetListJSON = new JsonArray();
		int count = 1;
		for (Character charToAdd : alphabet) {
			JsonObject letterJSON = new JsonObject();
			letterJSON.addProperty(count+"", charToAdd.toString());
			alphabetListJSON.add(letterJSON);
			automatonJSON.add("Alfabeto", alphabetListJSON);
			count++;
		}
		file.write(automatonJSON.toString());
		file.flush();
		file.close();
	}
}