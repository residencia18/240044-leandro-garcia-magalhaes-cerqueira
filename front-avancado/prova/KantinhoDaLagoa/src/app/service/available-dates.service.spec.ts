import { TestBed } from '@angular/core/testing';

import { AvailableDatesService } from './available-dates.service';

describe('AvailableDatesService', () => {
  let service: AvailableDatesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AvailableDatesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
