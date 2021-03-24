import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MapVaccinComponent } from './map-vaccin.component';

describe('MapVaccinComponent', () => {
  let component: MapVaccinComponent;
  let fixture: ComponentFixture<MapVaccinComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MapVaccinComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MapVaccinComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
