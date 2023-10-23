import { Component } from '@angular/core';
import { Car } from '../models/car';
import { CarService } from '../cars/car.service';

@Component({
  selector: 'app-car-list',
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.css']
})
export class CarListComponent {
  constructor(private carService : CarService) {}

  loadAllCars() {
    this.carService.getCars().subscribe({
      next : (data) => {
        this.cars = data;
    },})
  }

  loadCarsByFilter(filter:string){
    this.carService.getCarsWithFilters(filter).subscribe(cars=>{
      this.cars = cars;
    })}

  ngOnInit() {
    this.loadAllCars();
  }
  cars: Car[] = []
}
