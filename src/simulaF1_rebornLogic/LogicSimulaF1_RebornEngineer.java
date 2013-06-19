package simulaF1_rebornLogic;


public class LogicSimulaF1_RebornEngineer extends LogicSimulaF1_RebornPerson implements Runnable{
	
	private LogicSimulaF1_RebornTeam squad;
	
	private String pilotName;
	
	private boolean allDone;
	
	
	public LogicSimulaF1_RebornEngineer() {
		super();
	}


	public LogicSimulaF1_RebornEngineer(LogicSimulaF1_RebornTeam squad,
			String pilotName, int strategy, String name) {
		super(name);
		this.squad = squad;
		this.pilotName = pilotName;
		this.allDone = false;
		
	}


	@Override
	public void run() {
		
		while(!allDone){
			
			
			
		}
		
	}
	
	private void defineStrategy(){
		
		double fuelSpended = 250 - this.getSquad().getPilots().get(this.getPilotName()).getCar().getFuel();
		double fuelPerLap;
		double abrasion = 100 - this.getSquad().getPilots().get(this.getPilotName()).getCar().getTier().getAbrasion();
		double abrasionPerLap;
		
		int laps = this.getSquad().getChampionship().getCurrentCircuit().getLaps() -  this.getSquad().getPilots().get(this.getPilotName()).getCurrentLap();
		
		if(this.getSquad().getPilots().get(this.getPilotName()).getCurrentLap() != 0){
			fuelPerLap = fuelSpended/(this.getSquad().getPilots().get(this.getPilotName()).getCurrentLap()-1);
			abrasionPerLap = abrasion/(this.getSquad().getPilots().get(this.getPilotName()).getCurrentLap()-1);
		}	
		
		else{
			
			fuelPerLap = 0;
			abrasionPerLap = 0;
			
		}
		
		if((fuelSpended != 0) && (abrasion > 60) && (laps != 0)){
			
			if(fuelPerLap*laps > this.getSquad().getPilots().get(this.getPilotName()).getCar().getFuel() &&
					abrasionPerLap*laps > this.getSquad().getPilots().get(this.getPilotName()).getCar().getTier().getAbrasion()){
				
				this.getSquad().getBounds().get(this.getPilotName()).setStrategy(3);
				
			}
			
		}
		
		else if((fuelSpended != 0) && (laps != 0)){
			
			if(fuelPerLap*laps > this.getSquad().getPilots().get(this.getPilotName()).getCar().getFuel())
				this.getSquad().getBounds().get(this.getPilotName()).setStrategy(2);
				
		}
		
		else if( (abrasion > 60) && (laps != 0)){
			
			if(abrasionPerLap*laps > this.getSquad().getPilots().get(this.getPilotName()).getCar().getTier().getAbrasion())
				this.getSquad().getBounds().get(this.getPilotName()).setStrategy(1);
			
		}
		
		else this.getSquad().getBounds().get(this.getPilotName()).setStrategy(0);
		
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
	
	public synchronized boolean isAllDone() {
		return allDone;
	}


	public synchronized void setAllDone(boolean allDone) {
		this.allDone = allDone;
	}


	@Override
	public String toString() {
		return "LogicSimulaF1_RebornEngineer [name="+ super.getName() + ", pilotName="
				+ pilotName + "]";
	}
	
	
	
	
	
}
