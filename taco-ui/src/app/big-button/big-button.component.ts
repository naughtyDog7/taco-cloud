import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-big-button',
  templateUrl: './big-button.component.html',
  styleUrls: ['./big-button.component.css']
})
export class BigButtonComponent implements OnInit {
  @Input() label: String;

  constructor() { }

  ngOnInit(): void {
  }

}
