import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { UnistripeTestModule } from '../../../test.module';
import { ProductRatePlanPriceLinkComponent } from 'app/entities/product-rate-plan-price-link/product-rate-plan-price-link.component';
import { ProductRatePlanPriceLinkService } from 'app/entities/product-rate-plan-price-link/product-rate-plan-price-link.service';
import { ProductRatePlanPriceLink } from 'app/shared/model/product-rate-plan-price-link.model';

describe('Component Tests', () => {
  describe('ProductRatePlanPriceLink Management Component', () => {
    let comp: ProductRatePlanPriceLinkComponent;
    let fixture: ComponentFixture<ProductRatePlanPriceLinkComponent>;
    let service: ProductRatePlanPriceLinkService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UnistripeTestModule],
        declarations: [ProductRatePlanPriceLinkComponent],
      })
        .overrideTemplate(ProductRatePlanPriceLinkComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProductRatePlanPriceLinkComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProductRatePlanPriceLinkService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ProductRatePlanPriceLink(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.productRatePlanPriceLinks && comp.productRatePlanPriceLinks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
