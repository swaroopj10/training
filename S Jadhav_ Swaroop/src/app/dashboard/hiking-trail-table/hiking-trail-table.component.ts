import { Component } from '@angular/core';
import { HikingTrail } from 'src/app/models/hiking-trail';
import { HikingTrailService } from 'src/app/service/hiking-trail.service';

@Component({
  selector: 'app-hiking-trail-table',
  templateUrl: './hiking-trail-table.component.html',
  styleUrls: ['./hiking-trail-table.component.css']
})
export class HikingTrailTableComponent {
  trailList: HikingTrail[] = [];
  title: string = '';
  constructor(private hikingTrailService: HikingTrailService) {};

  ngOnInit() {
    this.loadAllTrails();
  }

  loadTrailByFilter(filter:string){
    this.hikingTrailService.getTrailByFilter(filter).subscribe(data=> this.trailList=data);
    this.title = filter;
  }

  loadAllTrails(){
    this.hikingTrailService.getAllTrails().subscribe(data=> this.trailList=data);
  }

  loadTrailByReverseOrder() {
    this.hikingTrailService.getAllTrailsByOrder('this.title').subscribe(data=> this.trailList=data);
  }

  loadTrailByTop() {
    this.hikingTrailService.getAllTrailsByTop('this.title').subscribe(data=> this.trailList=data);
  }
}
