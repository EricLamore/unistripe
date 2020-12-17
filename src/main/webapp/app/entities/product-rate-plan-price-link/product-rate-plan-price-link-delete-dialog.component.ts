import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProductRatePlanPriceLink } from 'app/shared/model/product-rate-plan-price-link.model';
import { ProductRatePlanPriceLinkService } from './product-rate-plan-price-link.service';

@Component({
  templateUrl: './product-rate-plan-price-link-delete-dialog.component.html',
})
export class ProductRatePlanPriceLinkDeleteDialogComponent {
  productRatePlanPriceLink?: IProductRatePlanPriceLink;

  constructor(
    protected productRatePlanPriceLinkService: ProductRatePlanPriceLinkService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.productRatePlanPriceLinkService.delete(id).subscribe(() => {
      this.eventManager.broadcast('productRatePlanPriceLinkListModification');
      this.activeModal.close();
    });
  }
}
