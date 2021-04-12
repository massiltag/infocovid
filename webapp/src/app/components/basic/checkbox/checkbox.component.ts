import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-checkbox',
  templateUrl: './checkbox.component.html',
  styleUrls: ['./checkbox.component.scss']
})
export class CheckboxComponent implements OnInit {
  @Input() color = 'primary';
  @Input() checked = false;
  @Input() indeterminate = false;
  @Input() labelPosition: 'before' | 'after' = 'before';
  @Input() disabled = false;

  @Output() public valueChange: EventEmitter<boolean> = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }

  onChange(event: any): void {
    this.valueChange.emit(event);
  }

}
