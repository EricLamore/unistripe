import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { UnistripeTestModule } from '../../../test.module';
import { BillingStripeLinkDetailComponent } from 'app/entities/billing-stripe-link/billing-stripe-link-detail.component';
import { BillingStripeLink } from 'app/shared/model/billing-stripe-link.model';

describe('Component Tests', () => {
  describe('BillingStripeLink Management Detail Component', () => {
    let comp: BillingStripeLinkDetailComponent;
    let fixture: ComponentFixture<BillingStripeLinkDetailComponent>;
    const route = ({ data: of({ billingStripeLink: new BillingStripeLink(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UnistripeTestModule],
        declarations: [BillingStripeLinkDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BillingStripeLinkDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BillingStripeLinkDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load billingStripeLink on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.billingStripeLink).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
