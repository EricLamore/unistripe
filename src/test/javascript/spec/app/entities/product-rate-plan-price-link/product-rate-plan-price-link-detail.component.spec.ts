import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { UnistripeTestModule } from '../../../test.module';
import { ProductRatePlanPriceLinkDetailComponent } from 'app/entities/product-rate-plan-price-link/product-rate-plan-price-link-detail.component';
import { ProductRatePlanPriceLink } from 'app/shared/model/product-rate-plan-price-link.model';

describe('Component Tests', () => {
  describe('ProductRatePlanPriceLink Management Detail Component', () => {
    let comp: ProductRatePlanPriceLinkDetailComponent;
    let fixture: ComponentFixture<ProductRatePlanPriceLinkDetailComponent>;
    const route = ({ data: of({ productRatePlanPriceLink: new ProductRatePlanPriceLink(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UnistripeTestModule],
        declarations: [ProductRatePlanPriceLinkDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ProductRatePlanPriceLinkDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProductRatePlanPriceLinkDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load productRatePlanPriceLink on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.productRatePlanPriceLink).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
