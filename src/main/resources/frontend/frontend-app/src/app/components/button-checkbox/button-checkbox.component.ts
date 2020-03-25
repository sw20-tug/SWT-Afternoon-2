import {Component} from '@angular/core';

@Component({
  selector: 'buttons-checkbox',
  templateUrl: './button-checkbox.component.html',
  styleUrls: ['./button-checkbox.component.css']
})
export class NgbdButtonsCheckbox {
  model = {
    left: true,
    middle: false,
    right: false
  };
}
