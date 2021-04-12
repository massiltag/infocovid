import {ComponentFixture, TestBed} from '@angular/core/testing';

import {SelectComponent} from './select.component';

describe('SelectComponent', () => {
  let component: SelectComponent<any>;
  let fixture: ComponentFixture<SelectComponent<any>>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SelectComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SelectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
