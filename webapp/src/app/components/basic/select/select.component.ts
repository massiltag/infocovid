import {Attribute, Component, EventEmitter, Input, Output} from '@angular/core';
import {ControlValueAccessor, FormControlName, FormGroupDirective} from '@angular/forms';
import {MatSelectChange} from '@angular/material/select';

export interface SelectItem<T> {
  text: string;
  value: T;
}

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.scss']
})
export class SelectComponent<T> implements ControlValueAccessor {
  @Input() public options: SelectItem<T>[];

  @Output() public selectionChange: EventEmitter<T> = new EventEmitter();

  public value: T;

  public formGroupDirective: FormGroupDirective;

  private onChange: (x: T) => void;
  private onTouched: () => void;

  constructor(@Attribute('placeholder') public readonly placeholder: string, public readonly formControl: FormControlName) {
    this.formControl.valueAccessor = this;
    this.formGroupDirective = this.formControl.formDirective;
  }

  public onOptionSelected(event: MatSelectChange): void {
    this.value = event.value;
    this.selectionChange.emit(this.value);
    this.onChange(this.value);
    this.onTouched();
  }

  public writeValue(newValue: T): void {
    this.value = newValue;
  }

  public registerOnChange(fn: (x: T) => void): void {
    this.onChange = fn;
  }

  public registerOnTouched(fn: () => void): void {
    this.onTouched = fn;
  }

  // tslint:disable-next-line: no-any
  public onClickClearAll(event: any): void {
    this.value = null;
    this.onOptionSelected({ value: null } as MatSelectChange);
    event.stopPropagation();
  }
}

