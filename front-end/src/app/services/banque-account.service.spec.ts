import { TestBed } from '@angular/core/testing';

import { BanqueAccountService } from './banque-account.service';

describe('BanqueAccountService', () => {
  let service: BanqueAccountService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BanqueAccountService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
