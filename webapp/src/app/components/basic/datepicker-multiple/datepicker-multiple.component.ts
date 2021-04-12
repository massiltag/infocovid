import {Attribute, Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ControlValueAccessor, FormControlName} from '@angular/forms';
import {MatDatepickerInputEvent} from '@angular/material/datepicker';

@Component({
  selector: 'app-datepicker-multiple',
  templateUrl: './datepicker-multiple.component.html',
  styleUrls: ['./datepicker-multiple.component.scss']
})
export class DatepickerMultipleComponent implements ControlValueAccessor, OnInit {
  @Input() public min: Date = null;

  @Input() public max: Date = null;

  @Output() public selectionChange: EventEmitter<{ start: Date; end: Date }> = new EventEmitter();

  public value: { start: Date; end: Date } = { start: null, end: null };

  private onChange: (x: { start: Date; end: Date }) => void;
  private onTouched: () => void;

  constructor(@Attribute('placeholder') public readonly placeholder: string, public readonly formControl: FormControlName) {
    this.formControl.valueAccessor = this;
  }

  public ngOnInit(): void {
    this.value.start = this.value.start || this.min;
    this.value.end = this.value.end || this.max;
  }

  public onStartChange(event: MatDatepickerInputEvent<Date>): void {
    const start = new Date(event.value);
    if (this.value && this.value.end && start.getTime() > this.value.end.getTime()) {
      this.value = { start, end: null };
    } else {
      this.value = { ...this.value, start };
    }

    this.selectionChange.emit(this.value);
    this.onChange(this.value);
    this.onTouched();
  }

  public onEndChange(event: MatDatepickerInputEvent<Date>): void {
    this.value = { ...this.value, end: new Date(event.value) };
    this.selectionChange.emit(this.value);
    this.onChange(this.value);
    this.onTouched();
  }

  public writeValue(newValue: { start: Date; end: Date }): void {
    if (newValue) {
      this.value = newValue as { start: Date; end: Date };
    } else {
      this.value = { start: null, end: null };
    }
  }

  public registerOnChange(fn: (x: { start: Date; end: Date }) => void): void {
    this.onChange = fn;
  }

  public registerOnTouched(fn: () => void): void {
    this.onTouched = fn;
  }
}
