import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { UnistripeTestModule } from '../../../test.module';
import { BillingStripeLinkUpdateComponent } from 'app/entities/billing-stripe-link/billing-stripe-link-update.component';
import { BillingStripeLinkService } from 'app/entities/billing-stripe-link/billing-stripe-link.service';
import { BillingStripeLink } from 'app/shared/model/billing-stripe-link.model';

describe('Component Tests', () => {
  describe('BillingStripeLink Management Update Component', () => {
    let comp: BillingStripeLinkUpdateComponent;
    let fixture: ComponentFixture<BillingStripeLinkUpdateComponent>;
    let service: BillingStripeLinkService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UnistripeTestModule],
        declarations: [BillingStripeLinkUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BillingStripeLinkUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BillingStripeLinkUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BillingStripeLinkService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BillingStripeLink(123);
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
        const entity = new BillingStripeLink();
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
