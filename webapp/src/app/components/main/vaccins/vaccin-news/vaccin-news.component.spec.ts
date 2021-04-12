import {ComponentFixture, TestBed} from '@angular/core/testing';

import {VaccinNewsComponent} from './vaccin-news.component';

describe('VaccinNewsComponent', () => {
  let component: VaccinNewsComponent;
  let fixture: ComponentFixture<VaccinNewsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VaccinNewsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VaccinNewsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
