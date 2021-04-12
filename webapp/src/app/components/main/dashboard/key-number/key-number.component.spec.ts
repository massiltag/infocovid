import {ComponentFixture, TestBed} from '@angular/core/testing';

import {KeyNumberComponent} from './key-number.component';

describe('KeyNumberComponent', () => {
  let component: KeyNumberComponent;
  let fixture: ComponentFixture<KeyNumberComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KeyNumberComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KeyNumberComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
