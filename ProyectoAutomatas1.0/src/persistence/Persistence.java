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
			finalStateListJSON.add(transitionJSON);
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

//	public void leerJson(String ruta) {
//		JsonParser parser = new JsonParser();
//		FileReader fr;
//		try {
//			fr = new FileReader(ruta);
//			JsonElement datos = parser.parse(fr);
//			dumpJSONElement(datos);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//	public static void dumpJSONElement(JsonElement elemento) {
//		if (elemento.isJsonObject()) {
//			System.out.println("Es objeto");
//			JsonObject obj = elemento.getAsJsonObject();
//			java.util.Set<java.util.Map.Entry<String,JsonElement>> entradas = obj.entrySet();
//			java.util.Iterator<java.util.Map.Entry<String,JsonElement>> iter = entradas.iterator();
//			while (iter.hasNext()) {
//				java.util.Map.Entry<String,JsonElement> entrada = iter.next();
//				System.out.println("Clave: " + entrada.getKey());
//				System.out.println("Valor:");
//				dumpJSONElement(entrada.getValue());
//			}
//
//		} else if (elemento.isJsonArray()) {
//			JsonArray array = elemento.getAsJsonArray();
//			System.out.println("Es array. Numero de elementos: " + array.size());
//			java.util.Iterator<JsonElement> iter = array.iterator();
//			while (iter.hasNext()) {
//				JsonElement entrada = iter.next();
//				dumpJSONElement(entrada);
//			}
//		} else if (elemento.isJsonPrimitive()) {
//			System.out.println("Es primitiva");
//			JsonPrimitive valor = elemento.getAsJsonPrimitive();
//			if (valor.isBoolean()) {
//				System.out.println("Es booleano: " + valor.getAsBoolean());
//			} else if (valor.isNumber()) {
//				System.out.println("Es numero: " + valor.getAsNumber());
//			} else if (valor.isString()) {
//				System.out.println("Es texto: " + valor.getAsString());
//			}
//		} else if (elemento.isJsonNull()) {
//			System.out.println("Es NULL");
//		} else {
//			System.out.println("Es otra cosa");
//		}
//	}
//
//
//	public void escribirJson(String ruta, Automaton automaton) {
//		Gson gson = new Gson();
//		String jsonString = gson.toJson(automaton);
//		System.out.println("JSON: " + jsonString);  
//		try {
//
//			FileWriter file = new FileWriter(ruta);
//			file.write(jsonString);
//			file.flush();
//			file.close();
//		} catch (IOException e) {
//			System.out.println(e.getMessage());
//		}
//	}
}