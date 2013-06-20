package simulaF1_rebornLogic;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe que representa a equipe.
 * @version 2.0
 */
public class LogicSimulaF1_RebornTeam {
	
	private LogicSimulaF1_RebornMechanic mechanic;
	
	private HashMap<String, LogicSimulaF1_RebornEngineer> engineers;
	
	private HashMap<String, LogicSimulaF1_RebornPilot> pilots;
	
	private HashMap<LogicSimulaF1_RebornPilot, LogicSimulaF1_RebornCar> cars;
	
	private String teamName;
	
	private int teamPoints;
	
	private PitStop pitStop;
	
	private LogicSimulaF1_RebornChampionship championship;
	
	private HashMap<String, LogicSimulaF1_RebornBoundPilotEngineer> bounds;
	
	/**
	 * Construtor da classe.
	 */
	public LogicSimulaF1_RebornTeam() {
		super();
	}

	public synchronized LogicSimulaF1_RebornMechanic getMechanic() {
		return mechanic;
	}

	public synchronized void setMechanic(LogicSimulaF1_RebornMechanic mechanic) {
		this.mechanic = mechanic;
	}

	public synchronized HashMap<String, LogicSimulaF1_RebornEngineer> getEngineers() {
		return engineers;
	}

	public synchronized void setEngineers(
			HashMap<String, LogicSimulaF1_RebornEngineer> engineers) {
		this.engineers = engineers;
	}

	public synchronized HashMap<String, LogicSimulaF1_RebornPilot> getPilots() {
		return pilots;
	}

	public synchronized void setPilots(
			HashMap<String, LogicSimulaF1_RebornPilot> pilots) {
		this.pilots = pilots;
	}

	public synchronized HashMap<LogicSimulaF1_RebornPilot, LogicSimulaF1_RebornCar> getCars() {
		return cars;
	}

	public synchronized void setCars(
			HashMap<LogicSimulaF1_RebornPilot, LogicSimulaF1_RebornCar> cars) {
		this.cars = cars;
	}

	public synchronized String getTeamName() {
		return teamName;
	}

	public synchronized void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public synchronized int getTeamPoints() {
		return teamPoints;
	}

	public synchronized void setTeamPoints(int teamPoints) {
		this.teamPoints = teamPoints;
	}

	public synchronized PitStop getPitStop() {
		return pitStop;
	}

	public synchronized void setPitStop(PitStop pitStop) {
		this.pitStop = pitStop;
	}

	public synchronized LogicSimulaF1_RebornChampionship getChampionship() {
		return championship;
	}

	public synchronized void setChampionship(
			LogicSimulaF1_RebornChampionship championship) {
		this.championship = championship;
	}
	
	public synchronized HashMap<String, LogicSimulaF1_RebornBoundPilotEngineer> getBounds() {
		return bounds;
	}

	public synchronized void setBounds(
			HashMap<String, LogicSimulaF1_RebornBoundPilotEngineer> bounds) {
		this.bounds = bounds;
	}

	/**
	 * Imprime as seguintes informações da Equipe:</br>
	 * - Pilotos</br>
	 * - Engenheiros</br>
	 */
	@Override
	public String toString() {
		
		String teamString = new String();
		
		for(LogicSimulaF1_RebornPilot tempPilot : this.pilots.values())
			teamString = teamString.concat(tempPilot.toString() + "\n");
		
		for(LogicSimulaF1_RebornEngineer tempEng : this.engineers.values())
			teamString = teamString.concat(tempEng.toString() + "\n");
		
		teamString = teamString.concat(this.mechanic.toString());
		
		return teamString;
		
	}
	
}
