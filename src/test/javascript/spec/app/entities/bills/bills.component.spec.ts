import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { UnistripeTestModule } from '../../../test.module';
import { BillsComponent } from 'app/entities/bills/bills.component';
import { BillsService } from 'app/entities/bills/bills.service';
import { Bills } from 'app/shared/model/bills.model';

describe('Component Tests', () => {
  describe('Bills Management Component', () => {
    let comp: BillsComponent;
    let fixture: ComponentFixture<BillsComponent>;
    let service: BillsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UnistripeTestModule],
        declarations: [BillsComponent],
      })
        .overrideTemplate(BillsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BillsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BillsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Bills(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.bills && comp.bills[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
