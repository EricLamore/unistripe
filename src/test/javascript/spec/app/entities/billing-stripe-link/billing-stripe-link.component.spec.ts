import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { UnistripeTestModule } from '../../../test.module';
import { BillingStripeLinkComponent } from 'app/entities/billing-stripe-link/billing-stripe-link.component';
import { BillingStripeLinkService } from 'app/entities/billing-stripe-link/billing-stripe-link.service';
import { BillingStripeLink } from 'app/shared/model/billing-stripe-link.model';

describe('Component Tests', () => {
  describe('BillingStripeLink Management Component', () => {
    let comp: BillingStripeLinkComponent;
    let fixture: ComponentFixture<BillingStripeLinkComponent>;
    let service: BillingStripeLinkService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UnistripeTestModule],
        declarations: [BillingStripeLinkComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: of({
                defaultSort: 'id,asc',
              }),
              queryParamMap: of(
                convertToParamMap({
                  page: '1',
                  size: '1',
                  sort: 'id,desc',
                })
              ),
            },
          },
        ],
      })
        .overrideTemplate(BillingStripeLinkComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BillingStripeLinkComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BillingStripeLinkService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new BillingStripeLink(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.billingStripeLinks && comp.billingStripeLinks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new BillingStripeLink(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.billingStripeLinks && comp.billingStripeLinks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
