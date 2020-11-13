import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { UnistripeTestModule } from '../../../test.module';
import { BillingCustomerUpdateComponent } from 'app/entities/billing-customer/billing-customer-update.component';
import { BillingCustomerService } from 'app/entities/billing-customer/billing-customer.service';
import { BillingCustomer } from 'app/shared/model/billing-customer.model';

describe('Component Tests', () => {
  describe('BillingCustomer Management Update Component', () => {
    let comp: BillingCustomerUpdateComponent;
    let fixture: ComponentFixture<BillingCustomerUpdateComponent>;
    let service: BillingCustomerService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UnistripeTestModule],
        declarations: [BillingCustomerUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BillingCustomerUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BillingCustomerUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BillingCustomerService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BillingCustomer(123);
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
        const entity = new BillingCustomer();
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
