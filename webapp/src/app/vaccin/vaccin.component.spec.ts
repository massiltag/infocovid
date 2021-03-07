import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VaccinComponent } from './vaccin.component';

describe('VaccinComponent', () => {
  let component: VaccinComponent;
  let fixture: ComponentFixture<VaccinComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VaccinComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VaccinComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
