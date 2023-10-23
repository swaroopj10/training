import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarListComponent } from './car-list.component';
import { Car } from '../models/car';
import { of } from 'rxjs';
import { CarService } from '../cars/car.service';

describe('CarListComponent', () => {
  let component: CarListComponent;
  let fixture: ComponentFixture<CarListComponent>;
  
  const testCars: Car[] = [
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

  let mockCarService = jasmine.createSpyObj('CarService', ['getCars']);
  mockCarService.getCars.and.returnValue(of(testCars));

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CarListComponent ],
      providers: [
        { provide: CarService, useValue: mockCarService }
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CarListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have title', () => {
    const compiled = fixture.debugElement.nativeElement;
    const title = compiled.querySelector('h1');
    expect(title.textContent).toBe('Cars List');
  })

  it('should have table entry', () => {
    const compiled = fixture.debugElement.nativeElement;
    const table = compiled.querySelector('table');
    expect(table.rows.length).toBeGreaterThan(1);
  })

  it('should retrieve cars from the service', () => {
    expect(component.cars.length).toBeGreaterThan(1);
  });

  it('should have the correct first item', () => {
    expect(component.cars[0].make).toBe('Daimler AG');
  })
});
