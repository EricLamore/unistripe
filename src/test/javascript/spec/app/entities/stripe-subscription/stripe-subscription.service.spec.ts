import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { StripeSubscriptionService } from 'app/entities/stripe-subscription/stripe-subscription.service';
import { IStripeSubscription, StripeSubscription } from 'app/shared/model/stripe-subscription.model';

describe('Service Tests', () => {
  describe('StripeSubscription Service', () => {
    let injector: TestBed;
    let service: StripeSubscriptionService;
    let httpMock: HttpTestingController;
    let elemDefault: IStripeSubscription;
    let expectedResult: IStripeSubscription | IStripeSubscription[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(StripeSubscriptionService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new StripeSubscription(0, 'AAAAAAA', 'AAAAAAA', 0, currentDate, currentDate);
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

      it('should create a StripeSubscription', () => {
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

        service.create(new StripeSubscription()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a StripeSubscription', () => {
        const returnedFromService = Object.assign(
          {
            stripeId: 'BBBBBB',
            stripeEmail: 'BBBBBB',
            taxeRate: 1,
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

      it('should return a list of StripeSubscription', () => {
        const returnedFromService = Object.assign(
          {
            stripeId: 'BBBBBB',
            stripeEmail: 'BBBBBB',
            taxeRate: 1,
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

      it('should delete a StripeSubscription', () => {
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
