package simulaF1_rebornLogic;

/**
 * Classe que representa o Engenheiro. Estende a classe {@link LogicSimulaF1_RebornPerson}.
 * @version 2.0
 */
public class LogicSimulaF1_RebornEngineer extends LogicSimulaF1_RebornPerson implements Runnable{
	
	private LogicSimulaF1_RebornTeam squad;
	
	private String pilotName;
	
	private boolean allDone;
	
	/**
	 * Construtor da classe sem parâmetros.
	 */
	public LogicSimulaF1_RebornEngineer() {
		super();
	}

	/**
	 * Contrutor da classe com parâmetros
	 * @param <code>squad</code> - Equipe a qual pertence o engenheiro
	 * @param <code>pilotName</code> - Nome do piloto a qual pertence o engenheiro
	 * @param <code>strategy</code> - Estratégia definida para a corrida
	 * @param <code>name</code> - Nome do engenheiro passado para o construtor da classe. Veja {@link LogicSimulaF1_RebornPerson}
	 */
	public LogicSimulaF1_RebornEngineer(LogicSimulaF1_RebornTeam squad,
			String pilotName, int strategy, String name) {
		super(name);
		this.squad = squad;
		this.pilotName = pilotName;
		this.allDone = false;
		
	}

	/**
	 * Método implementado que está especificado na interface {@link Runnable}.
	 */
	@Override
	public void run() {
		
		while(!allDone){
			
			
			
		}
		
	}
	
	/**
	 * Define a estratégia que o respectivo piloto desse engenheiro seguirá durante a corruida.
	 */
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

	/**
	 * Imprime as seguintes informações de engenheiro:</br>
	 * - Nome do engenheiro</br>
	 * - Nome do piloto</br>
	 */
	@Override
	public String toString() {
		return "LogicSimulaF1_RebornEngineer [name="+ super.getName() + ", pilotName="
				+ pilotName + "]";
	}
	
}
