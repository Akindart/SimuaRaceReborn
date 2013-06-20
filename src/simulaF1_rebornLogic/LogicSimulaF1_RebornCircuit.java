package simulaF1_rebornLogic;

import java.util.HashMap;
import java.util.ArrayList;

/**
 * Classe que representa a pista no campeonato.
 * @version 2.0
 */
public class LogicSimulaF1_RebornCircuit {

	private String name;
	private String country;
	private int id;
	private int tempo;
	private ArrayList<LogicSimulaF1_RebornSection> sctions;
	private HashMap<LogicSimulaF1_RebornTeam, PitStop> pitStops;
	private int lenghtCircuit;
	private int laps;
	private LogicSimulaF1_RebornChampionship championship;
	private LogicSimulaF1_RebornRace race;	
	private int pitStopStarts;	
	private int pitStopEnds;
	private int sectionPitStopEnds;
	private int start;
	private BoundPilotRace pilotRace;
	
	/**
	 * Construtor da classe.
	 */
	public LogicSimulaF1_RebornCircuit() {
		super();
	}
	
	/**
	 * Inicia a corrida com esta pista.</br>
	 * Utiliza:</br>
	 * - {@link LogicSimulaF1_RebornCircuit#prepareCarsForRace}
	 */
	public void startRace() {
	
		
		this.pilotRace = new BoundPilotRace();
		this.pilotRace.setPilots(new HashMap<String, LogicSimulaF1_RebornPilot>());
		
		
		//adiciona clones das instancias dos pilotos a classe de ligação entre a thread da pista e a thread do piloto
		for(LogicSimulaF1_RebornTeam tempTeam : this.championship.getSquads().values()){
			tempTeam.setBounds(new HashMap<String, LogicSimulaF1_RebornBoundPilotEngineer>());
			for(LogicSimulaF1_RebornPilot tempPilot : tempTeam.getPilots().values()){
				
				tempTeam.getBounds().put(tempPilot.getName(), new LogicSimulaF1_RebornBoundPilotEngineer());
				tempTeam.getBounds().get(tempPilot.getName()).setStrategy(0);
				
				this.prepareCarsForRace(tempPilot);
				
				try {
					this.pilotRace.getPilots().put(tempPilot.getName(), (LogicSimulaF1_RebornPilot)tempPilot.clone());
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				//System.out.println(this.pilotRace.getPilots().get(tempPilot.getName()).toString());
				
			}
		
		}
		
		//System.out.println(this.toString());
				
		//instancia a classe race
		this.race = new LogicSimulaF1_RebornRace(this.startGrid(), false, this);
		
		
		
		Thread raceThread = new Thread(this.race);
		
		raceThread.start();
	}
	
	/**
	 * Define o gride de largada da corrida que ocorrera nesta
	 * instância da Pista através de um metodo pseudo-randômico
	 * de seleção. 
	 * @return ArrayList de pilotos na ordem dogrid de largada
	 */	
	public ArrayList<String> startGrid(){
		
		ArrayList<String> positions = new ArrayList<>();
		double auxPositionStart = this.getStart();
		
		for(LogicSimulaF1_RebornPilot tempPilot : this.pilotRace.getPilots().values())
			positions.add(tempPilot.getName());
		
		for(int i=0; i<positions.size(); i++){
			
			String tempPilot = positions.remove(i);
			int rand = (int)(Math.random()*positions.size());
			System.out.println("rand=" + rand);
			this.championship.getPilotsHashMap().get(tempPilot).setCurrentSection(this.championship.getCurrentCircuit().getSctions().get(0));
			this.championship.getPilotsHashMap().get(tempPilot).setDistanceCurretLap(auxPositionStart);
			this.championship.getPilotsHashMap().get(tempPilot).setTotalDistance(auxPositionStart);
			this.championship.getPilotsHashMap().get(tempPilot).setCurrentLap(0);
			this.championship.getPilotsHashMap().get(tempPilot).setAllDone(false);
			auxPositionStart -= 1.5;
			positions.add(rand, tempPilot);
			
		}
		
		return positions;
		
	}
	
	/**
	 * Inicializa as informações padrões do carro: combustível e o estado dele.
	 * @param <code>pilot</code> - Piloto a qual as informações serão inicializadas.
	 */
	public void prepareCarsForRace(LogicSimulaF1_RebornPilot pilot){
		
		pilot.getCar().setFuel(250);
		pilot.getCar().setState(0);
		if(tempo == 0) pilot.getCar().setTier(new LogicSimulaF1_RebornTire(0, 100.0)); //pista seca
		else pilot.getCar().setTier(new LogicSimulaF1_RebornTire(1, 100.0)); //pista molhada
				
	}
	
	public synchronized String getName() {
		return name;
	}
	public synchronized void setName(String name) {
		this.name = name;
	}
	public synchronized String getCountry() {
		return country;
	}
	public synchronized void setCountry(String country) {
		this.country = country;
	}
	public synchronized int getId() {
		return id;
	}
	public synchronized void setId(int id) {
		this.id = id;
	}
	public synchronized int getTempo() {
		return tempo;
	}
	public synchronized void setTempo(int tempo) {
		this.tempo = tempo;
	}
	public synchronized ArrayList<LogicSimulaF1_RebornSection> getSctions() {
		return sctions;
	}
	public synchronized void setSctions(
			ArrayList<LogicSimulaF1_RebornSection> sctions) {
		this.sctions = sctions;
	}
	public synchronized HashMap<LogicSimulaF1_RebornTeam, PitStop> getPitStops() {
		return pitStops;
	}
	public synchronized void setPitStops(
			HashMap<LogicSimulaF1_RebornTeam, PitStop> pitStops) {
		this.pitStops = pitStops;
	}
	public synchronized int getLenghtCircuit() {
		return lenghtCircuit;
	}
	public synchronized void setLenghtCircuit(int lenghtCircuit) {
		this.lenghtCircuit = lenghtCircuit;
	}
	public synchronized int getLaps() {
		return laps;
	}
	public synchronized void setLaps(int laps) {
		this.laps = laps;
	}
	public synchronized LogicSimulaF1_RebornChampionship getChampionship() {
		return championship;
	}
	public synchronized void setChampionship(
			LogicSimulaF1_RebornChampionship championship) {
		this.championship = championship;
	}
	public synchronized LogicSimulaF1_RebornRace getRace() {
		return race;
	}
	public synchronized void setRace(LogicSimulaF1_RebornRace race) {
		this.race = race;
	}
	public synchronized int getPitStopStarts() {
		return pitStopStarts;
	}
	public synchronized void setPitStopStarts(int pitStopStarts) {
		this.pitStopStarts = pitStopStarts;
	}
	public synchronized int getPitStopEnds() {
		return pitStopEnds;
	}
	public synchronized void setPitStopEnds(int pitStopEnds) {
		this.pitStopEnds = pitStopEnds;
	}
	public synchronized int getStart() {
		return start;
	}
	public synchronized void setStart(int start) {
		this.start = start;
	}
	public synchronized BoundPilotRace getPilotRace() {
		return pilotRace;
	}
	public synchronized void setPilotRace(BoundPilotRace pilotRace) {
		this.pilotRace = pilotRace;
	}
		
	public synchronized int getSectionPitStopEnds() {
		return sectionPitStopEnds;
	}

	public synchronized void setSectionPitStopEnds(int sectionPitStopEnds) {
		this.sectionPitStopEnds = sectionPitStopEnds;
	}

	/**
	 * Imprime as seguintes informações da pista:</br>
	 * - Nome</br>
	 * - País</br>
	 * - Id</br>
	 * - Tempo</br>
	 * - Tamanho da pista</br>
	 * - Voltas</br>
	 * - Início Pitstop</br>
	 * - Fim Pitstop</br>
	 * - Largada<br>
	 * - Seções
	 */
	@Override
	public String toString() {
		
		String circuitString = new String();
		
		for(LogicSimulaF1_RebornSection tempSection : this.sctions){
			
			circuitString = circuitString.concat(tempSection.toString() + "\n");
			
		}		
		
		return "LogicSimulaF1_RebornCircuit [name=" + name + ", country="
				+ country + ", id=" + id + ", tempo=" + tempo
				+ ", lenghtCircuit=" + lenghtCircuit + ", laps="
				+ laps + ", pitStopStarts="
				+ pitStopStarts + ", pitStopEnds=" + pitStopEnds + ", start="
				+ start + ", " +"\n"
				+ circuitString				
				+ "]";
	}
	
	
	
	
	
}
