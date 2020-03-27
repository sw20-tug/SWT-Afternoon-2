import {Component, Input} from '@angular/core';

@Component({
  selector: 'buttons-checkbox',
  templateUrl: './button-checkbox.component.html',
  styleUrls: ['./button-checkbox.component.css']
})
export class NgbdButtonsCheckbox {
  @Input() value;
  @Input() enabled= true;
  model = {
    left: true,
    middle: false,
    right: false
  };
  formatLabel(value: number) {
    if (value >= 1000) {
      return Math.round(value / 1000) + 'k';
    }
    return value;
  }
  change($event)
  {
    console.log("TODO!");
  }
}

