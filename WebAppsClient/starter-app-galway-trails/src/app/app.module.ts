import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';

import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HikingTrailTableComponent } from './dashboard/hiking-trail-table/hiking-trail-table.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    HikingTrailTableComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
