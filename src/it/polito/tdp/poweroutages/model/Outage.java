package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;

public class Outage {
	
	private Integer id;
	private LocalDateTime d1;
	private LocalDateTime d2;
	private Nerc nerc;
	
	public Outage(Integer id, LocalDateTime d1, LocalDateTime d2,Nerc n) {
		super();
		this.id = id;
		this.d1 = d1;
		this.d2 = d2;
		this.nerc=n;
	}
	
	
	public Nerc getNerc() {
		return nerc;
	}


	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDateTime getD1() {
		return d1;
	}
	public void setD1(LocalDateTime d1) {
		this.d1 = d1;
	}
	public LocalDateTime getD2() {
		return d2;
	}
	public void setD2(LocalDateTime d2) {
		this.d2 = d2;
	}
	

}
