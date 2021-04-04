package com.pantheonsorbonne.infocovid.util;

import lombok.Builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Builder
public class MethodesMoindresCarres {

	private ArrayList<Integer> nbVaccinationsList;


	private List<Integer> jourList;
	private double a, b;
	boolean immunite = false;

	// a et b vont servir à construire la droite de prévision, pour l'année n+1 on fait a*(n+1) +b

	
	// juste pr faire la somme des elements de la liste
	public Integer sum(List<Integer> chiffre) {
		Integer sum=(Integer) 0;
		for (int i = 0; i < chiffre.size(); i++) {
			sum = sum + chiffre.get(i);
		}
		return sum;
	}
	
	// juste pr faire la somme au carré des elements de la liste
	public Integer sumCarre(List<Integer> chiffre) {
		Integer sum = 0;
		for (int i = 0; i < chiffre.size(); i++) {
			sum += chiffre.get(i)*chiffre.get(i);
		}
		return sum;
	}
	
	// moyenne nombre de vaccinations en tout
	public Integer moyenneNBvacc() {
		return (sum(nbVaccinationsList) / nbVaccinationsList.size());
		
	}
	
	//moyenne des jours
	public Integer moyenneJour() {
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
	
	// retourne une prévision du nombre de vaccinations sur 14 jours
	/** et ça va changer en fonction du nombre de vaccinations à chaque fois qu'on avance dans le temps
	 * pour la prévision de l'immunité collective, ya un boolééen qui va servir à dire si on l'a atteint ou pas.
	 * utilise y = ax+b pour prédire les prochains nombre de vaccinations sur 2 semaines
	 * @return
	 */
	
	public HashMap<Double, Double> prevision() {
		HashMap<Double, Double> donnes=new HashMap<Double, Double>();
		Double nbVaccinations =0.0;

		int tauxImmunite = 67000000*60/100;

		int n = 0;
		for (Integer ignored : this.nbVaccinationsList) {
			jourList.add(n);
			n++;
		}

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
		ArrayList<Integer> anneesArrayList=new ArrayList<Integer>();
		ArrayList<Integer> chiffresDaffaireArrayList=new ArrayList<Integer>();
		Random random=new Random();
		for(Integer i=(Integer) 1000;i<2000;i++) {
			anneesArrayList.add(i);
			chiffresDaffaireArrayList.add((Integer) (0.001+random.nextFloat()*1000));
		}
		
		MethodesMoindresCarres m=new MethodesMoindresCarres(anneesArrayList,chiffresDaffaireArrayList);
		System.out.println(m.a+" "+m.b);
		HashMap<Double, Double> donnees=m.prevision();
		System.out.println(donnees);
	}
*/	
}


	
	

