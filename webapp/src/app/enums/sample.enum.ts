import {SelectItem} from '../components/basic/select/select.component';

export enum SampleEnum {
  A = 'a',
  B = 'b'
}

export const settings = [
  {
    type: SampleEnum.A,
    color: 'tomato',
    icon: 'build'
  },
  {
    type: SampleEnum.B,
    color: 'royalblue',
    icon: 'add'
  }
];

// tslint:disable-next-line:no-namespace
export namespace SampleEnumFunc {

  export function getAsMatSelectItems(): SelectItem<SampleEnum>[] {
    let array;
    array = [];
    for (const item of Object.values(SampleEnum)) {
      array.push({
        text: item,
        value: item
      });
    }
    return array;
  }

  export function getAsStringArray(): string[] {
    let array;
    array = [];
    for (const item of Object.values(SampleEnum)) {
      array.push(item);
    }
    return array;
  }

  // tslint:disable-next-line:typedef
  export function getSettings(type: string) {
    return settings.filter(o => o.type.toLowerCase() === type.toLowerCase())[0];
  }

}
