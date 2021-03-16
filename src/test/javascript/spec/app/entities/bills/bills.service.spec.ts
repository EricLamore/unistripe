import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { BillsService } from 'app/entities/bills/bills.service';
import { IBills, Bills } from 'app/shared/model/bills.model';

describe('Service Tests', () => {
  describe('Bills Service', () => {
    let injector: TestBed;
    let service: BillsService;
    let httpMock: HttpTestingController;
    let elemDefault: IBills;
    let expectedResult: IBills | IBills[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BillsService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Bills(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        currentDate,
        currentDate,
        currentDate,
        currentDate,
        currentDate,
        0,
        0,
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            periodStart: currentDate.format(DATE_TIME_FORMAT),
            periodEnd: currentDate.format(DATE_TIME_FORMAT),
            created: currentDate.format(DATE_TIME_FORMAT),
            send: currentDate.format(DATE_TIME_FORMAT),
            dueDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Bills', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            periodStart: currentDate.format(DATE_TIME_FORMAT),
            periodEnd: currentDate.format(DATE_TIME_FORMAT),
            created: currentDate.format(DATE_TIME_FORMAT),
            send: currentDate.format(DATE_TIME_FORMAT),
            dueDate: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            periodStart: currentDate,
            periodEnd: currentDate,
            created: currentDate,
            send: currentDate,
            dueDate: currentDate,
          },
          returnedFromService
        );

        service.create(new Bills()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Bills', () => {
        const returnedFromService = Object.assign(
          {
            customerId: 'BBBBBB',
            customerName: 'BBBBBB',
            customerEmail: 'BBBBBB',
            year: 1,
            month: 1,
            periodStart: currentDate.format(DATE_TIME_FORMAT),
            periodEnd: currentDate.format(DATE_TIME_FORMAT),
            created: currentDate.format(DATE_TIME_FORMAT),
            send: currentDate.format(DATE_TIME_FORMAT),
            dueDate: currentDate.format(DATE_TIME_FORMAT),
            amountDue: 1,
            total: 1,
            tax: 1,
            totalDiscountAmounts: 1,
            totalTaxAmounts: 1,
            url: 'BBBBBB',
            pdfUrl: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            periodStart: currentDate,
            periodEnd: currentDate,
            created: currentDate,
            send: currentDate,
            dueDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Bills', () => {
        const returnedFromService = Object.assign(
          {
            customerId: 'BBBBBB',
            customerName: 'BBBBBB',
            customerEmail: 'BBBBBB',
            year: 1,
            month: 1,
            periodStart: currentDate.format(DATE_TIME_FORMAT),
            periodEnd: currentDate.format(DATE_TIME_FORMAT),
            created: currentDate.format(DATE_TIME_FORMAT),
            send: currentDate.format(DATE_TIME_FORMAT),
            dueDate: currentDate.format(DATE_TIME_FORMAT),
            amountDue: 1,
            total: 1,
            tax: 1,
            totalDiscountAmounts: 1,
            totalTaxAmounts: 1,
            url: 'BBBBBB',
            pdfUrl: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            periodStart: currentDate,
            periodEnd: currentDate,
            created: currentDate,
            send: currentDate,
            dueDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Bills', () => {
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
