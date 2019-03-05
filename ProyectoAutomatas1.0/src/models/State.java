package models;

public class State {
	
	private boolean isAccept;
	private String name;
	
	
	
	public State(boolean isAccept, String name) {
		super();
		this.isAccept = isAccept;
		this.name = name;
	}
	
	
	public boolean isAccept() {
		return isAccept;
	}
	public void setAccept(boolean isAccept) {
		this.isAccept = isAccept;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
