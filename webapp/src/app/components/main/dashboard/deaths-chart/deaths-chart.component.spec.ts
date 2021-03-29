import {ComponentFixture, TestBed} from '@angular/core/testing';

import {DeathsChartComponent} from './deaths-chart.component';

describe('DeathsChartComponent', () => {
  let component: DeathsChartComponent;
  let fixture: ComponentFixture<DeathsChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeathsChartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeathsChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
