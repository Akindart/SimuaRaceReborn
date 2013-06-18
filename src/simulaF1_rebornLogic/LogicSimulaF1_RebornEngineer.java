package simulaF1_rebornLogic;


public class LogicSimulaF1_RebornEngineer extends LogicSimulaF1_RebornPerson implements Runnable{
	
	private LogicSimulaF1_RebornTeam squad;
	
	private String pilotName;
	
	
	public LogicSimulaF1_RebornEngineer() {
		super();
	}


	public LogicSimulaF1_RebornEngineer(LogicSimulaF1_RebornTeam squad,
			String pilotName, int strategy, String name) {
		super(name);
		this.squad = squad;
		this.pilotName = pilotName;
		
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	
	


	public synchronized String getPilotName() {
		return pilotName;
	}


	public synchronized void setPilotName(String pilotName) {
		this.pilotName = pilotName;
	}


	public synchronized LogicSimulaF1_RebornTeam getSquad() {
		return squad;
	}



	public synchronized void setSquad(LogicSimulaF1_RebornTeam squad) {
		this.squad = squad;
	}



	@Override
	public String toString() {
		return "LogicSimulaF1_RebornEngineer [name="+ super.getName() + ", pilotName="
				+ pilotName + "]";
	}
	
	
	
	
	
}
