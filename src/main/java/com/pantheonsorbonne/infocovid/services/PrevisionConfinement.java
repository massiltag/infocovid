package com.pantheonsorbonne.infocovid.services;
import java.util.ArrayList;

public class PrevisionConfinement {
	
	private ArrayList<Long> nbCas, nbHospitalisations, nbDeces;
	private Long populationFrance;
	

	public PrevisionConfinement(ArrayList<Long> nbCas, ArrayList<Long> nbHospitalisations, ArrayList<Long> nbDeces, Long population) {
		this.nbCas=nbCas;
		this.nbDeces=nbDeces;
		this.nbHospitalisations=nbHospitalisations;
		this.populationFrance=population;
	}
	

	/**
	 * 
	 * @return un tableau où le premier champ correspond à un booléen qui dit si ya possibilité de confinement,
	 * et le 2eme champ qui donne le jour à partir duquel le confinement devrait commencer
	 */
	public ArrayList<Object> previsionConfinement() {
		boolean confinement = false;
		int tauxMonteeCas = 0;
		int tauxMonteeDeces = 0;
		int tauxMonteeHospitalisations = 0;
		Long nbDeCasConcerne =null;
		ArrayList<Object> previsionConfinement = null;
		
		for (int i = 1; i < this.nbCas.size(); i++) {
			// en prenant en compte le nombre de cas et la vitesse de montée chaque jour
			if (this.nbCas.get(i) >= 25000 && this.nbCas.get(i)-this.nbCas.get(i-1)>= this.nbCas.get(i-1)*1.3) {
				tauxMonteeCas += 1;
				if (nbDeCasConcerne == null) {
					nbDeCasConcerne = this.nbCas.get(i);
				}
			}else if (this.nbDeces.get(i) >= 100 && this.nbDeces.get(i)-this.nbDeces.get(i-1) >= this.nbDeces.get(i-1)*1.3) {
				tauxMonteeDeces += 1;
				if (nbDeCasConcerne == null) {
					nbDeCasConcerne = this.nbDeces.get(i);
				}
			}else if (this.nbHospitalisations.get(i) >= 1000 && this.nbHospitalisations.get(i)-this.nbHospitalisations.get(i-1) >= this.nbHospitalisations.get(i-1)*1.2) {
				tauxMonteeHospitalisations +=1;
				if (nbDeCasConcerne == null) {
					nbDeCasConcerne = this.nbHospitalisations.get(i);
				}
			}
		}
		
		if (tauxMonteeCas >= 5 || tauxMonteeDeces >= 5 || tauxMonteeHospitalisations >= 5) {
			confinement = true;
		}else if (tauxMonteeCas >=3 && tauxMonteeCas >= 3) {
			confinement = true;
			
		}else if (tauxMonteeHospitalisations >=3 && tauxMonteeCas >= 3) {
			confinement = true;
			
		}else if (tauxMonteeHospitalisations >=3 && tauxMonteeDeces >= 3) {
			confinement = true;
		}
			previsionConfinement.add(confinement);
			previsionConfinement.add(nbDeCasConcerne);
			
		return previsionConfinement; // et retourner le boolean ?;
	}

}
