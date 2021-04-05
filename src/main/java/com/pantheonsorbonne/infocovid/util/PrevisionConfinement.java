package com.pantheonsorbonne.infocovid.util;
import com.pantheonsorbonne.infocovid.domain.model.Prevision;
import lombok.Builder;

import java.awt.print.Printable;
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
		String typeCas= "";

		for (int i = 1; i < this.nbCas.size(); i++) {
			
			
			// en prenant en compte le nombre de cas et la vitesse de montée chaque jour
			if (this.nbCas.get(i-1) >= 25000 && this.nbCas.get(i)-this.nbCas.get(i-1)>= this.nbCas.get(i-1)*0.01) {
				System.out.println("in 1");
				tauxMonteeCas += 1;
				if (nbDeCasConcerne == -1) {
					nbDeCasConcerne = this.nbCas.get(i);
					typeCas = "casCovid";
					
				}
			}if (this.nbDeces.get(i-1) >= 100 && this.nbDeces.get(i)-this.nbDeces.get(i-1) >= this.nbDeces.get(i-1)*0.009) {
				System.out.println("in 2");
				tauxMonteeDeces += 1;
				if (nbDeCasConcerne == -1) {
					nbDeCasConcerne = this.nbDeces.get(i);
					typeCas = "deces";

				}
			}if (this.nbHospitalisations.get(i-1) >= 1000 && this.nbHospitalisations.get(i)-this.nbHospitalisations.get(i-1) >= this.nbHospitalisations.get(i-1)*0.006) {
				System.out.println("in 3");
				tauxMonteeHospitalisations +=1;
				if (nbDeCasConcerne == -1) {
					nbDeCasConcerne = this.nbHospitalisations.get(i);
					typeCas = "hospi";

				}
			}
		}
		if(5<=0.4*tauxMonteeCas+0.3*(tauxMonteeDeces+tauxMonteeHospitalisations)) {
			confinement = true;
		}

		return Prevision.builder()
				.confinement(confinement)
				.nombreCas(nbDeCasConcerne)
				.typeCas(typeCas)
				.build();
	}

}
