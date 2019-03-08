package models;

public class Transition {
	
	private State from;
	private State to;
	private Character character;
	
	public Transition(State from, State to, Character character) {
		this.from = from;
		this.to = to;
		this.character = character;
	}

	public State getFrom() {
		return from;
	}

	public void setFrom(State from) {
		this.from = from;
	}

	public State getTo() {
		return to;
	}

	public void setTo(State to) {
		this.to = to;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	@Override
	public String toString() {
		return "Transition from=" + from + ", to=" + to + ", character=" + character;
	}
	
	
}
