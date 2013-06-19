package simulaF1_rebornLogic;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe que representa a corrida. Implementa Runnable pois ela será uma thread que
 * executará a parte da parte principal do programa.
 * @version 2.0
 */
public class LogicSimulaF1_RebornRace implements Runnable {

	private HashMap<LogicSimulaF1_RebornPilot, Thread> threadsPilots;

	private ArrayList<String> position;

	private LogicSimulaF1_RebornCircuit circuit;

	private boolean endRace;


	/**
	 * Construtor da Classe Corrida.</br>
	 * @param <code>position</code> - Possição atual dos pilotos</br>
	 * @param <code>endRace</code> - Variável booleana que fala o fim da corrida.</br>
	 * @param <code>circuit</code> - Pista atual que será executada.</br>
	 */
	public LogicSimulaF1_RebornRace(ArrayList<String> position, boolean endRace, LogicSimulaF1_RebornCircuit circuit) {
		super();
		this.position = position;
		this.endRace = endRace;
		this.circuit = circuit;

		//		for(String tempName : position)
		//			System.out.println(tempName);

	}


	/**
	 * Método run implementado da interface Runnable. Ele define o que a thread fará
	 * enquanto ela estiver sendo executada. Utiliza {@link LogicSimulaF1_RebornRace#verifyRaceStatus}
	 */
	@Override
	public void run() {

		System.out.println("Cheguei na Race finalmente");

		/*for(LogicSimulaF1_RebornPilot tempPilot : this.circuit.getPilotRace().getPilots().values())
			System.out.println(tempPilot.toString() + "\n");*/

		/*for(LogicSimulaF1_RebornPilot tempPilot : this.circuit.getChampionship().getPilotsArrayList())
			System.out.println(tempPilot.toString() + "\n");
		 */
		this.threadsPilots = new HashMap<>();

		for(LogicSimulaF1_RebornPilot tempPilot : this.getCircuit().getChampionship().getPilotsArrayList()){
			this.threadsPilots.put(tempPilot, new Thread(tempPilot));
			//System.out.println(tempPilot.toString());
		}

		for(Thread tempThread : this.threadsPilots.values())
			tempThread.start();

		while(!endRace){

			//for(LogicSimulaF1_RebornPilot tempPilot : this.getCircuit().getPilotRace().getPilots().values()){
			//System.out.println(tempPilot.toString());
			//}
			this.verifyRaceStatus();
			//System.out.println(this.position.toString());
			//System.out.println("[" + this.getCircuit().getPilotRace().getPilots().get(this.position.get(0)).getTotalDistance() + ", " + 
					//this.getCircuit().getPilotRace().getPilots().get(this.position.get(1)).getTotalDistance() + "]");
			this.verifyEndRace();

			



		}

		System.out.println("ACABEI PORRA!");

	}

	/**
	 * Verifica o estado dos pilotos durante a corrida para verificar
	 * a ocorrência de carros perto demais que poderão se envolver num
	 * acidente, derrapagem de veículos, ou seja, ações que podem desencadear
	 * outra ação. Utiliza os seguintes métodos:</br>
	 * {@link LogicSimulaF1_RebornRace#verifyCrash}</br>
	 * {@link LogicSimulaF1_RebornRace#verifyPosition}</br>
	 * {@link LogicSimulaF1_RebornRace#GodsFuckngWrathAlgorithm}</br>
	 */
	private void verifyRaceStatus(){



		for(LogicSimulaF1_RebornPilot tempPilot : this.getCircuit().getPilotRace().getPilots().values()){

			ArrayList<LogicSimulaF1_RebornPilot> pilotsInTheAccident = new ArrayList<>();
			
			pilotsInTheAccident.add(tempPilot);
			
			int position = 0;

			for(LogicSimulaF1_RebornPilot tempPilot2 : this.getCircuit().getPilotRace().getPilots().values()){
				
				this.verifyCrash(pilotsInTheAccident, tempPilot, tempPilot2);
				
				//System.out.println(pilotsInTheAccident.toString());
							
				position += this.verifyPosition(tempPilot, tempPilot2);

			}
			
			System.out.println("\n\n\n\n\n\n\n\n\n");
			
			System.out.println("Antes do verify skid: " + tempPilot.getCurrentSection());			
			
			System.out.println("\n\n\n\n\n\n\n\n\n");
			
			//this.verifySkid(tempPilot);
			
			
			if(pilotsInTheAccident.size() > 1)
				this.GodsFuckngWrathAlgorithm(pilotsInTheAccident);
			
			
			
			
			this.position.remove(tempPilot.getName());
			this.position.add(position, tempPilot.getName());
			
			
			

		}

	}

	/**
	 * Algorítmo Ira de Deus. Esse algoritmo verifica os possíveis pilotos que se
	 * envolverão em acidentes. Calcula uma probabiliade de um acidente acontecer.
	 * Nota-se que não necessariamente dois carros se envolverão em acidentes.  
	 * @param <code>pilotsInTheAccident</code> - Array de pilotos envolvidos no possível acidentes
	 */
	private void GodsFuckngWrathAlgorithm(ArrayList<LogicSimulaF1_RebornPilot> pilotsInTheAccident){
		
		
		
		if(Math.random() < 0.0003){
			
			System.out.println("Fucking God's Wrath Algorithm");
			
			for(LogicSimulaF1_RebornPilot tempPilot : pilotsInTheAccident){
						
				this.getCircuit().getPilotRace().getPilots().get(tempPilot.getName()).setAllDone(true);
						
				
			}
			
		}
		
	}
	
	/**
	 * Verifica, entre dois pilotos, qual está na frente.</br>
	 * @param <code>tempPilot</code> - Piloto um a ser comparado</br>
	 * @param <code>tempPilot2</code> - Piloto dois a ser comparado</br>
	 * @return Retorna 0 quando o tempPilot2 estiver na frente. Caso contrário retorna 1.
	 */
	private int verifyPosition(LogicSimulaF1_RebornPilot tempPilot, LogicSimulaF1_RebornPilot tempPilot2){

		if(!(tempPilot.getName().compareTo(tempPilot2.getName()) == 0)){
			//System.out.println("Aqui");
			
			if(tempPilot.getTotalDistance() < tempPilot2.getTotalDistance()){
				//System.out.println("TO AQUI");
				return 1;
			}
			else return 0;
		}
		else return 0;

	}

	private synchronized void verifySkid(LogicSimulaF1_RebornPilot pilot){
		
		//System.out.println("pilot.getCar().getSpeed()=" + pilot.getCar().getSpeed() );
		
		//System.out.println("\n\n\n\n\n\n\n\n");
		
		//System.out.println(pilot.getCurrentSection());
		
		//System.out.println("\n\n\n\n\n\n\n\n");
		
		//System.out.println("pilot.getCurrentSection().getMaxSpeed()=" + pilot.getCurrentSection().getMaxSpeed() );
		
		if(pilot.getCar().getSpeed() > (pilot.getCurrentSection().getMaxSpeed() + 5)){
			
			try {
				this.threadsPilots.get(this.getCircuit().getChampionship().getPilotsHashMap().get(pilot.getName())).sleep(300);
			} catch (InterruptedException e) {
				
				System.out.println("aqui");
				
				e.printStackTrace();
			}
			
			LogicSimulaF1_RebornSection section = this.getCircuit().getPilotRace().getPilots().get(pilot.getName()).getCurrentSection();
			
			this.getCircuit().getChampionship().getPilotsHashMap().get(pilot.getName()).setAllDone(true);
			
			this.getCircuit().getPilotRace().getPilots().get(pilot.getName()).setCurrentSection(section);
			
		}
		
		else if(pilot.getCar().getSpeed() > pilot.getCurrentSection().getMaxSpeed()){
			
			if(Math.random() < 0.02){
				
				try {
					this.threadsPilots.get(this.getCircuit().getChampionship().getPilotsHashMap().get(pilot.getName())).sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				LogicSimulaF1_RebornSection section = this.getCircuit().getPilotRace().getPilots().get(pilot.getName()).getCurrentSection();
				
				this.getCircuit().getChampionship().getPilotsHashMap().get(pilot.getName()).setAllDone(true);
				
				this.getCircuit().getPilotRace().getPilots().get(pilot.getName()).setCurrentSection(section);
				
			}
			
		}
		
	}
	
	/**
	 * Verifica possíveis pilotos que podem se envolver no acidente.</br>
	 * @param <code>pilots</code> - Array de pilotos onde os pilotos que soferão acidentes serão adicionados
	 * @param <code>pilot</code> - Possível piloto envolvidos em acidente
	 * @param <code>pilot2</code> - Possível piloto envolvidos em acidente
	 */
	private synchronized void verifyCrash(ArrayList<LogicSimulaF1_RebornPilot> pilots, LogicSimulaF1_RebornPilot pilot, LogicSimulaF1_RebornPilot pilot2){
		
		if(Math.abs(pilot.getTotalDistance() - pilot2.getTotalDistance()) <= 2){
			
			pilots.add(pilot2);
			
		}
		
	}
	
	/**
	 * Verifica o final da corrida.
	 */
	private void verifyEndRace(){

		for(Thread tempThread : this.threadsPilots.values())
			if(tempThread.isAlive()){
				this.endRace = false;
				break;
			}
			else this.endRace = true;

	}

	public synchronized HashMap<LogicSimulaF1_RebornPilot, Thread> getThreadsPilots() {
		return threadsPilots;
	}



	public synchronized void setThreadsPilots(
			HashMap<LogicSimulaF1_RebornPilot, Thread> threadsPilots) {
		this.threadsPilots = threadsPilots;
	}



	public synchronized LogicSimulaF1_RebornCircuit getCircuit() {
		return circuit;
	}



	public synchronized void setCircuit(LogicSimulaF1_RebornCircuit circuit) {
		this.circuit = circuit;
	}



	public synchronized boolean isEndRace() {
		return endRace;
	}


	public synchronized void setEndRace(boolean endRace) {
		this.endRace = endRace;
	}




}
