package views;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JPanel;

import models.Automaton;
import models.State;
import models.Transition;

public class DrawingAutomaton extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public void createCodigoDrawing(Automaton automaton) {
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter("codigo.txt");
			pw = new PrintWriter(fichero);
			pw.println("digraph finite_state_machine {\r\n" + 
					"	rankdir=LR;\r\n" + 
					"	size=\"8,5\"\r\n" + 
					"	node [shape = doublecircle];");
			ArrayList<State> statesFinals = automaton.getFinalState();
			for (State state : statesFinals) {
				pw.print(" "+ state.getName()+" ");
			}
			pw.print(";");
			pw.println("node [shape = circle];");
			ArrayList<State> states = automaton.getStateList();
			for (State state : states) {
				pw.print(" "+ state.getName()+" ");
			}
			ArrayList<Transition> transitions = automaton.getTransitionlist();
			for (Transition transition : transitions) {
				pw.println("" + transition.getFrom().getName() +" -> " + transition.getTo().getName() +" [ label =  \""+transition.getCharacter()+ "\" ];");
			}
			pw.println("}");
		}catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	public void generar(String rutaImg){
		try {

			//path del dot.exe,por lo general es la misma, pero depende de donde hayas instalado el paquete de Graphviz

			String dotPath = "Graphviz2.26.3\\bin\\dot.exe";

			//path del archivo creado con el codigo del graphviz que queremos

			String fileInputPath = "codigo.txt";

			//path de salida del grafo, es decir el path de la imagen que vamos a crear con graphviz

			String fileOutputPath = rutaImg;

			//tipo de imagen de salida, en este caso es jpg

			String tParam = "-Tjpg";

			String tOParam = "-o";

			//concatenamos nuestras direcciones. Lo que hice es crear un vector, para poder editar las direcciones de entrada y salida, usando las variables antes inicializadas

			//recordemos el comando en la consola de windows: C:\Archivos de programa\Graphviz 2.21\bin\dot.exe -Tjpg grafo1.txt -o grafo1.jpg Esto es lo que concatenamos en el vector siguiente:

			String[] cmd = new String[5];
			cmd[0] = dotPath;
			cmd[1] = tParam;
			cmd[2] = fileInputPath;
			cmd[3] = tOParam;
			cmd[4] = fileOutputPath;

			//Invocamos nuestra clase 

			Runtime rt = Runtime.getRuntime();

			//Ahora ejecutamos como lo hacemos en consola

			rt.exec( cmd );

		} catch (Exception ex) {
			ex.printStackTrace();
		} 
	}
}
