import { TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CarService } from './car.service';
import { Car } from '../models/car';

describe('CarService', () => {
  let service: CarService;
  let httpTestingController: HttpTestingController;
  
  let testCars: Car[] = [
    {
      "doors": 3,
      "make": "Daimler AG",
      "model": "Smart C453",
      "price": 23000,
      "year": 2023
      },
      {
      "doors": 5,
      "make": "Chrysler",
      "model": "Pacifica Hybrid",
      "price": 60000,
      "year": 2024
      }
  ];

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientTestingModule]
    });
    service = TestBed.inject(CarService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  it('should return cars', inject([CarService], fakeAsync((service: CarService) => {
    let cars: Car[] = [];
    service.getCars().subscribe(data => cars = data);
    const req = httpTestingController.expectOne('http://localhost:8080/CarService/jaxrs/cars?filter=');
    expect(req.request.method).toEqual('GET');
    req.flush(testCars);
    tick();
    expect(cars[0].make).toBe('Daimler AG');
    })));
});
