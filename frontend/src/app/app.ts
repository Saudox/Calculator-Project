import { Component } from '@angular/core';
import { DisplayComponent } from './display/display';
import { KeypadComponent } from './keypad/keypad';
import { HttpClient } from '@angular/common/http';
import { HeaderComponent } from './header/header';
import {NgIf} from '@angular/common';
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [DisplayComponent, KeypadComponent, HeaderComponent, NgIf],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  display: string = '';
  errorMessage : string | null = null;
  errorMode: boolean = false;
  constructor(private http: HttpClient) {}

  handleButtonClick(value: string) {
    interface calcResult{
      value : number | null;
      status: string;
    }
    interface midWayOperations{
      value : string | null;
      status: string;
    }
    switch (value) {
      case 'C':
      case 'CE':
        this.display = '';
        this.errorMode = false;
        break;
      case '⌫':
        this.display = this.display.slice(0, -1);
        break;
      case '+/-':
        const url6 = 'http://localhost:8080/api/negate';
        this.http.post<midWayOperations>(url6, this.display)
          .subscribe(result => {
            if(result.status === "Success" && result.value !== null) {
              this.display = result.value.toString();
            }
            else{
              this.showError(result.status);
            }
          });
        break;
      case 'x²':
        const url3 = 'http://localhost:8080/api/pwrTwo';
        this.http.post<midWayOperations>(url3, this.display)
          .subscribe(result => {
            if(result.status === "Success" && result.value !== null) {
              this.display = result.value.toString();
            }
            else{
              this.showError(result.status);
            }
          });
        break;
      case '√x':
        const url1 = 'http://localhost:8080/api/sqrt';
        this.http.post<midWayOperations>(url1, this.display)
          .subscribe(result => {
            if(result.status === "Success" && result.value !== null) {
              this.display = result.value.toString();
            }
            else{
              this.showError(result.status);
            }
          });
        break;
      case '1/x':
        const url4 = 'http://localhost:8080/api/recip';
        this.http.post<midWayOperations>(url4, this.display)
          .subscribe(result => {
            if(result.status === "Success" && result.value !== null) {
              this.display = result.value.toString();
            }
            else{
              this.showError(result.status);
            }
          });
        break;
      case '%':
        const url5 = 'http://localhost:8080/api/percent';
        this.http.post<midWayOperations>(url5, this.display)
          .subscribe(result => {
            if(result.status === "Success" && result.value !== null) {
              this.display = result.value.toString();
            }
            else{
              this.showError(result.status);
            }
          });
        break;
      case '=':

        const url2 = 'http://localhost:8080/api/calc';
        this.http.post<calcResult>(url2, this.display)
          .subscribe(result => {
            if(result.status === "Success" && result.value !== null) {
              this.display = this.display = parseFloat(result.value.toString()).toString();
            }
            else if(result.status === "Division by zero"){
              this.errorMode = true;
              this.display = result.status;
            }
            else{
              this.showError(result.status);
            }
        });
        break;
      default:
        this.display += value;
        break;
    }
  }

  showError(msg: string) {
    this.errorMessage = msg;
    setTimeout(() => this.errorMessage = null, 1000);
  }
}
