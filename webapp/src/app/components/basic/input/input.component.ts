import {Attribute, Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControlName, FormGroupDirective} from '@angular/forms';

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.scss']
})
export class InputComponent implements OnInit {
  @Output() public selectionChange: EventEmitter<string> = new EventEmitter();

  public value: string;

  public showClearAll = false;

  public formGroupDirective: FormGroupDirective;

  private onChange: (x: string) => void;
  private onTouched: () => void;

  constructor(@Attribute('placeholder') public readonly placeholder: string,
              @Attribute('type') public readonly type: string,
              public readonly formControl: FormControlName) {
    this.formControl.valueAccessor = this;
    this.formGroupDirective = this.formControl.formDirective;
  }

  ngOnInit(): void {}

  public onInputChange(event: { target: { value: string } }): void {
    this.showClearAll = event.target.value.length > 0;
    this.value = event.target.value;
    this.selectionChange.emit(this.value);
    this.onChange(this.value);
    this.onTouched();
  }

  public writeValue(newValue: string): void {
    newValue ? (this.showClearAll = true) : (this.showClearAll = false);
    this.value = newValue;
  }

  public registerOnChange(fn: (x: string) => void): void {
    this.onChange = fn;
  }

  public registerOnTouched(fn: () => void): void {
    this.onTouched = fn;
  }

  public onClickClearAll(): void {
    this.onInputChange({ target: { value: '' } });
  }

}
