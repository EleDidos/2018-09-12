package it.polito.tdp.poweroutages.model;

public class Arco {
	
	private Nerc n1;
	private Nerc n2;
	private int peso; //mesi in cui ci sono stati blackout in entrambi i vertici
	public Nerc getN1() {
		return n1;
	}
	public void setN1(Nerc n1) {
		this.n1 = n1;
	}
	public Nerc getN2() {
		return n2;
	}
	public void setN2(Nerc n2) {
		this.n2 = n2;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public Arco(Nerc n1, Nerc n2, int peso) {
		super();
		this.n1 = n1;
		this.n2 = n2;
		this.peso = peso;
	}
	public String toString() {
		return n1+"-"+n2+" ("+peso+")";
	}

}
