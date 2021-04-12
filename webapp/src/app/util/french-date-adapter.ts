import {Injectable} from '@angular/core';
import {Platform} from '@angular/cdk/platform';
import {NativeDateAdapter} from '@angular/material/core';

@Injectable()
export class FrenchDateAdapter extends NativeDateAdapter {
  constructor() {
    super('fr', new Platform(0));
  }

  getFirstDayOfWeek(): number {
    return 1;
  }
}
