import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PredictionsConfComponent } from './predictions-conf.component';

describe('PredictionsConfComponent', () => {
  let component: PredictionsConfComponent;
  let fixture: ComponentFixture<PredictionsConfComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PredictionsConfComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PredictionsConfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
