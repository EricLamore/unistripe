import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { UniproxyConsoService } from 'app/entities/uniproxy-conso/uniproxy-conso.service';
import { IUniproxyConso, UniproxyConso } from 'app/shared/model/uniproxy-conso.model';
import { TYPE } from 'app/shared/model/enumerations/type.model';

describe('Service Tests', () => {
  describe('UniproxyConso Service', () => {
    let injector: TestBed;
    let service: UniproxyConsoService;
    let httpMock: HttpTestingController;
    let elemDefault: IUniproxyConso;
    let expectedResult: IUniproxyConso | IUniproxyConso[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(UniproxyConsoService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new UniproxyConso(0, 'AAAAAAA', 'AAAAAAA', 0, 0, TYPE.SIGNATURE, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a UniproxyConso', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new UniproxyConso()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a UniproxyConso', () => {
        const returnedFromService = Object.assign(
          {
            subscriptionId: 'BBBBBB',
            orgId: 'BBBBBB',
            month: 1,
            year: 1,
            type: 'BBBBBB',
            conso: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of UniproxyConso', () => {
        const returnedFromService = Object.assign(
          {
            subscriptionId: 'BBBBBB',
            orgId: 'BBBBBB',
            month: 1,
            year: 1,
            type: 'BBBBBB',
            conso: 1,
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

      it('should delete a UniproxyConso', () => {
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
