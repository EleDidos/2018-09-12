package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;

public class Event implements Comparable<Event>{

	public enum EventType{
		INIZIO,
		FINE
	}
	private LocalDateTime time;
	private EventType type;
	private Nerc n;
	private Nerc donatore;
	private Outage o;
	
	public Outage getO() {
		return o;
	}

	public void setO(Outage o) {
		this.o = o;
	}

	public Nerc getDonatore() {
		return donatore;
	}

	public void setDonatore(Nerc donatore) {
		this.donatore = donatore;
	}

	@Override
	public int compareTo(Event other) {
		return this.time.compareTo(other.time);
	}

	public Event(LocalDateTime time, EventType type,Nerc n,Outage o) {
		super();
		this.time = time;
		this.type = type;
		this.n=n;
		this.o=o;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public Nerc getN() {
		return n;
	}

	public void setN(Nerc n) {
		this.n = n;
	}
	
	
	
}
