package simulaF1_rebornLogic;

/**
 * É uma classe criada toda vez que uma corrida é iniciada. Os pilotos 
 * são os fornecedores da informação e a engenheiro é a consumidora da 
 * informação. Como passar mensagens diretamente entre as threads
 * é arriscado, essa classe permite uma comunicação sem que maiores
 * problemas aconteçam.
 * 
 * @version 2.0 
 */
public class LogicSimulaF1_RebornBoundPilotEngineer {
	
	int strategy;

	public synchronized int getStrategy() {
		return strategy;
	}

	public synchronized void setStrategy(int strategy) {
		this.strategy = strategy;
	}
	
	

}
