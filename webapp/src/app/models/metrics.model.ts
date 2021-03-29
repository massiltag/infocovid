import {StatsRecap} from './stats-recap.model';
import {VaccineStats} from './vaccine-stats.model';

export interface Metrics {

    date: string;

    recap: StatsRecap;

    vaccineStats: VaccineStats;

}
