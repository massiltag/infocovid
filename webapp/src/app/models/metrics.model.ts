import {StatsRecap} from './stats-recap.model';
import {VaccineStats} from './vaccine-stats.model';
import {Prevision_confinement} from './prevision_confinement.model';
import {Prevision_immunite} from './prevision_immunite.model';
export interface Metrics {

    date: string;

    recap: StatsRecap;

    vaccineStats: VaccineStats;

    prevision_confinement: Prevision_confinement;

    prevision_immunite: Prevision_immunite;

}
