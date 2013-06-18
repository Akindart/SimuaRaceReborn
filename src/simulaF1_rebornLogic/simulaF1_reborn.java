package simulaF1_rebornLogic;

public class simulaF1_reborn {

	public static void main(String args[]){
		
		LogicSimulaF1_RebornChampionship championship = new LogicSimulaF1_RebornChampionship();
		
		XMLparser.leArquivoCampeonato("simulaF1_minimalTest.xml", championship);
		XMLparser.leArquivoPista("pistasF1.xml", championship);
		
		championship.startNextRace(0);
		
		
		//System.out.println(championship.toString());
		
	}
	
}
