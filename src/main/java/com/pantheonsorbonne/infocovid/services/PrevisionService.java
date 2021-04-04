package com.pantheonsorbonne.infocovid.services;

import com.pantheonsorbonne.infocovid.domain.model.Prevision;
import com.pantheonsorbonne.infocovid.domain.model.PrevisionImmunite;

public interface PrevisionService {

    Prevision getPrevision();

    PrevisionImmunite getMethodeMoindreCarres();

}
