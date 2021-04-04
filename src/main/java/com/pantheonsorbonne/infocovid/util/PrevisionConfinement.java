package com.pantheonsorbonne.infocovid.util;
import com.pantheonsorbonne.infocovid.domain.model.Prevision;
import lombok.Builder;

import java.util.List;


@Builder
public class PrevisionConfinement {

	private List<Integer> nbCas, nbHospitalisations, nbDeces;
	

	/**
	 * @return un tableau où le premier champ correspond à un booléen qui dit si ya possibilité de confinement,
	 * et le 2eme champ qui donne le jour à partir duquel le confinement devrait commencer
	 */
	public Prevision previsionConfinement() {
		boolean confinement = false;
		int tauxMonteeCas = 0;
		int tauxMonteeDeces = 0;
		int tauxMonteeHospitalisations = 0;
		int nbDeCasConcerne = -1;

		for (int i = 1; i < this.nbCas.size(); i++) {
			// en prenant en compte le nombre de cas et la vitesse de montée chaque jour
			if (this.nbCas.get(i) >= 25000 && this.nbCas.get(i)-this.nbCas.get(i-1)>= this.nbCas.get(i-1)*1.3) {
				tauxMonteeCas += 1;
				if (nbDeCasConcerne == -1) {
					nbDeCasConcerne = this.nbCas.get(i);
				}
			}else if (this.nbDeces.get(i) >= 100 && this.nbDeces.get(i)-this.nbDeces.get(i-1) >= this.nbDeces.get(i-1)*1.3) {
				tauxMonteeDeces += 1;
				if (nbDeCasConcerne == -1) {
					nbDeCasConcerne = this.nbDeces.get(i);
				}
			}else if (this.nbHospitalisations.get(i) >= 1000 && this.nbHospitalisations.get(i)-this.nbHospitalisations.get(i-1) >= this.nbHospitalisations.get(i-1)*1.2) {
				tauxMonteeHospitalisations +=1;
				if (nbDeCasConcerne == -1) {
					nbDeCasConcerne = this.nbHospitalisations.get(i);
				}
			}
		}
		
		if (tauxMonteeCas >= 5 || tauxMonteeDeces >= 5 || tauxMonteeHospitalisations >= 5) {
			confinement = true;

		}else if (tauxMonteeHospitalisations >=3 && tauxMonteeCas >= 3) {
			confinement = true;
			
		}else if (tauxMonteeHospitalisations >=3 && tauxMonteeDeces >= 3) {
			confinement = true;
		}

		return Prevision.builder()
				.confinement(confinement)
				.nombreCas(nbDeCasConcerne)
				.build();
	}

}
