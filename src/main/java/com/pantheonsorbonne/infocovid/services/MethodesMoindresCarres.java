package com.pantheonsorbonne.infocovid.services;
import java.time.temporal.TemporalAdjuster;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class MethodesMoindresCarres {

	private ArrayList<Long> jourList;
	private ArrayList<Long> nbVaccinationsList;
	private double a, b;
	boolean immunite = false;
	
	// a et b vont servir à construire la droite de prévision, pour l'année n+1 on fait a*(n+1) +b
	
	
	public MethodesMoindresCarres(ArrayList<Long> jourList, ArrayList<Long> nbVaccinationsList) {
		
		this.jourList = jourList;
		this.nbVaccinationsList = nbVaccinationsList;
		this.calculA();

		this.calculB();

	}
	
	// juste pr faire la somme des elements de la liste
	public Long sum(ArrayList<Long> chiffre) {
		Long sum=(long) 0;
		for (int i = 0; i < chiffre.size(); i++) {
			sum = sum + chiffre.get(i);
		}
		return sum;
	}
	
	// juste pr faire la somme au carré des elements de la liste
	public Long sumCarre(ArrayList<Long> chiffre) {
		Long sum=(long) 0;
		for (int i = 0; i < chiffre.size(); i++) {
			sum += chiffre.get(i)*chiffre.get(i);
		}
		return sum;
	}
	
	// moyenne nombre de vaccinations en tout
	public Long moyenneNBvacc() {
		return (sum(nbVaccinationsList) / nbVaccinationsList.size());
		
	}
	
	//moyenne des jours
	public Long moyenneJour() {
		return (sum(jourList) / jourList.size());
	}
	
	// somme des jours * nombre de vaccinations
	public Double sumJourVacc() {
		Double sum = (Double) 0.0;
		for (int i = 0; i < jourList.size(); i++) {
			sum = sum + (jourList.get(i) * nbVaccinationsList.get(i));
		}
		return sum;
	}
	
	// calculer a de y=ax+b
	public Double calculA() {
		this.a = (sumJourVacc()- ((jourList.size())*moyenneJour()*moyenneNBvacc()))/ (sumCarre(jourList)*(jourList.size()));
		return a;
	}
	
	// calculer b de y=ax+b
	public Double calculB() {
		this.b = moyenneNBvacc() - a*moyenneJour();
		return b;
	}
	
	// retourne le nombre de vaccinations dans les 60 jours / 2 mois
	/**
	 * utilise y = ax+b pour prédire les prochains nombre de vaccinations sur 2 semaines
	 * @return
	 */
	
	public HashMap<Double, Double> prevision() {
		HashMap<Double, Double> donnes=new HashMap<Double, Double>();
		Double nbVaccinations =0.0;

		int tauxImmunite = 67000000*60/100;

		for (Double i = (double) jourList.size(); i < jourList.size() +14; i++) {
			nbVaccinations = a*i+b;
			donnes.put(i, nbVaccinations);
			if (nbVaccinations >= tauxImmunite) {
				this.immunite = true;
			}
		}
		return donnes;
	}
	
/*	public static void  main(String args[]) {
		ArrayList<Long> anneesArrayList=new ArrayList<Long>();
		ArrayList<Long> chiffresDaffaireArrayList=new ArrayList<Long>();
		Random random=new Random();
		for(Long i=(long) 1000;i<2000;i++) {
			anneesArrayList.add(i);
			chiffresDaffaireArrayList.add((long) (0.001+random.nextFloat()*1000));
		}
		
		MethodesMoindresCarres m=new MethodesMoindresCarres(anneesArrayList,chiffresDaffaireArrayList);
		System.out.println(m.a+" "+m.b);
		HashMap<Double, Double> donnees=m.prevision();
		System.out.println(donnees);
	}
*/	
}


	
	

