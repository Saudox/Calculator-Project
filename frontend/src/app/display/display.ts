import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-display',
  standalone: true,
  templateUrl: './display.html',
  styleUrls: ['./display.css']
})
export class DisplayComponent {
  @Input() value = ''; // parent binds [value]="display"
}
