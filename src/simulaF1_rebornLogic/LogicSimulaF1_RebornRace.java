package simulaF1_rebornLogic;

import java.util.ArrayList;
import java.util.HashMap;

public class LogicSimulaF1_RebornRace implements Runnable {

	private HashMap<LogicSimulaF1_RebornPilot, Thread> threadsPilots;

	private ArrayList<String> position;

	private LogicSimulaF1_RebornCircuit circuit;

	private boolean endRace;



	public LogicSimulaF1_RebornRace(ArrayList<String> position, boolean endRace, LogicSimulaF1_RebornCircuit circuit) {
		super();
		this.position = position;
		this.endRace = endRace;
		this.circuit = circuit;

		//		for(String tempName : position)
		//			System.out.println(tempName);

	}



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
			System.out.println(this.position.toString());
			System.out.println("[" + this.getCircuit().getPilotRace().getPilots().get(this.position.get(0)).getTotalDistance() + ", " + 
					this.getCircuit().getPilotRace().getPilots().get(this.position.get(1)).getTotalDistance() + "]");
			this.verifyEndRace();

			



		}

		System.out.println("ACABEI PORRA!");

	}

	private void verifyRaceStatus(){



		for(LogicSimulaF1_RebornPilot tempPilot : this.getCircuit().getPilotRace().getPilots().values()){

			ArrayList<LogicSimulaF1_RebornPilot> pilotsInTheAccident = new ArrayList<>();
			
			pilotsInTheAccident.add(tempPilot);
			
			int position = 0;

			for(LogicSimulaF1_RebornPilot tempPilot2 : this.getCircuit().getPilotRace().getPilots().values()){
				
				this.verifyCrash(pilotsInTheAccident, tempPilot, tempPilot2);
				
				System.out.println(pilotsInTheAccident.toString());
				
				
				position += this.verifyPosition(tempPilot, tempPilot2);

			}
			
			if(pilotsInTheAccident.size() > 1)
				this.GodsFuckngWrathAlgorithm(pilotsInTheAccident);
			
			this.position.remove(tempPilot.getName());
			this.position.add(position, tempPilot.getName());
			
			
			

		}

	}

	private void GodsFuckngWrathAlgorithm(ArrayList<LogicSimulaF1_RebornPilot> pilotsInTheAccident){
		
		
		
		if(Math.random() < 0.0003){
			
			System.out.println("Fucking God's Wrath Algorithm");
			
			for(LogicSimulaF1_RebornPilot tempPilot : pilotsInTheAccident){
				
				try {
					this.threadsPilots.get(this.getCircuit().getChampionship().getPilotsHashMap().get(tempPilot.getName())).sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				this.getCircuit().getChampionship().getPilotsHashMap().get(tempPilot.getName()).setAllDone(true);
				//this.threadsPilots.remove(this.getCircuit().getChampionship().getPilotsHashMap().get(tempPilot.getName()));
				
			}
			
		}
		
	}
	
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

	private synchronized void verifyCrash(ArrayList<LogicSimulaF1_RebornPilot> pilots, LogicSimulaF1_RebornPilot pilot, LogicSimulaF1_RebornPilot pilot2){
		
		if(Math.abs(pilot.getTotalDistance() - pilot2.getTotalDistance()) <= 2){
			
			pilots.add(pilot2);
			
		}
		
	}
	
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
