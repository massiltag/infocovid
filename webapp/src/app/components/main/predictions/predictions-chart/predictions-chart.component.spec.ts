import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PredictionsChartComponent } from './predictions-chart.component';

describe('PredictionsChartComponent', () => {
  let component: PredictionsChartComponent;
  let fixture: ComponentFixture<PredictionsChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PredictionsChartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PredictionsChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
