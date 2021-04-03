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
	

	
	public boolean previsionConfinement() {
		boolean confinement = false;
		int tauxMonteeCas = 0;
		int tauxMonteeDeces = 0;
		int tauxMonteeHospitalisations = 0;
		
		for (int i = 1; i < this.nbCas.size(); i++) {
			// en prenant en compte le nombre de cas et la vitesse de montée chaque jour
			if (this.nbCas.get(i) >= 25000 && this.nbCas.get(i)-this.nbCas.get(i-1)>= this.nbCas.get(i-1)*1.3) {
				tauxMonteeCas += 1;
			}else if (this.nbDeces.get(i) >= 100 && this.nbDeces.get(i)-this.nbDeces.get(i-1) >= this.nbDeces.get(i-1)*1.3) {
				tauxMonteeDeces += 1;
			}else if (this.nbHospitalisations.get(i) >= 1000 && this.nbHospitalisations.get(i)-this.nbHospitalisations.get(i-1) >= this.nbHospitalisations.get(i-1)*1.2) {
				tauxMonteeHospitalisations +=1;
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
			
		return confinement;
	}

}
