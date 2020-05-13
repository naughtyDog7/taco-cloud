import { Injectable } from '@angular/core';
import {ApiService} from "../api/api.service";

@Injectable({
  providedIn: 'root'
})
export class RecentService {

  constructor(private apiService: ApiService) { }

  getRecentTacos() {
    return this.apiService.get('/tacos/recent')
  }
}
