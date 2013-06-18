package simulaF1_rebornLogic;

public class LogicSimulaF1_RebornSection {

	private double maxSpeed;
	private int id;
	private double lenght;
	private LogicSimulaF1_RebornCircuit circuit;
	private double start;
	private double fim;
	
	
	
	public LogicSimulaF1_RebornSection(double maxSpeed, int id, double lenght,
			double start, double fim) {
		super();
		this.maxSpeed = maxSpeed;
		this.id = id;
		this.lenght = lenght;
		this.start = start;
		this.fim = fim;
	}
	
	public synchronized double getMaxSpeed() {
		return maxSpeed;
	}
	public synchronized void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	public synchronized int getId() {
		return id;
	}
	public synchronized void setId(int id) {
		this.id = id;
	}
	public synchronized double getLenght() {
		return lenght;
	}
	public synchronized void setLenght(double lenght) {
		this.lenght = lenght;
	}
	public synchronized LogicSimulaF1_RebornCircuit getCircuit() {
		return circuit;
	}
	public synchronized void setCircuit(LogicSimulaF1_RebornCircuit circuit) {
		this.circuit = circuit;
	}
	public synchronized double getStart() {
		return start;
	}
	public synchronized void setStart(double start) {
		this.start = start;
	}
	public synchronized double getFim() {
		return fim;
	}
	public synchronized void setFim(double fim) {
		this.fim = fim;
	}

	@Override
	public String toString() {
		return "LogicSimulaF1_RebornSection [maxSpeed=" + maxSpeed + ", id="
				+ id + ", lenght=" + lenght + ", start=" + start + ", fim="
				+ fim + "]";
	}
	
	
	
	
}
