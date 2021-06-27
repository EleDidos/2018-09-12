package it.polito.tdp.poweroutages;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Output;

public class Main {
	public static void main(String[] args) {
       
		Model model = new Model();
		model.creaGrafo();
		System.out.println("Caratteristiche del grafo:\n#VERTICI = "+model.getNVertici()+"\n#ARCHI = "+model.getNArchi());
		
		int K = 3; //mesi di simulazione
		//NON SO COME FARE IL CALCOLO DEI MESI
		model.simula(K);
		System.out.println("Numero di catastrofi = "+model.getNCatasftrofi()+"\n\nBonus:\n");
		for(Output o: model.getOutput())
			System.out.println(o+"\n");
    }
}