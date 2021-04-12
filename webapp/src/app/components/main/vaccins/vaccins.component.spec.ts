import {ComponentFixture, TestBed} from '@angular/core/testing';

import {VaccinsComponent} from './vaccins.component';

describe('VaccinsComponent', () => {
  let component: VaccinsComponent;
  let fixture: ComponentFixture<VaccinsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VaccinsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VaccinsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
