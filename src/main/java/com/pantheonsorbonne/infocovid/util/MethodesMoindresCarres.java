package com.pantheonsorbonne.infocovid.util;

import lombok.Builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.pantheonsorbonne.infocovid.domain.model.Prevision;
import com.pantheonsorbonne.infocovid.domain.model.PrevisionImmunite;

@Builder
public class MethodesMoindresCarres {

	private List<Integer> nbVaccinationsList;


	private List<Integer> jourList;
	private int a, b;
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
	public Integer sumJourVacc() {
		int sum = 0;
		for (int i = 0; i < jourList.size(); i++) {
			sum = sum + (jourList.get(i) * nbVaccinationsList.get(i));
		}
		return sum;
	}
	
	// calculer a de y=ax+b
	public void calculA() {
		this.a = (sumJourVacc()- ((jourList.size())*moyenneJour()*moyenneNBvacc()))/ (sumCarre(jourList)*(jourList.size()));
	}
	
	// calculer b de y=ax+b
	public void calculB() {
		this.b = moyenneNBvacc() - a*moyenneJour();
	}
	
	// retourne une prévision du nombre de vaccinations sur 14 jours
	/** et ça va changer en fonction du nombre de vaccinations à chaque fois qu'on avance dans le temps
	 * pour la prévision de l'immunité collective, on retourne aussi un boolééen qui va servir à dire si on l'a atteint ou pas.
	 * et pour finir on retourne le nombre de vaccinations du jour où l'immunité est atteinte
	 * 
	 * on utilise y = ax+b pour prédire les prochains nombres de vaccinations sur 2 semaines, sela créé une droite
	 * @return
	 */
	public PrevisionImmunite previsionImmunite() {
		
		// utilisé pour calculter y=ax+b (nombre de vaccinations pour un jour), connaitre si on atteint le nombre de
		//vaccinations nécessaire pour l'immunité, et pour récupérer le jour où l'immunité est atteinte.
		int nbVaccinations =0; 
		
		// utilisé pour récupérer sur le chart le jour où l'immunité a été atteinte (contient le nombre de vaccinations ce jour là).
		// valeur à retourner
		int nbVaccinAtteint=-1;
		
		// Liste qui récupère le nombre de vaccins prévisionnel pour 14 jours
		// valeurs à retourner
		ArrayList<Integer> nbVaccinQuotidien = new ArrayList<Integer>();
		
		// Le nombre minimum de vaccinés à atteindre pour l'immunité collective
		float tauxImmunite = (float) (67000000*0.6);
		
		// sert à enregistrer la première valeur seulement du nombre de vaccins quand on atteint l'immunité
		boolean arretCalcul=false;
		
		// Le nombre de vaccinations en entrée, on les utilise pour définir les paramètres a et b de la méthode des
		// moindres carrés
		int n = 0;

		
		// L'objet à retourner à la fin, regroupe immunite, nbVaccinAtteint et nbVaccinQuotidien
		PrevisionImmunite previsionImmunite=null;

		// à utiliser ensuite pour avoir un nombre de jour mis à jour
		for (Integer ignored : this.nbVaccinationsList) {
			jourList.add(n);
			n++;
		}
		this.calculA();
		this.calculB();
		// Calcul prévisions du nombre de vaccinations sur les prochains 14 jours 
		// Pour chaque jour :
		for (int i = jourList.size(); i < jourList.size() +14; i++) {
			// calcul du nombre de vaccinations prévisionnel
			nbVaccinations = a*i+b;

			nbVaccinQuotidien.add(nbVaccinations);
			
			// si le nombre de vaccinations prévisionnel a atteint le taux d'immunité collective
			// le booléen sera à true et on enregistre ce nombre là
			if (nbVaccinations >= tauxImmunite) {
				this.immunite = true;
				nbVaccinAtteint= nbVaccinations;
				break;
			}
		}
		return PrevisionImmunite.builder()
				.immunite(immunite)
				.nbVaccinAtteint(nbVaccinAtteint)
				.nbVaccinQuotidien(nbVaccinQuotidien)
				.build();
	}
	

}


	
	

