import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { UnistripeTestModule } from '../../../test.module';
import { ProductRatePlanPriceLinkUpdateComponent } from 'app/entities/product-rate-plan-price-link/product-rate-plan-price-link-update.component';
import { ProductRatePlanPriceLinkService } from 'app/entities/product-rate-plan-price-link/product-rate-plan-price-link.service';
import { ProductRatePlanPriceLink } from 'app/shared/model/product-rate-plan-price-link.model';

describe('Component Tests', () => {
  describe('ProductRatePlanPriceLink Management Update Component', () => {
    let comp: ProductRatePlanPriceLinkUpdateComponent;
    let fixture: ComponentFixture<ProductRatePlanPriceLinkUpdateComponent>;
    let service: ProductRatePlanPriceLinkService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UnistripeTestModule],
        declarations: [ProductRatePlanPriceLinkUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ProductRatePlanPriceLinkUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProductRatePlanPriceLinkUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProductRatePlanPriceLinkService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProductRatePlanPriceLink(123);
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
        const entity = new ProductRatePlanPriceLink();
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
