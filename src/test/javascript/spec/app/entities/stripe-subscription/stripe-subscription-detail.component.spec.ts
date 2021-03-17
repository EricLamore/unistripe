import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { UnistripeTestModule } from '../../../test.module';
import { StripeSubscriptionDetailComponent } from 'app/entities/stripe-subscription/stripe-subscription-detail.component';
import { StripeSubscription } from 'app/shared/model/stripe-subscription.model';

describe('Component Tests', () => {
  describe('StripeSubscription Management Detail Component', () => {
    let comp: StripeSubscriptionDetailComponent;
    let fixture: ComponentFixture<StripeSubscriptionDetailComponent>;
    const route = ({ data: of({ stripeSubscription: new StripeSubscription(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UnistripeTestModule],
        declarations: [StripeSubscriptionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(StripeSubscriptionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(StripeSubscriptionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load stripeSubscription on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.stripeSubscription).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
