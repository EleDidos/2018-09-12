package it.polito.tdp.poweroutages.model;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.poweroutages.model.Event.EventType;

public class Simulatore {
	private PriorityQueue<Event> queue;
	private SimpleWeightedGraph<Nerc, DefaultWeightedEdge>graph;
	
	private Integer catastrofe;
	
	
	public Simulatore(SimpleWeightedGraph<Nerc, DefaultWeightedEdge>graph, List<Outage> outages) {
		
		catastrofe=0;
		queue = new PriorityQueue<Event> ();
		this.graph=graph;
		
		for(Outage o:outages) {
			//evento inizio  per ogni outage
			Event e1=new Event(o.getD1(),EventType.INIZIO, o.getNerc(), o);
			queue.add(e1);
			
		}
		
		
		
	}
	
	
	public void run() {
		while(!queue.isEmpty()) {
			Event e = this.queue.poll();
			processEvent(e);
		}//while
	}
	
	
	private void processEvent(Event e) {
		switch(e.getType()) {
				
			case INIZIO: //da chi si fa aiutare il nerc con blackout?
				Nerc scelto=this.aiuto(e.getN());
				
				if(scelto!=null) {
					Event e2=new Event(e.getO().getD2(),EventType.FINE, e.getO().getNerc(), e.getO());
					e2.setDonatore(scelto);
					scelto.addVicinoAiutato(e.getN());
					queue.add(e2);
				
				}
				break;
				
			case FINE:
				Nerc donatore = e.getDonatore();
				donatore.setImpegnato(false);
				Long bonus = (e.getO().getD1()).until(e.getO().getD2(), ChronoUnit.DAYS);
				donatore.addGGServizi(bonus);
				break;
				
			default:
				break;
		}
	}
	
	
	/**
	 * deve scegliere da chi farsi aiutare
	 * @param daAiutare
	 */
	private Nerc aiuto(Nerc daAiutare) {
		
	//DA CHI FARSI AIUTARE???
		Nerc scelto;
		
		//non ha vicini
				if(Graphs.neighborListOf(graph, daAiutare)==null) {
					catastrofe++;
					return null;
				}
		
		//1.vicino cha ha aiutato
		List <Nerc> possibili = new ArrayList <Nerc>(); //DONATORI
		
		for(Nerc n:Graphs.neighborListOf(graph, daAiutare))
			if(daAiutare.getAiutati().contains(n))
				possibili.add(n);
		
		if(possibili.size()==1 && possibili.get(0).isImpegnato()==false)
			scelto=possibili.get(0);
		
		//2. vicino con minor peso
		else if(possibili.size()==0) {
			scelto=this.getMinore(Graphs.neighborListOf(graph, daAiutare), daAiutare);
		}
		
		//3. trovato pià di un donatore tra quelli che aveva aiutato a sua volta
		//scelgo minore tra i donatori
		else if(possibili.size()>1) {
			scelto=this.getMinore(possibili, daAiutare);
		}
		
		//se niente va bene--> scelgo minore e libero
		else {
			scelto=this.getMinore(Graphs.neighborListOf(graph, daAiutare), daAiutare);
		}
		
		//se non ci sono vicini liberi
		if(scelto==null)
			catastrofe++;
		else
			scelto.setImpegnato(true);
		
		return scelto;
		
	}
	
	
	/**
	 * Scegli Nerc della lista passata con peso
	 * minore = indice di correlazione minore
	 */
	private Nerc getMinore(List <Nerc> vicini, Nerc daAiutare) {
		double min=Double.MAX_VALUE;
		Nerc minimo=null;
		
		for(Nerc n: vicini) {
			DefaultWeightedEdge e = graph.getEdge(daAiutare, n);
			if(graph.getEdgeWeight(e)<min & n.isImpegnato()==false) {
				min=graph.getEdgeWeight(e);
				minimo=n;
			}
		}
		if(minimo==null)
			return null; //non ci sono vicini liberi
		return minimo;
			
	}
	
	
	public int getNCatasftrofi() {
		return catastrofe;
	}
	
	
	// NERC e BONUS
	public List<Output> getOutput(){
		List<Output> finale= new ArrayList <Output >();
		for(Nerc n:graph.vertexSet()) {
			Output o = new Output(n,(long)n.getBonus());
			finale.add(o);
		}
		return finale;
	}

}
