import { Injectable } from '@angular/core';
import { HikingTrail } from '../models/hiking-trail';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class HikingTrailService {

  constructor(private http:HttpClient) { }
  baseUrl="http://localhost:8080/trails?sortBy="
  

  getAllTrails():Observable<HikingTrail[]>{
    return this.http.get<HikingTrail[]>(this.baseUrl)
  }
  
  getTrailByFilter(filter:string):Observable<HikingTrail[]>{
    return this.http.get<HikingTrail[]>(`http://localhost:8080/trails?sortBy=${filter}`)
  }

  getAllTrailsByOrder(filter:string):Observable<HikingTrail[]>{
    return this.http.get<HikingTrail[]>(`http://localhost:8080/trails?sortBy=${filter}/&sortAsc=false`)
  }

  getAllTrailsByTop(filter:string):Observable<HikingTrail[]>{
    return this.http.get<HikingTrail[]>(`http://localhost:8080/trails?sortBy=${filter}/&howMany=5`)
  }
}
