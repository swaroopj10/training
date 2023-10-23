import { Injectable } from '@angular/core';
import { Car } from '../models/car';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CarService {
  cars: Car[] = []

  constructor(private http: HttpClient) {
    this.url = 'http://localhost:8080/CarService/jaxrs/cars?filter=';
  }

  private url: string;

  getCars(): Observable<Car[]> {
   return this.http.get<Car[]>(this.url);
  }

  getCarsWithFilters(filter:string):Observable<Car[]>{
    return this.http.get<Car[]>(`${this.url}${filter}`)
  }
}
