import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { BillingCustomerService } from 'app/entities/billing-customer/billing-customer.service';
import { IBillingCustomer, BillingCustomer } from 'app/shared/model/billing-customer.model';

describe('Service Tests', () => {
  describe('BillingCustomer Service', () => {
    let injector: TestBed;
    let service: BillingCustomerService;
    let httpMock: HttpTestingController;
    let elemDefault: IBillingCustomer;
    let expectedResult: IBillingCustomer | IBillingCustomer[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BillingCustomerService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new BillingCustomer(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        false,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate
      );
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

      it('should create a BillingCustomer', () => {
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

        service.create(new BillingCustomer()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a BillingCustomer', () => {
        const returnedFromService = Object.assign(
          {
            taxNo: 'BBBBBB',
            thirdPartyAccountingCode: 'BBBBBB',
            siret: 'BBBBBB',
            ownerId: 'BBBBBB',
            isParticular: true,
            partner: true,
            partnerId: 'BBBBBB',
            customerId: 'BBBBBB',
            customerName: 'BBBBBB',
            stripeId: 'BBBBBB',
            stripeEmail: 'BBBBBB',
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

      it('should return a list of BillingCustomer', () => {
        const returnedFromService = Object.assign(
          {
            taxNo: 'BBBBBB',
            thirdPartyAccountingCode: 'BBBBBB',
            siret: 'BBBBBB',
            ownerId: 'BBBBBB',
            isParticular: true,
            partner: true,
            partnerId: 'BBBBBB',
            customerId: 'BBBBBB',
            customerName: 'BBBBBB',
            stripeId: 'BBBBBB',
            stripeEmail: 'BBBBBB',
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

      it('should delete a BillingCustomer', () => {
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
