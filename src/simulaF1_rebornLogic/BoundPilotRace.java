package simulaF1_rebornLogic;

import java.util.HashMap;

/**
 * É uma classe criada toda vez que uma corrida é iniciada. Os pilotos 
 * são os fornecedores da informação e a corrida é a consumidora da 
 * informação. Como passar mensagens diretamente entre as threads
 * é arriscado, essa classe permite uma comunicação sem que maiores
 * problemas aconteçam.
 * 
 * @version 2.0
 */
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
