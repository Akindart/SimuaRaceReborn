package simulaF1_rebornLogic;

public class LogicSimulaF1_RebornMechanic extends LogicSimulaF1_RebornPerson {
	
	private LogicSimulaF1_RebornTeam squad;
	
	public LogicSimulaF1_RebornMechanic() {
		super();
	}


	public LogicSimulaF1_RebornMechanic(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}


	public synchronized LogicSimulaF1_RebornTeam getSquad() {
		return squad;
	}


	public synchronized void setSquad(LogicSimulaF1_RebornTeam squad) {
		this.squad = squad;
	}


	@Override
	public String toString() {
		return "LogicSimulaF1_RebornMechanic [name="
				+ super.getName() + "]";
	}
	
	
	

	
	
	
	
}
