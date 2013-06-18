package simulaF1_rebornLogic;

public class LogicSimulaF1_RebornPerson implements Cloneable {

	private String name;
		
	public LogicSimulaF1_RebornPerson() {
		super();
	}

	public LogicSimulaF1_RebornPerson(String name) {
		super();
		this.name = name;
	}

	public synchronized String getName() {
		return name;
	}

	public synchronized void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
