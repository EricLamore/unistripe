import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { EventUsesService } from 'app/entities/event-uses/event-uses.service';
import { IEventUses, EventUses } from 'app/shared/model/event-uses.model';
import { StatusType } from 'app/shared/model/enumerations/status-type.model';

describe('Service Tests', () => {
  describe('EventUses Service', () => {
    let injector: TestBed;
    let service: EventUsesService;
    let httpMock: HttpTestingController;
    let elemDefault: IEventUses;
    let expectedResult: IEventUses | IEventUses[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EventUsesService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new EventUses(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 0, StatusType.NONE, 'AAAAAAA', 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a EventUses', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new EventUses()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EventUses', () => {
        const returnedFromService = Object.assign(
          {
            cusotmerId: 'BBBBBB',
            customerName: 'BBBBBB',
            subscriptionId: 'BBBBBB',
            subscriptionName: 'BBBBBB',
            productId: 'BBBBBB',
            productName: 'BBBBBB',
            month: 1,
            year: 1,
            status: 'BBBBBB',
            error: 'BBBBBB',
            total: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EventUses', () => {
        const returnedFromService = Object.assign(
          {
            cusotmerId: 'BBBBBB',
            customerName: 'BBBBBB',
            subscriptionId: 'BBBBBB',
            subscriptionName: 'BBBBBB',
            productId: 'BBBBBB',
            productName: 'BBBBBB',
            month: 1,
            year: 1,
            status: 'BBBBBB',
            error: 'BBBBBB',
            total: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a EventUses', () => {
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
