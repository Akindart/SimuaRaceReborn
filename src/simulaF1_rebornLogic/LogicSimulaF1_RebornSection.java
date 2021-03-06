package simulaF1_rebornLogic;

/**
 * Classe que representa seção.
 * @version 2.0
 */
public class LogicSimulaF1_RebornSection {

	private double maxSpeed;
	private int id;
	private double lenght;
	private LogicSimulaF1_RebornCircuit circuit;
	private double start;
	private double fim;
		
	/**
	 * Contrutor da Classe.</br>
	 * @param <code>maxSpeed</code> - Velodidade máxima para a seção</br>
	 * @param <code>id</code> - Identificação da Seção</br>
	 * @param <code>lenght</code> - Tamanho do percurso</br>
	 * @param <code>start</code> - Início da Seção</br>
	 * @param <code>fim</code> - Fim da Seção</br>
	 */
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

	/**
	 * Imprime as seguintes informações de Seção:</br>
	 * - Velocidade Máxima</br>
	 * - Identificador</br>
	 * - Tamanho</br>
	 * - Início</br>
	 * - Fim</br>
	 */
	@Override
	public String toString() {
		return "LogicSimulaF1_RebornSection [maxSpeed=" + maxSpeed + ", id="
				+ id + ", lenght=" + lenght + ", start=" + start + ", fim="
				+ fim + "]";
	}
	
	
	
	
}
