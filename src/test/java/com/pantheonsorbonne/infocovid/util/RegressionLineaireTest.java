package com.pantheonsorbonne.infocovid.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pantheonsorbonne.infocovid.domain.model.PrevisionImmunite;
import com.pantheonsorbonne.infocovid.services.PrevisionService;

public class RegressionLineaireTest {

	private List<Integer> nbVaccinationsList;

	float tauxImmunite = (float) (67000000 * 0.7);
	boolean immunite = false;

	PrevisionImmunite previsionImmunite;
	RegressionLineaire regressionLineaire;

	private final PrevisionService previsionService = null;

	@BeforeEach
	public void initPrevision() {
		Integer[] listTemp = { 4665083, 4950923, 5174050, 5235148, 5387919, 5524802, 5669315, 5830908, 6049951, 6214850,
				6262935, 6426856, 6668849, 6918424, 7248910, 7597304, 7782591, 7857037, 8053426, 8328412, 8580219,
				8876379, 9143722, 9279895, 9335517, 9393313, 9591559, 9835792 };
		nbVaccinationsList = new ArrayList<Integer>();
		Collections.addAll(nbVaccinationsList, listTemp);

		regressionLineaire = RegressionLineaire.builder().nbVaccinationsList(nbVaccinationsList)
				.jourList(new ArrayList<Integer>()).build();
		previsionImmunite = regressionLineaire.previsionImmunite();

	}

	@Test
	public void testPrevisionImmunite_Immunite() throws IllegalArgumentException {
		assertEquals(previsionImmunite.isImmunite(), false);

	}

	@Test
	public void testPrevisionImmunite_nbVaccinQuotidien() throws IllegalArgumentException {
		Double[] listTemp2 = { 1.0354114E7, 1.0550612E7, 1.074711E7, 1.0943608E7, 1.1140106E7, 1.1336604E7, 1.1533102E7,
				1.17296E7, 1.1926098E7, 1.2122596E7, 1.2319094E7, 1.2515592E7, 1.271209E7, 1.2908588E7, 1.3105086E7 };
		List<Double> tempDoubles = new ArrayList<Double>();
		Collections.addAll(tempDoubles, listTemp2);

		assertEquals(previsionImmunite.getNbVaccinQuotidien(), tempDoubles);
	}

	@Test
	public void testPrevisionImmunite_nbCVaccinAtteint() throws IllegalArgumentException {
		assertEquals(previsionImmunite.getNbVaccinAtteint(), -1);
	}

}
