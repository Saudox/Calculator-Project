import { Component, EventEmitter, Output, Input } from '@angular/core';

@Component({
  selector: 'app-keypad',
  templateUrl: './keypad.html',
  styleUrl: './keypad.css'
})
export class KeypadComponent {
  @Input() errorMode: boolean = false;

  @Output() buttonClick = new EventEmitter<string>();

  onButtonClick(value: string) {
    this.buttonClick.emit(value);
  }
}
