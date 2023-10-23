import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing'

import { EtfService } from './etf.service';
import { Etf } from '../models/etf';

describe('EtfService', () => {
  let service: EtfService;
  let httpTestingController: HttpTestingController;
  const testEtfs:Etf[]=[
    {
      "Ticker": "BAR",
      "Fund_Name": "GraniteShares Gold Trust",
      "Issuer": "GraniteShares",
      "AUM_bil": 0.877,
      "Expense_Ratio": 0.0017,
      "ThreeMoTR": 0.0795,
      "Segment": "Commodities: Precious Metals Gold"
      },
      {
      "Ticker": "DBC",
      "Fund_Name": "Invesco DB Commodity Index Tracking Fund",
      "Issuer": "Invesco",
      "AUM_bil": 0.769,
      "Expense_Ratio": 0.0089,
      "ThreeMoTR": -0.2505,
      "Segment": "Commodities: Broad Market"
      }
  ]

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientTestingModule]
    });
    service = TestBed.inject(EtfService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
