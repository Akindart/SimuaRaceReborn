package simulaF1_rebornLogic;

public class LogicSimulaF1_RebornTire {

	private int type;
	
	private double abrasion;

	public LogicSimulaF1_RebornTire(int type, double abrasion) {
		super();
		this.type = type;
		this.abrasion = abrasion;
	}
	
	public void abrasionCalc(int tempo){
		
		double auxAbrasion;
		
		auxAbrasion = (9.100 + Math.random() * 0.200);
		
		if(tempo == 0){
			
			if(this.type == 1)
				auxAbrasion *= 1.2;
			else if(this.type == 0)
				auxAbrasion *= 1;
			
		}
		
		else if(tempo == 1){
			
			if(this.type == 1)
				auxAbrasion *= 1;
			else if(this.type == 0)
				auxAbrasion *= 0.8;
			
		} 
		
		this.setAbrasion(this.getAbrasion() - (auxAbrasion/10000));
		
	}
	
	public synchronized int getType() {
		return type;
	}

	public synchronized void setType(int type) {
		this.type = type;
	}

	public synchronized double getAbrasion() {
		return abrasion;
	}

	public synchronized void setAbrasion(double abrasion) {
		this.abrasion = abrasion;
	}

	@Override
	public String toString() {
		return "LogicSimulaF1_RebornTire [type=" + type + ", abrasion="
				+ abrasion + "]";
	}
	
}
