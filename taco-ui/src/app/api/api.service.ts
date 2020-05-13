import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../model/user";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private resourceServerUrl = "http://localhost:8081/api";

  constructor(private http: HttpClient) { }


  // getTest() {
  //   return this.http.get('https://jsonplaceholder.typicode.com/users');
  // }

  get<T>(path: String) {
    return this.http.get<T>(this.resourceServerUrl + path);
  }

  post<T>(path: String, body: any, options: any) {
    return this.http.post<T>(this.resourceServerUrl + path, body, options);
  }
}
