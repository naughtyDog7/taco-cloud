import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-group-box',
  templateUrl: './group-box.component.html',
  styleUrls: ['./group-box.component.css']
})
export class GroupBoxComponent implements OnInit {
  // tslint:disable-next-line:ban-types
  @Input() title: String;

  constructor() { }

  ngOnInit(): void { }

}
