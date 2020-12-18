import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { ProductRatePlanPriceLinkService } from 'app/entities/product-rate-plan-price-link/product-rate-plan-price-link.service';
import { IProductRatePlanPriceLink, ProductRatePlanPriceLink } from 'app/shared/model/product-rate-plan-price-link.model';

describe('Service Tests', () => {
  describe('ProductRatePlanPriceLink Service', () => {
    let injector: TestBed;
    let service: ProductRatePlanPriceLinkService;
    let httpMock: HttpTestingController;
    let elemDefault: IProductRatePlanPriceLink;
    let expectedResult: IProductRatePlanPriceLink | IProductRatePlanPriceLink[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ProductRatePlanPriceLinkService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ProductRatePlanPriceLink(
        0,
        'AAAAAAA',
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

      it('should create a ProductRatePlanPriceLink', () => {
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

        service.create(new ProductRatePlanPriceLink()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ProductRatePlanPriceLink', () => {
        const returnedFromService = Object.assign(
          {
            stripeId: 'BBBBBB',
            stripeNickName: 'BBBBBB',
            productId: 'BBBBBB',
            productName: 'BBBBBB',
            productRatePlanId: 'BBBBBB',
            productRatePlanName: 'BBBBBB',
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

      it('should return a list of ProductRatePlanPriceLink', () => {
        const returnedFromService = Object.assign(
          {
            stripeId: 'BBBBBB',
            stripeNickName: 'BBBBBB',
            productId: 'BBBBBB',
            productName: 'BBBBBB',
            productRatePlanId: 'BBBBBB',
            productRatePlanName: 'BBBBBB',
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

      it('should delete a ProductRatePlanPriceLink', () => {
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
