import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { BillingStripeLinkService } from 'app/entities/billing-stripe-link/billing-stripe-link.service';
import { IBillingStripeLink, BillingStripeLink } from 'app/shared/model/billing-stripe-link.model';

describe('Service Tests', () => {
  describe('BillingStripeLink Service', () => {
    let injector: TestBed;
    let service: BillingStripeLinkService;
    let httpMock: HttpTestingController;
    let elemDefault: IBillingStripeLink;
    let expectedResult: IBillingStripeLink | IBillingStripeLink[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BillingStripeLinkService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new BillingStripeLink(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate, currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            migrateAt: currentDate.format(DATE_FORMAT),
            updatedAt: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a BillingStripeLink', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            migrateAt: currentDate.format(DATE_FORMAT),
            updatedAt: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            migrateAt: currentDate,
            updatedAt: currentDate,
          },
          returnedFromService
        );

        service.create(new BillingStripeLink()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a BillingStripeLink', () => {
        const returnedFromService = Object.assign(
          {
            stripeId: 'BBBBBB',
            stripeEmail: 'BBBBBB',
            customerId: 'BBBBBB',
            customerName: 'BBBBBB',
            migrateAt: currentDate.format(DATE_FORMAT),
            updatedAt: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            migrateAt: currentDate,
            updatedAt: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of BillingStripeLink', () => {
        const returnedFromService = Object.assign(
          {
            stripeId: 'BBBBBB',
            stripeEmail: 'BBBBBB',
            customerId: 'BBBBBB',
            customerName: 'BBBBBB',
            migrateAt: currentDate.format(DATE_FORMAT),
            updatedAt: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            migrateAt: currentDate,
            updatedAt: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a BillingStripeLink', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
