import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { UnistripeTestModule } from '../../../test.module';
import { BillingCustomerDetailComponent } from 'app/entities/billing-customer/billing-customer-detail.component';
import { BillingCustomer } from 'app/shared/model/billing-customer.model';

describe('Component Tests', () => {
  describe('BillingCustomer Management Detail Component', () => {
    let comp: BillingCustomerDetailComponent;
    let fixture: ComponentFixture<BillingCustomerDetailComponent>;
    const route = ({ data: of({ billingCustomer: new BillingCustomer(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UnistripeTestModule],
        declarations: [BillingCustomerDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BillingCustomerDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BillingCustomerDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load billingCustomer on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.billingCustomer).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
