import { TestBed } from '@angular/core/testing';
import { AppComponent } from './app.component';
import jasmine from 'jasmine';
import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  template: ''
})
export class MockDashboardComponent {};

describe('AppComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [
        AppComponent,
        MockDashboardComponent
      ],
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'starter-app-galway-trails'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('starter-app-galway-trails');
  });
});
