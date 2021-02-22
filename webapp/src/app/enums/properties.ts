import {SelectItem} from '../components/basic/select/select.component';
import {SampleEnum, SampleEnumFunc} from './sample.enum';

export enum Properties {
  SAMPLE = 'ABCD'
}

// tslint:disable-next-line:no-namespace
export namespace Properties {

  export const DEFAULT_LIST = [
    'a',
    'b'
  ];

  export const SAMPLE_OPTIONS: SelectItem<SampleEnum>[] = SampleEnumFunc.getAsMatSelectItems();

  export const DATE_FORMAT = {
    parse: {
      dateInput: ['DD/MM/YYYY']
    },
    display: {
      dateInput: 'DD/MM/YYYY',
      monthYearLabel: 'MMM YYYY',
      dateA11yLabel: 'LL',
      monthYearA11yLabel: 'MMMM YYYY',
    },
  };

}
