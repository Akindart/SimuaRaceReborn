package simulaF1_rebornLogic;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe Campeonato. Genrecia todo o campeonato em simulação
 * @version 2.0
 */
public class LogicSimulaF1_RebornChampionship {

	private HashMap<String, LogicSimulaF1_RebornTeam> squads;
	private ArrayList<LogicSimulaF1_RebornCircuit> circuits;
	private LogicSimulaF1_RebornCircuit currentCircuit;
	
	/**
	 * Método Construtor da classe. Define um hash para armazenar as equipes
	 * e um arraylist para armazenar as pistas.
	 */
	public LogicSimulaF1_RebornChampionship() {
		super();
		
		this.squads = new HashMap<>();
		this.circuits = new ArrayList<>();
		
	}
	
	/**
	 * Inicia a próxima corrida do campeonato.
	 * @param <code>id</code> - Identificador da proxima pista a ser simulada
	 */
	public void startNextRace(int id){
		
		this.currentCircuit = this.getCircuits().get(id);
		this.currentCircuit.startRace();
		
	}

	public synchronized HashMap<String, LogicSimulaF1_RebornTeam> getSquads() {
		return squads;
	}
	
	public synchronized void setSquads(
			HashMap<String, LogicSimulaF1_RebornTeam> squads) {
		this.squads = squads;
	}
	
	public synchronized ArrayList<LogicSimulaF1_RebornCircuit> getCircuits() {
		return circuits;
	}
	
	public synchronized void setCircuits(
			ArrayList<LogicSimulaF1_RebornCircuit> circuits) {
		this.circuits = circuits;
	}
	
	public synchronized LogicSimulaF1_RebornCircuit getCurrentCircuit() {
		return currentCircuit;
	}
	
	public synchronized void setCurrentCircuit(
			LogicSimulaF1_RebornCircuit currentCircuit) {
		this.currentCircuit = currentCircuit;
	}
	
	/**
	 * Pela todos os pilotos de todas as equipes e coloca num arrylist para ser 
	 * mais fácil de manipular.
	 * @return ArrayList com todos os pilotos do campeonato.
	 */
	public ArrayList<LogicSimulaF1_RebornPilot> getPilotsArrayList(){
		
		ArrayList<LogicSimulaF1_RebornPilot> pilots = new ArrayList<>();
		
		for(LogicSimulaF1_RebornTeam tempTeam : this.squads.values())
			for(LogicSimulaF1_RebornPilot tempPilot : tempTeam.getPilots().values())
				pilots.add(tempPilot);
		
		return pilots;
		
	}
	
	/**
	 * Pela todos os pilotos de todas as equipes e coloca num Hashmap para ser manipulado.
	 * @return Hashmap com todos os pilotos do campeonato.
	 */
	public HashMap<String, LogicSimulaF1_RebornPilot> getPilotsHashMap(){
		
		HashMap<String, LogicSimulaF1_RebornPilot> pilots = new HashMap<>();
		
		for(LogicSimulaF1_RebornTeam tempTeam : this.squads.values())
			for(LogicSimulaF1_RebornPilot tempPilot : tempTeam.getPilots().values())
				pilots.put(tempPilot.getName(), tempPilot);
		
		return pilots;
		
	}
	
	/**
	 * Imprime as seguintes informações de campeonato:</br>
	 * - Esquipes</br>
	 * - Pistas</br>
	 */
	@Override
	public String toString() {
	
		String championshipString = new String();
		
		for(LogicSimulaF1_RebornTeam tempTeam : this.squads.values()){
			
			championshipString = championshipString.concat(tempTeam.toString() + "\n");
			
		}
		
		
		for(LogicSimulaF1_RebornCircuit tempCircuit : this.circuits){
			
			championshipString = championshipString.concat(tempCircuit.toString() + "\n");
			
		}
		return championshipString;
	
	}
	
	
	
		
}
