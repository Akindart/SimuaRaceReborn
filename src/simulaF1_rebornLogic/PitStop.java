package simulaF1_rebornLogic;

/**
 * Classe que representa a pitstop. Pitstop é uma seção especial.
 * @version 2.0
 */
public class PitStop extends LogicSimulaF1_RebornSection{

	private LogicSimulaF1_RebornTeam team;
	
	private LogicSimulaF1_RebornPilot waitingPilot;

	/**
	 * Construtor da classe. 
	 */
	public PitStop() {
		super(22.3, 0, 80.0, 0.0, 80.0);
	}

	public synchronized LogicSimulaF1_RebornTeam getTeam() {
		return team;
	}

	public synchronized void setTeam(LogicSimulaF1_RebornTeam team) {
		this.team = team;
	}

	public synchronized LogicSimulaF1_RebornPilot getWaitingPilot() {
		return waitingPilot;
	}

	public synchronized void setWaitingPilot(LogicSimulaF1_RebornPilot waitingPilot) {
		this.waitingPilot = waitingPilot;
	}
	
}
