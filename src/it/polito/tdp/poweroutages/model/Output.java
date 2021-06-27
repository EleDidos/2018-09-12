package it.polito.tdp.poweroutages.model;

public class Output {
	
	private Nerc n;
	private long bonus; //gg servizio
	public Nerc getN() {
		return n;
	}
	public void setN(Nerc n) {
		this.n = n;
	}
	public long getBonus() {
		return bonus;
	}
	public void setBonus(long bonus) {
		this.bonus = bonus;
	}
	public Output(Nerc n, long bonus) {
		super();
		this.n = n;
		this.bonus = bonus;
	}
	
	public String toString() {
		return n+" - "+bonus+" giorni";
	}

}
