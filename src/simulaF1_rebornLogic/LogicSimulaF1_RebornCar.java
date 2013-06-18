package simulaF1_rebornLogic;

public class LogicSimulaF1_RebornCar {

	private LogicSimulaF1_RebornTire tier;

	private LogicSimulaF1_RebornPilot pilot;

	private LogicSimulaF1_RebornTeam squad;

	private int state;

	private double fuel;

	private double speed;
	
	private double maxSpeed;

	private double weight;	

	private int carNumber;
	
	private String carColor;

	public LogicSimulaF1_RebornCar(){
		
		this.maxSpeed = 86.33;
			
	}
	
	private void updateCarsAtributes(){
		
		this.decreaseFuel();
		this.tier.abrasionCalc(this.getSquad().getChampionship().getCurrentCircuit().getTempo());
		
	}	
	
	public void setSpeedWithInfluencyFromTier(double speed){
		
		this.setSpeed(speed);
		
		if(this.getSquad().getChampionship().getCurrentCircuit().getTempo() == 1){//pista com chuva
			
			if(this.getTier().getType() == 0)
				this.setSpeed(this.getSpeed()*0.8);
			
		}
		
		if(this.getSquad().getChampionship().getCurrentCircuit().getTempo() == 0){//pista seca
		
			if(this.getTier().getType() == 1)
				this.setSpeed(this.getSpeed()*1.05);
		
		}
		
		if(this.getTier().getAbrasion() > 40 || this.getTier().getAbrasion() < 60)
			this.setSpeed(this.getSpeed()*1.07);
		
		this.updateCarsAtributes();
		
	}
			
	private void decreaseFuel(){
		
		this.fuel = this.fuel - (0.00133*this.speed);
		
	}
	
	public synchronized LogicSimulaF1_RebornTire getTier() {
		return tier;
	}

	public synchronized void setTier(LogicSimulaF1_RebornTire pneu) {
		this.tier = pneu;
	}

	public synchronized LogicSimulaF1_RebornPilot getPilot() {
		return pilot;
	}

	public synchronized void setPilot(LogicSimulaF1_RebornPilot pilot) {
		this.pilot = pilot;
	}

	public synchronized LogicSimulaF1_RebornTeam getSquad() {
		return squad;
	}

	public synchronized void setSquad(LogicSimulaF1_RebornTeam squad) {
		this.squad = squad;
	}

	public synchronized int getState() {
		return state;
	}

	public synchronized void setState(int state) {
		this.state = state;
	}

	public synchronized double getFuel() {
		return fuel;
	}

	public synchronized void setFuel(double fuel) {
		this.fuel = fuel;
	}

	public synchronized double getSpeed() {
		return speed;
	}

	public synchronized void setSpeed(double speed) {
		this.speed = speed;
	}

	public synchronized double getMaxSpeed() {
		return maxSpeed;
	}

	public synchronized void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public synchronized double getWeight() {
		return weight;
	}

	public synchronized void setWeight(double weight) {
		this.weight = weight;
	}

	public synchronized int getCarNumber() {
		return carNumber;
	}

	public synchronized void setCarNumber(int carNumber) {
		this.carNumber = carNumber;
	}

	public synchronized String getCarColor() {
		return carColor;
	}

	public synchronized void setCarColor(String carColor) {
		this.carColor = carColor;
	}

	@Override
	public String toString() {
		return "LogicSimulaF1_RebornCar [tier=" + tier.toString() 
				+ ", state=" + state + ", fuel=" + fuel
				+ ", speed=" + speed + ", maxSpeed=" + maxSpeed + ", weight="
				+ weight + ", carNumber=" + carNumber + ", carColor="
				+ carColor + "]";
	}
	
	
	
	
	
}
