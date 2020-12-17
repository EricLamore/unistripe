import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { UnistripeTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { ProductRatePlanPriceLinkDeleteDialogComponent } from 'app/entities/product-rate-plan-price-link/product-rate-plan-price-link-delete-dialog.component';
import { ProductRatePlanPriceLinkService } from 'app/entities/product-rate-plan-price-link/product-rate-plan-price-link.service';

describe('Component Tests', () => {
  describe('ProductRatePlanPriceLink Management Delete Component', () => {
    let comp: ProductRatePlanPriceLinkDeleteDialogComponent;
    let fixture: ComponentFixture<ProductRatePlanPriceLinkDeleteDialogComponent>;
    let service: ProductRatePlanPriceLinkService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UnistripeTestModule],
        declarations: [ProductRatePlanPriceLinkDeleteDialogComponent],
      })
        .overrideTemplate(ProductRatePlanPriceLinkDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProductRatePlanPriceLinkDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProductRatePlanPriceLinkService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
