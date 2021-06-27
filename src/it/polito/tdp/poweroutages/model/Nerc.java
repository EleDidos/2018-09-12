package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

public class Nerc {
	private int id;
	private String value;
	private List <Nerc> aiutati;
	private boolean impegnato;
	private long bonus; //gg di aiuto dati agli altri

	public Nerc(int id, String value) {
		this.id = id;
		this.value = value;
		aiutati = new ArrayList <Nerc>();
		impegnato=false;
		bonus=0;
	}
	
	public void addGGServizi(Long gg) {
		bonus+=gg;
	}
	
	public Long getBonus() {
		return this.bonus;
	}

	public boolean isImpegnato() { //sta donando energia
		return impegnato;
	}



	public void setImpegnato(boolean impegnato) {
		this.impegnato = impegnato;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nerc other = (Nerc) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(value);
		return builder.toString();
	}
	
	public void addVicinoAiutato(Nerc n) {
		this.aiutati.add(n);
	}
	
	public List <Nerc> getAiutati(){
		return aiutati;
	}
}
