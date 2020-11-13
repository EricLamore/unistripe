import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { CustomerOrganizationLinkService } from 'app/entities/customer-organization-link/customer-organization-link.service';
import { ICustomerOrganizationLink, CustomerOrganizationLink } from 'app/shared/model/customer-organization-link.model';

describe('Service Tests', () => {
  describe('CustomerOrganizationLink Service', () => {
    let injector: TestBed;
    let service: CustomerOrganizationLinkService;
    let httpMock: HttpTestingController;
    let elemDefault: ICustomerOrganizationLink;
    let expectedResult: ICustomerOrganizationLink | ICustomerOrganizationLink[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CustomerOrganizationLinkService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new CustomerOrganizationLink(0, 'AAAAAAA', 'AAAAAAA', currentDate, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            organisationRegisterDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a CustomerOrganizationLink', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            organisationRegisterDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            organisationRegisterDate: currentDate,
          },
          returnedFromService
        );

        service.create(new CustomerOrganizationLink()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a CustomerOrganizationLink', () => {
        const returnedFromService = Object.assign(
          {
            organisationId: 'BBBBBB',
            organisationName: 'BBBBBB',
            organisationRegisterDate: currentDate.format(DATE_FORMAT),
            customerId: 'BBBBBB',
            customerName: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            organisationRegisterDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of CustomerOrganizationLink', () => {
        const returnedFromService = Object.assign(
          {
            organisationId: 'BBBBBB',
            organisationName: 'BBBBBB',
            organisationRegisterDate: currentDate.format(DATE_FORMAT),
            customerId: 'BBBBBB',
            customerName: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            organisationRegisterDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a CustomerOrganizationLink', () => {
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
