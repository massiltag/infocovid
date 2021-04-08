package com.pantheonsorbonne.infocovid.util;

import java.util.ArrayList;
import java.util.List;

import com.pantheonsorbonne.infocovid.domain.model.PrevisionImmunite;

import lombok.Builder;

/**
 * La régression linéaire simple est utilisée dans notre projet pour prédire l'immunité
 * collective en établissant une relation linéaire entre les valeurs du nombre
 * de vaccination
 * 
 * On affichera donc sur le graphique le nombre de vaccinations sur les 30
 * derniers jours ainsi que la prévisionsur 14 jours. Si l'immunité est
 * atteinte, un point sera affiché sur le jour concerné.
 * 
 * @author Equipe Scoovidoo
 *
 */

@Builder
public class RegressionLineaire {

	private List<Integer> nbVaccinationsList;
	private List<Integer> jourList;

	private int b;
	private int a;
	private int r2;
	private int svar0;
	private int svar1;

	boolean immunite = false;

	/**
	 * Calculs nécessaires pour déterminer la droite de tendance y= ax + b afin de
	 * formuler les prévisions
	 */
	public void linearRegression() {

		int nb = 0;
		for (Integer ignored : this.nbVaccinationsList) {
			jourList.add((int) nb);
			nb++;
		}
		int n = this.jourList.size();

		// 1er pass
		int sumJour = 0, sumNbVacc = 0, sumJour2 = 0;

		for (int i = 0; i < n; i++) {
			sumJour += this.jourList.get(i);
			sumJour2 += this.jourList.get(i) * this.jourList.get(i);
			sumNbVacc += this.nbVaccinationsList.get(i);
		}
		int jourBar = sumJour / n;
		int nbVaccBar = sumNbVacc / n;

		// 2eme pass: récap
		int jour2bar = 0, nbVacc2bar = 0, jourNbVaccBar = 0;
		for (int i = 0; i < n; i++) {
			jour2bar += (this.jourList.get(i) - jourBar) * (this.jourList.get(i) - jourBar);
			nbVacc2bar += (this.nbVaccinationsList.get(i) - nbVaccBar) * (this.nbVaccinationsList.get(i) - nbVaccBar);
			jourNbVaccBar += (this.jourList.get(i) - jourBar) * (this.nbVaccinationsList.get(i) - nbVaccBar);
		}

		this.a = jourNbVaccBar / jour2bar;
		this.b = nbVaccBar - a * jourBar;

		// d'autres analyses statistiques
		int rss = 0;
		int ssr = 0;

		for (int i = 0; i < n; i++) {
			int fit = a * this.jourList.get(i) + b;
			rss += (fit - this.nbVaccinationsList.get(i)) * (fit - this.nbVaccinationsList.get(i));
			ssr += (fit - nbVaccBar) * (fit - nbVaccBar);
		}

		int degre = n - 2;
		this.r2 = ssr / nbVacc2bar;

		int svar = rss / degre;
		this.svar1 = svar / jour2bar;
		this.svar0 = svar / n + jourBar * jourBar * svar1;

		if (this.jourList.size() != this.nbVaccinationsList.size()) {
			throw new IllegalArgumentException("la longueur des tableaux n'dst pas la meme");
		}

	}

	public int getB() {
		return b;
	}

	public int getA() {
		return a;
	}

	public int R2() {
		return r2;
	}

	public double aStdErr() {
		return Math.sqrt(svar1);
	}

	public int prediction(int x) {
		return a * x + b;
	}

	/**
	 * calcul du nombre de vaccinations prévisionnel
	 * 
	 * @return un objet PrevisionImmunite contenant une liste du nombre de
	 *         vaccination prévisionnel sur les 14 jours suivants , un booléen
	 *         indiquant si l'immunité collective est atteinte et le nombre de
	 *         vaccination atteint le jour de l'immunité (pour afficher dans le
	 *         graphique)
	 */
	public PrevisionImmunite previsionImmunite() {
		int nbVaccinations = 0;
		int nbVaccinAtteint = -1;

		ArrayList<Double> nbVaccinQuotidien = new ArrayList<Double>();
		float tauxImmunite = (float) (67000000 * 0.6);

		this.linearRegression();
		System.out.println(jourList.size());

		for (int i = jourList.size() + 1; i <= jourList.size() + 15; i++) {

			System.out.println(i);

			nbVaccinations = prediction(i);

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
