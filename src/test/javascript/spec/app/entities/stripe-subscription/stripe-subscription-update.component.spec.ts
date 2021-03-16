import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { UnistripeTestModule } from '../../../test.module';
import { StripeSubscriptionUpdateComponent } from 'app/entities/stripe-subscription/stripe-subscription-update.component';
import { StripeSubscriptionService } from 'app/entities/stripe-subscription/stripe-subscription.service';
import { StripeSubscription } from 'app/shared/model/stripe-subscription.model';

describe('Component Tests', () => {
  describe('StripeSubscription Management Update Component', () => {
    let comp: StripeSubscriptionUpdateComponent;
    let fixture: ComponentFixture<StripeSubscriptionUpdateComponent>;
    let service: StripeSubscriptionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UnistripeTestModule],
        declarations: [StripeSubscriptionUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(StripeSubscriptionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(StripeSubscriptionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(StripeSubscriptionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new StripeSubscription(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new StripeSubscription();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
