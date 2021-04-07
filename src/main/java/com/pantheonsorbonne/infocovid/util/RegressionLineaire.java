package com.pantheonsorbonne.infocovid.util;

import java.util.ArrayList;
import java.util.List;

import com.pantheonsorbonne.infocovid.domain.model.PrevisionImmunite;

import lombok.Builder;

@Builder
public class RegressionLineaire {

	private List<Integer> nbVaccinationsList;
	private List<Integer> jourList;

	private int intercept;
	private int slope;
	private int r2;
	private int svar0;
	private int svar1;
	
	boolean immunite = false;

	public void linearRegression() {

		
		// à utiliser ensuite pour avoir un nombre de jour mis à jour

		int nb=0;
		for (Integer ignored : this.nbVaccinationsList) {
			jourList.add((int) nb);
			nb++;
		}
		int n = this.jourList.size();

		// first pass
		int sumJour =0, sumNbVacc = 0, sumJour2= 0;

			for(int i = 0;i<n;i++){
				sumJour += this.jourList.get(i);
				sumJour2 += this.jourList.get(i) * this.jourList.get(i);
				sumNbVacc += this.nbVaccinationsList.get(i);
			}
		int jourBar = sumJour / n;
		int nbVaccBar = sumNbVacc / n;
		
		//second pass: compute summary statistics
		int jour2bar = 0, nbVacc2bar = 0, jourNbVaccBar = 0;
		for (int i = 0; i < n; i++) {
			jour2bar += (this.jourList.get(i) - jourBar) * (this.jourList.get(i) - jourBar);
			nbVacc2bar += (this.nbVaccinationsList.get(i) - nbVaccBar) * (this.nbVaccinationsList.get(i) - nbVaccBar);
			jourNbVaccBar += (this.jourList.get(i) - jourBar) * (this.nbVaccinationsList.get(i) - nbVaccBar);
		}
		
		this.slope = jourNbVaccBar / jour2bar;
		this.intercept = nbVaccBar - slope * jourBar;
		
		//more statistical analysis
		int rss = 0;
		int ssr = 0;
		
		for (int i = 0; i < n; i++) {
			int fit = slope*this.jourList.get(i) + intercept;
			rss += (fit - this.nbVaccinationsList.get(i)) * (fit - this.nbVaccinationsList.get(i));
			ssr += (fit - nbVaccBar) * (fit - nbVaccBar);
		}
		
		int degrees = n-2;
		this.r2 = ssr/nbVacc2bar;
		
		int svar = rss / degrees;
		this.svar1 = svar / jour2bar;
		this.svar0 = svar / n + jourBar*jourBar*svar1;
		
		if (this.jourList.size() != this.nbVaccinationsList.size()) {
			throw new IllegalArgumentException("la longueur des tableaux n'dst pas la meme");
		} 
	
	}
	
	public int getIntercept() {
		return intercept;
	}
	
	public int getSlope() {
		return slope;
	}
	
	public int R2() {
		return r2;
	}	
	
	
	public double slopeStdErr() {
		return Math.sqrt(svar1);
	}
	
	
	public int prediction(int x) {
		return slope*x + intercept;
	}
	
	public PrevisionImmunite previsionImmunite() {
		int nbVaccinations = 0;
		int nbVaccinAtteint = -1;
		
		ArrayList<Double> nbVaccinQuotidien = new ArrayList<Double>();
		float tauxImmunite = (float) (67000000 * 0.6);

		this.linearRegression();
		System.out.println(jourList.size());
	

		for (int i = jourList.size() +1 ; i <= jourList.size() + 15; i++) {

			System.out.println(i);
			// calcul du nombre de vaccinations prévisionnel
			nbVaccinations = prediction(i);
			System.out.println(slope);
			System.out.println(intercept);
			System.out.println(nbVaccinations);

			nbVaccinQuotidien.add((double) nbVaccinations);

			// si le nombre de vaccinations prévisionnel a atteint le taux d'immunité
			// collective le booléen sera à true et on enregistre ce nombre là
			if (nbVaccinations >= tauxImmunite) {
				this.immunite = true;
				nbVaccinAtteint = nbVaccinations;
				break;
			}
		}
		return PrevisionImmunite.builder().immunite(immunite).nbVaccinAtteint(nbVaccinAtteint)
				.nbVaccinQuotidien(nbVaccinQuotidien).build();
		
	}
}
