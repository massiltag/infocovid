import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PredictionsVaccinationComponent } from './predictions-vaccination.component';

describe('PredictionsVaccinationComponent', () => {
  let component: PredictionsVaccinationComponent;
  let fixture: ComponentFixture<PredictionsVaccinationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PredictionsVaccinationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PredictionsVaccinationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
