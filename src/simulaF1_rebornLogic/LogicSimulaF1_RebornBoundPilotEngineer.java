package simulaF1_rebornLogic;

public class LogicSimulaF1_RebornBoundPilotEngineer {
	
	int strategy;

	public synchronized int getStrategy() {
		return strategy;
	}

	public synchronized void setStrategy(int strategy) {
		this.strategy = strategy;
	}
	
	

}
