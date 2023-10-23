import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HikingTrailTableComponent } from './hiking-trail-table.component';
import { HikingTrail } from 'src/app/models/hiking-trail';
import { HikingTrailService } from 'src/app/service/hiking-trail.service';

describe('HikingTrailTableComponent', () => {
  let component: HikingTrailTableComponent;
  let fixture: ComponentFixture<HikingTrailTableComponent>;

  beforeEach(async () => {
    let mockTrail: HikingTrail[] = [
      {
        "name": "Caher River Loop",
        "length": 14.7,
        "timeToComplete": 4,
        "drivingTime": 60,
        "difficulty": "Moderate loop trail",
        "comments": "It's filled with gorgeous wildlife and stunning views, with a magnificent view of Galway Bay from the summit"
        },
        {
        "name": "Cahermurphy Loop Trail",
        "length": 9.7,
        "timeToComplete": 3,
        "drivingTime": 58,
        "difficulty": "Moderate loop trail",
        "comments": "A great hiking trek that goes through many different terrains, including along the river, along the lake, and through the forest!"
        },
        {
        "name": "Cashel Hill",
        "length": 4.8,
        "timeToComplete": 2,
        "drivingTime": 63,
        "difficulty": "Moderate loop trail",
        "comments": "A scenic hiking and walking path that is famous for its wildlife, mountainscapes, and lakes that can be seen from the trail's summit"
        },
    ];
    const trailService = jasmine.createSpyObj("TrailService",['getAllTrails'])
    trailService.getAllTrails.and.returnValue(of(mockTrail));
    await TestBed.configureTestingModule({
      declarations: [ HikingTrailTableComponent ],
      providers:[{useValue:trailService,provide:HikingTrailService}]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HikingTrailTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should contain data',()=>{
    let compiled= fixture.nativeElement;
    const rows = compiled.querySelectorAll('table')
    expect(rows.length).toBeGreaterThanOrEqual(1);
  })

  it('table should contain correct first element',()=>{
    let compiled=fixture.nativeElement;
    expect(compiled.querySelector('tbody tr').cells[0].textContent).toEqual('Caher River Loop')
  })

  it('table should contain table header',()=>{
    let compiled=fixture.nativeElement;
    expect(compiled.querySelector('thead tr').cells[0].textContent).toEqual('Name')
  })

  it('table should contain buttons',()=>{
    let compiled=fixture.nativeElement;
    expect(compiled.querySelector('button').textContent).toEqual('Get All Trails')
  })

  it('table should contain sub header',()=>{
    let compiled=fixture.nativeElement;
    expect(compiled.querySelector('h2').textContent).toEqual('Additional Filters')
  })

});
