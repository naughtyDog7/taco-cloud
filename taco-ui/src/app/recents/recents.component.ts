import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RecentService} from "./recent.service";

@Component({
  selector: 'app-recents',
  templateUrl: './recents.component.html',
  styleUrls: ['./recents.component.css']
})
export class RecentsComponent implements OnInit {

  recentTacos: any;

  constructor(private recentService: RecentService) {}

  ngOnInit(): void {
    this.recentService.getRecentTacos()
      .subscribe(data => this.recentTacos = data);
  }

}
