import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { UnistripeTestModule } from '../../../test.module';
import { BillingCustomerComponent } from 'app/entities/billing-customer/billing-customer.component';
import { BillingCustomerService } from 'app/entities/billing-customer/billing-customer.service';
import { BillingCustomer } from 'app/shared/model/billing-customer.model';

describe('Component Tests', () => {
  describe('BillingCustomer Management Component', () => {
    let comp: BillingCustomerComponent;
    let fixture: ComponentFixture<BillingCustomerComponent>;
    let service: BillingCustomerService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UnistripeTestModule],
        declarations: [BillingCustomerComponent],
      })
        .overrideTemplate(BillingCustomerComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BillingCustomerComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BillingCustomerService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new BillingCustomer(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.billingCustomers && comp.billingCustomers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
