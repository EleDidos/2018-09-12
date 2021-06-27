package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.poweroutages.db.PowerOutagesDAO;

public class Model {
	
	private SimpleWeightedGraph< Nerc , DefaultWeightedEdge>graph;
	private Map <Integer, Nerc > idMap;
	private PowerOutagesDAO dao;
	private Simulatore sim;
	
	public Model() {
		idMap= new HashMap <Integer, Nerc >();
		dao=new PowerOutagesDAO();
	}
	
	public void creaGrafo() {
		graph= new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		dao.loadAllNercs(idMap);
		Graphs.addAllVertices(graph, idMap.values());
		
		for(Arco a : dao.listArchi(idMap))
			Graphs.addEdge(graph,a.getN1(),a.getN2(),a.getPeso());
		
		
	}
	
	public Integer getNVertici() {
		return graph.vertexSet().size();
	}
	
	public Integer getNArchi() {
		return graph.edgeSet().size();
	}
	

	public SimpleWeightedGraph< Nerc , DefaultWeightedEdge> getGraph() {
		return graph;
	}
	
	
	public void simula(Integer 	K) {
		List<Outage> out = dao.listAllOutagesInKMonths(idMap,K);
		sim=new Simulatore(graph,out);
		sim.run();
	}
	
	public int getNCatasftrofi() {
		return sim.getNCatasftrofi();
	}
	
	
	// NERC e BONUS
	public List<Output> getOutput(){
		return sim.getOutput();
	}
	

}
