package simulaF1_rebornLogic;

import java.util.HashMap;

public class BoundPilotRace {

	private HashMap<String, LogicSimulaF1_RebornPilot> pilots;

	public synchronized HashMap<String, LogicSimulaF1_RebornPilot> getPilots() {
		return pilots;
	}

	public synchronized void setPilots(
			HashMap<String, LogicSimulaF1_RebornPilot> pilots) {
		this.pilots = pilots;
	}

	
	
}
