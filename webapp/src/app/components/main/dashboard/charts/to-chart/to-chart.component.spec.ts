import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ToChartComponent} from './to-chart.component';

describe('DeathsChartComponent', () => {
  let component: ToChartComponent;
  let fixture: ComponentFixture<ToChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ToChartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ToChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
