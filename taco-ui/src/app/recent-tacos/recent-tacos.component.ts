import {Component, Injectable, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-recent-tacos',
  templateUrl: './recent-tacos.component.html',
  styleUrls: ['./recent-tacos.component.css']
})
@Injectable()
export class RecentTacosComponent implements OnInit {

  recentTacos: any;

  constructor(private httpClient: HttpClient) { }

  ngOnInit(): void {
    this.httpClient.get('http://localhost:8081/design/recent')
      .subscribe(data => this.recentTacos = data);
  }

}
