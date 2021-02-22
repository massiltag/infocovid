import {Attribute, Component, EventEmitter, Input, Output} from '@angular/core';
import {ControlValueAccessor, FormControlName, FormGroupDirective} from '@angular/forms';
import {MatSelectChange} from '@angular/material/select';
import {SelectItem} from '../select/select.component';

@Component({
  selector: 'app-select-checkbox',
  templateUrl: './select-checkbox.component.html',
  styleUrls: ['./select-checkbox.component.scss']
})
export class SelectCheckboxComponent<T> implements ControlValueAccessor {
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
