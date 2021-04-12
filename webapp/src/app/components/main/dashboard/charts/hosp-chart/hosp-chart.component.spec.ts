import {ComponentFixture, TestBed} from '@angular/core/testing';

import {HospChartComponent} from './hosp-chart.component';

describe('ReaChartComponent', () => {
  let component: HospChartComponent;
  let fixture: ComponentFixture<HospChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HospChartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HospChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
