package persistence;

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
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import models.Automaton;
import models.State;
import models.Transition;

public class Persistence {

//	public ArrayList<Recipe> readJson() throws IOException {
//		ArrayList<Recipe> recipes = new ArrayList<>();
//		BufferedReader bufferedReader = new BufferedReader(new FileReader("src/data/recipies.json"));
//		Gson gson = new Gson();
//		JsonArray recipesJsonObject = gson.fromJson(bufferedReader, JsonArray.class);
//		for (JsonElement recipeJson : recipesJsonObject) {
//			JsonObject recipe = recipeJson.getAsJsonObject();
//			Recipe createdRecipe = RecipesManager.createRecipe(recipe.get("name").getAsString(),
//					recipe.get("ingredients").getAsJsonArray().size(), 
//					recipe.get("steps").getAsJsonArray().size(), getTotalTime(recipe.get("timers").getAsJsonArray()), 
//					recipe.get("imageURL").getAsString(), getIngredientsList(recipe.get("ingredients").getAsJsonArray()));
//			recipes.add(createdRecipe);
//		}
//		return recipes;
//	}
	
	public void writeJson(ArrayList<State> stateList, State initialState, ArrayList<State> finalState,
		ArrayList<Transition> transitionlist, ArrayList<Character> alphabet) throws IOException {
		File folderData = new File(System.getProperty("user.dir") + "\\" + "data");
		File fileReport = new File(folderData.getAbsolutePath() + "\\" + "automaton.json");
		folderData.mkdirs();
		FileWriter file = new FileWriter(fileReport);
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
		initialStateJSON.addProperty("Estado inicial nombre", initialState.getName());
		automatonJSON.add("Estado inicial", initialStateJSON);
		
//		Agrega la lista de estados aceptables
		JsonArray finalStateListJSON = new JsonArray();
		for (State finalStateToAdd : finalState) {
			JsonObject finalStateJSON1 = new JsonObject();
			finalStateJSON1.addProperty("isAccept", finalStateToAdd.isAccept());
			finalStateJSON1.addProperty("Nombre del estado aceptable", finalStateToAdd.getName());	
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
		for (Character charToAdd : alphabet) {
			JsonObject letterJSON = new JsonObject();
			letterJSON.addProperty(charToAdd.toString(), charToAdd.toString());
			alphabetListJSON.add(letterJSON);
			automatonJSON.add("Alfabeto", alphabetListJSON);
		}
		file.write(automatonJSON.toString());
		file.flush();
		file.close();
	}
}