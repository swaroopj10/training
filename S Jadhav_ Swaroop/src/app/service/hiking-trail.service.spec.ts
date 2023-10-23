import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of, throwError } from 'rxjs';
import { FormsModule } from '@angular/forms';
import { HikingTrailTableComponent } from '../dashboard/hiking-trail-table/hiking-trail-table.component';
import { HikingTrail } from '../models/hiking-trail';
import { HikingTrailService } from './hiking-trail.service';

describe('TrialListComponent', () => {
  let component: HikingTrailTableComponent;
  let fixture: ComponentFixture<HikingTrailTableComponent>;
  const mockedTrails:HikingTrail[]=[{
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
    }]
  beforeEach(async () => {
    let trailService:any = jasmine.createSpyObj('TrailService',['getAllTrails']);
    trailService.getAllTrails.and.returnValue(of(mockedTrails));
    await TestBed.configureTestingModule({
      declarations: [ HikingTrailTableComponent ],
      providers:[{provide:HikingTrailService,useValue:trailService}],
      imports:[FormsModule]
    })
    .compileComponents();
    fixture = TestBed.createComponent(HikingTrailTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('it should contain a table',()=>{
    const compiled = fixture.debugElement.nativeElement;
    const table = compiled.querySelector('table');
    console.log(table);
    expect(table.rows.length).toBe(3);
    expect(table.rows[0].cells[0].textContent).toBe('Name');
  })

  async function mockTrailService(trailOb:Observable<any>) {
    const mockTrailServicee  = jasmine.createSpyObj('TrailService', ['getAllTrails']); 
    mockTrailServicee.getAllTrails.and.returnValue(of(trailOb));
    async () => {await TestBed.configureTestingModule({
      declarations: [ HikingTrailTableComponent ],
      providers:[{provide: HikingTrailService, useValue: mockTrailServicee}]
    }).compileComponents();
    fixture = TestBed.createComponent(HikingTrailTableComponent);
    fixture.detectChanges();
   };
  }

   it('negative test case', async () => 
    {  
      const errorOb = throwError(()=>new Error('mock service failed'));
      const mockTrails: HikingTrail[] = [] ;
      await mockTrailService(of(mockedTrails)); 
      const htmlE = fixture.debugElement.nativeElement; 
      const table = htmlE.querySelector('table.tr');
      expect(table).toBeFalsy();
    })
});