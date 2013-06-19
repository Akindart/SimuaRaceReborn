package simulaF1_rebornLogic;



public class LogicSimulaF1_RebornPilot extends LogicSimulaF1_RebornPerson implements Runnable {

	private int points;

	private LogicSimulaF1_RebornTeam squad;

	private LogicSimulaF1_RebornEngineer engineer;

	private LogicSimulaF1_RebornCar car;

	private LogicSimulaF1_RebornSection currentSection;

	private String sigla;

	private String nacionality;

	private boolean allDone;

	private int strategy;

	private String engineerOrder;

	private double distanceCurretLap;

	private double totalDistance;

	private int currentLap;

	private LogicSimulaF1_RebornCircuit circuit;

	private int state;

	public LogicSimulaF1_RebornPilot(String name) {
		super(name);

	}

	public LogicSimulaF1_RebornPilot(){

		super();	

	}

	@Override
	public void run() {

		System.out.println(this.toString());

		while(!this.allDone){

			this.accelerate();

			this.setTotalDistance(this.getTotalDistance() + this.getCar().getSpeed());
			this.setDistanceCurretLap(this.getDistanceCurretLap() + this.getCar().getSpeed());

			//System.out.println(this.toString());
			//System.out.println("Pilot: " + this.getName() + " - Distancia Volta atual: " +this.getDistanceCurretLap());
			//System.out.println("Pilot: " + this.getName() + " - Velocidade: " + this.getCar().getSpeed());

			this.changeSection();
			this.countLap();

			this.setAllDone(this.getSquad().getChampionship().getCurrentCircuit().getPilotRace().getPilots().get(this.getName()).isAllDone());


			this.cloneInformationToThreadPool();

			//System.out.println(this.getName()+ ": " + this.getTotalDistance());
			//System.out.println(this.getName()+ ": " + this.getDistanceCurretLap());
			//System.out.println(this.getName()+ ": " + this.getCurrentLap());


			if(this.getCurrentLap() == this.getSquad().getChampionship().getCurrentCircuit().getLaps())
				this.setAllDone(true);


		}

		this.cloneInformationToThreadPool();

		System.out.println("Print do fim do piloto");
		System.out.println(this.toString());

	}

	private void accelerate(){

		if(this.getSquad().getBounds().get(this.getName()).getStrategy() == 0 )
			this.accelerateWhitoutStrategy();
		else this.accelerateWIthStrategy();

	}

	private void accelerateWIthStrategy(){

		if(this.getCurrentSection().getId() < this.getSquad().getChampionship().getCurrentCircuit().getSctions().size()
				&&	(this.getDistanceCurretLap() + this.getCar().getSpeed()*3 > 
				this.getSquad().getChampionship().getCurrentCircuit().getSctions().get(this.getCurrentSection().getId()).getStart() ||
				this.getDistanceCurretLap() + this.getCar().getSpeed()*8 > this.getSquad().getChampionship().getCurrentCircuit().getPitStopStarts())){

			if(Math.abs(this.getDistanceCurretLap() + this.getCar().getSpeed()*8 - this.getSquad().getChampionship().getCurrentCircuit().getPitStopStarts()) < 
					Math.abs(this.getDistanceCurretLap() + this.getCar().getSpeed()*3 -
							this.getSquad().getChampionship().getCurrentCircuit().getSctions().get(this.getCurrentSection().getId()).getStart())){

				this.threshold(this.getSquad().getChampionship().getCurrentCircuit().getPitStops().get(this.getSquad().getTeamName()).getMaxSpeed());

			}
			else {
				
				if(this.getCar().getMaxSpeed() < this.getSquad().getChampionship().getCurrentCircuit().getSctions().get(this.getCurrentSection().getId()).getMaxSpeed())
					this.threshold(this.getCar().getMaxSpeed());

				else this.threshold(this.getSquad().getChampionship().getCurrentCircuit().getSctions().get(this.getCurrentSection().getId()).getMaxSpeed());

			}
		}

		else if((this.getDistanceCurretLap() + this.getCar().getSpeed()*3 > 
		this.getSquad().getChampionship().getCurrentCircuit().getSctions().get(3).getFim() || 
		)&& 
		this.getCurrentSection().getId() == this.getSquad().getChampionship().getCurrentCircuit().getSctions().size()){

			if(this.getCar().getMaxSpeed() < this.getSquad().getChampionship().getCurrentCircuit().getSctions().get(0).getMaxSpeed())
				this.threshold(this.getCar().getMaxSpeed());

			else this.threshold(this.getSquad().getChampionship().getCurrentCircuit().getSctions().get(0).getMaxSpeed());

		}

		else if(this.getCar().getMaxSpeed() <= this.getCurrentSection().getMaxSpeed()) this.threshold(this.getCar().getMaxSpeed());

		else this.threshold(this.getCurrentSection().getMaxSpeed());

	}

	private void accelerateWhitoutStrategy(){

		if(this.getCurrentSection().getId() < this.getSquad().getChampionship().getCurrentCircuit().getSctions().size()
				&&	this.getDistanceCurretLap() + this.getCar().getSpeed()*3 > 
		this.getSquad().getChampionship().getCurrentCircuit().getSctions().get(this.getCurrentSection().getId()).getStart()){

			if(this.getCar().getMaxSpeed() < this.getSquad().getChampionship().getCurrentCircuit().getSctions().get(this.getCurrentSection().getId()).getMaxSpeed())
				this.threshold(this.getCar().getMaxSpeed());

			else this.threshold(this.getSquad().getChampionship().getCurrentCircuit().getSctions().get(this.getCurrentSection().getId()).getMaxSpeed());

		}

		else if(this.getDistanceCurretLap() + this.getCar().getSpeed()*3 > 
		this.getSquad().getChampionship().getCurrentCircuit().getSctions().get(0).getStart() && 
		this.getCurrentSection().getId() == this.getSquad().getChampionship().getCurrentCircuit().getSctions().size()){

			if(this.getCar().getMaxSpeed() < this.getSquad().getChampionship().getCurrentCircuit().getSctions().get(0).getMaxSpeed())
				this.threshold(this.getCar().getMaxSpeed());

			else this.threshold(this.getSquad().getChampionship().getCurrentCircuit().getSctions().get(0).getMaxSpeed());

		}

		else if(this.getCar().getMaxSpeed() <= this.getCurrentSection().getMaxSpeed()) this.threshold(this.getCar().getMaxSpeed());

		else this.threshold(this.getCurrentSection().getMaxSpeed());


	}

	private void threshold(double thresholdSpeed){

		if ((this.getCar().getSpeed()+ 10) <  thresholdSpeed)
			this.getCar().setSpeedWithInfluencyFromTier(this.getCar().getSpeed() + 10);

		else if ((this.getCar().getSpeed()+ 5) <  thresholdSpeed)
			this.getCar().setSpeedWithInfluencyFromTier(this.getCar().getSpeed() + 5);

		else if ((this.getCar().getSpeed()+ 1) <  thresholdSpeed)
			this.getCar().setSpeedWithInfluencyFromTier(this.getCar().getSpeed() + 1);	

		else if ((this.getCar().getSpeed()) <=  thresholdSpeed)
			return ;

		else if((this.getCar().getSpeed() + (-1)) <  thresholdSpeed)
			this.getCar().setSpeedWithInfluencyFromTier(this.getCar().getSpeed() + (-1));

		else if((this.getCar().getSpeed()+ (-5)) <  thresholdSpeed)
			this.getCar().setSpeedWithInfluencyFromTier(this.getCar().getSpeed() + (-5));

		else if((this.getCar().getSpeed()+ (-10)) <  thresholdSpeed)
			this.getCar().setSpeedWithInfluencyFromTier(this.getCar().getSpeed() + (-10));

	}

	private void changeSection(){

		if(this.getSquad().getBounds().get(this.getName()).getStrategy() != 0 && this.getDistanceCurretLap() > 
		this.getSquad().getChampionship().getCurrentCircuit().getPitStopStarts())
			this.setCurrentSection(this.getSquad().getChampionship().getCurrentCircuit().getPitStops().get(this.getSquad().getTeamName()));

		else if(this.getCurrentSection().equals(this.getSquad().getChampionship().getCurrentCircuit().getPitStops().get(this.getSquad().getTeamName())) && 
				this.getDistanceCurretLap() > this.getSquad().getChampionship().getCurrentCircuit().getPitStopEnds())
			this.setCurrentSection(this.getSquad().getChampionship().getCurrentCircuit().getSctions().get(this.getSquad().getChampionship().getCurrentCircuit().getSectionPitStopEnds()));

		else if(this.getCurrentSection().getId() < this.getSquad().getChampionship().getCurrentCircuit().getSctions().size() && 
				this.getDistanceCurretLap() > this.getSquad().getChampionship().getCurrentCircuit().getSctions().get(this.currentSection.getId()).getStart()){

			//System.out.println("entrei no primeiro if");
			this.setCurrentSection(this.getSquad().getChampionship().getCurrentCircuit().getSctions().get(this.currentSection.getId()));

		}
		else if(this.getDistanceCurretLap() >= this.getSquad().getChampionship().getCurrentCircuit().getLenghtCircuit()){

			//System.out.println("entrei no segundo if");
			this.setCurrentSection(this.getSquad().getChampionship().getCurrentCircuit().getSctions().get(0));

		}

	}

	private void cloneInformationToThreadPool(){

		try {
			this.getSquad().getChampionship().getCurrentCircuit().getPilotRace().getPilots().put(this.getName(), (LogicSimulaF1_RebornPilot) this.clone());
		} catch (CloneNotSupportedException e) {

			System.out.println("Erro");

			e.printStackTrace();
		}

	}

	private void countLap(){

		if(this.getDistanceCurretLap() >=  this.getSquad().getChampionship().getCurrentCircuit().getLenghtCircuit()){
			//System.out.println("LAPS: " + this.getName()+ ": " + this.getCurrentLap());
			currentLap++;

			if(this.getCurrentLap() > this.getSquad().getChampionship().getCurrentCircuit().getLaps()){

				this.setDistanceCurretLap(0.0);
				this.setCurrentLap(0);

			}
			else this.setDistanceCurretLap(this.getDistanceCurretLap() - this.getSquad().getChampionship().getCurrentCircuit().getLenghtCircuit());
		}

	}

	public synchronized int getPoints() {
		return points;
	}

	public synchronized void setPoints(int points) {
		this.points = points;
	}

	public synchronized LogicSimulaF1_RebornTeam getSquad() {
		return squad;
	}

	public synchronized void setSquad(LogicSimulaF1_RebornTeam squad) {
		this.squad = squad;
	}

	public synchronized LogicSimulaF1_RebornEngineer getEngineer() {
		return engineer;
	}

	public synchronized void setEngineer(LogicSimulaF1_RebornEngineer engineer) {
		this.engineer = engineer;
	}

	public synchronized LogicSimulaF1_RebornCar getCar() {
		return car;
	}

	public synchronized void setCar(LogicSimulaF1_RebornCar car) {
		this.car = car;
	}

	public synchronized LogicSimulaF1_RebornSection getCurrentSection() {
		return currentSection;
	}

	public synchronized void setCurrentSection(
			LogicSimulaF1_RebornSection currentSection) {
		this.currentSection = currentSection;
	}

	public synchronized String getSigla() {
		return sigla;
	}

	public synchronized void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public synchronized String getNacionality() {
		return nacionality;
	}

	public synchronized void setNacionality(String nacionality) {
		this.nacionality = nacionality;
	}

	public synchronized boolean isAllDone() {
		return allDone;
	}

	public synchronized void setAllDone(boolean allDone) {
		this.allDone = allDone;
	}

	public synchronized int getStrategy() {
		return strategy;
	}

	public synchronized void setStrategy(int strategy) {
		this.strategy = strategy;
	}

	public synchronized String getEngineerOrder() {
		return engineerOrder;
	}

	public synchronized void setEngineerOrder(String engineerOrder) {
		this.engineerOrder = engineerOrder;
	}

	public synchronized double getDistanceCurretLap() {
		return distanceCurretLap;
	}

	public synchronized void setDistanceCurretLap(double distanceCurretLap) {
		this.distanceCurretLap = distanceCurretLap;
	}

	public synchronized double getTotalDistance() {
		return totalDistance;
	}

	public synchronized void setTotalDistance(double totalDistance) {
		this.totalDistance = totalDistance;
	}

	public synchronized int getCurrentLap() {
		return currentLap;
	}

	public synchronized void setCurrentLap(int currentLap) {
		this.currentLap = currentLap;
	}

	public synchronized LogicSimulaF1_RebornCircuit getCircuit() {
		return circuit;
	}

	public synchronized void setCircuit(LogicSimulaF1_RebornCircuit circuit) {
		this.circuit = circuit;
	}

	public synchronized int getState() {
		return state;
	}

	public synchronized void setState(int state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "LogicSimulaF1_RebornPilot [Name="+ super.getName() +"\npoints=" + points + ", "
				+ "\ncar=" + car.toString()
				//+ ", \ncurrentSection=" 
				//+ currentSection.toString() 
				+ ", \nsigla=" + sigla
				+ ", \nnacionality=" + nacionality + ",\n allDone=" + allDone
				+ ",\n strategy=" + strategy + ",\n engineerOrder=" + engineerOrder
				+ ",\n distanceCurretLap=" + distanceCurretLap
				+ ",\n totalDistance=" + totalDistance + ",\n currentLap="
				+ currentLap 
				//+ ",\n circuit=" + circuit.toString() 
				+ ",\n state=" + state
				+ "\n this=" + this.hashCode()
				+"]";
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone()	;
	}






}
