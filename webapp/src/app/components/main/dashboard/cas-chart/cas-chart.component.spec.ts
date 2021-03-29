import {ComponentFixture, TestBed} from '@angular/core/testing';

import {CasChartComponent} from './cas-chart.component';

describe('ChartComponent', () => {
  let component: CasChartComponent;
  let fixture: ComponentFixture<CasChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CasChartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CasChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
