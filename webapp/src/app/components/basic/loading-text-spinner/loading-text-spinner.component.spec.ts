import {ComponentFixture, TestBed} from '@angular/core/testing';

import {LoadingTextSpinnerComponent} from './loading-text-spinner.component';

describe('LoadingTextSpinnerComponent', () => {
  let component: LoadingTextSpinnerComponent;
  let fixture: ComponentFixture<LoadingTextSpinnerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoadingTextSpinnerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoadingTextSpinnerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
