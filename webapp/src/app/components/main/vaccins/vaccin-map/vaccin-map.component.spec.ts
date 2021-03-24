import {ComponentFixture, TestBed} from '@angular/core/testing';

import {VaccinMapComponent} from './vaccin-map.component';

describe('VaccinMapComponent', () => {
  let component: VaccinMapComponent;
  let fixture: ComponentFixture<VaccinMapComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VaccinMapComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VaccinMapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
