import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IProductRatePlanPriceLink } from 'app/shared/model/product-rate-plan-price-link.model';
import { ProductRatePlanPriceLinkService } from './product-rate-plan-price-link.service';
import { ProductRatePlanPriceLinkDeleteDialogComponent } from './product-rate-plan-price-link-delete-dialog.component';

@Component({
  selector: 'jhi-product-rate-plan-price-link',
  templateUrl: './product-rate-plan-price-link.component.html',
})
export class ProductRatePlanPriceLinkComponent implements OnInit, OnDestroy {
  productRatePlanPriceLinks?: IProductRatePlanPriceLink[];
  eventSubscriber?: Subscription;

  constructor(
    protected productRatePlanPriceLinkService: ProductRatePlanPriceLinkService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.productRatePlanPriceLinkService
      .query()
      .subscribe((res: HttpResponse<IProductRatePlanPriceLink[]>) => (this.productRatePlanPriceLinks = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInProductRatePlanPriceLinks();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IProductRatePlanPriceLink): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInProductRatePlanPriceLinks(): void {
    this.eventSubscriber = this.eventManager.subscribe('productRatePlanPriceLinkListModification', () => this.loadAll());
  }

  delete(productRatePlanPriceLink: IProductRatePlanPriceLink): void {
    const modalRef = this.modalService.open(ProductRatePlanPriceLinkDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.productRatePlanPriceLink = productRatePlanPriceLink;
  }
}
