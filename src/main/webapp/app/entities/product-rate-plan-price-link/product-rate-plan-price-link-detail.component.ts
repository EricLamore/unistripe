import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProductRatePlanPriceLink } from 'app/shared/model/product-rate-plan-price-link.model';

@Component({
  selector: 'jhi-product-rate-plan-price-link-detail',
  templateUrl: './product-rate-plan-price-link-detail.component.html',
})
export class ProductRatePlanPriceLinkDetailComponent implements OnInit {
  productRatePlanPriceLink: IProductRatePlanPriceLink | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ productRatePlanPriceLink }) => (this.productRatePlanPriceLink = productRatePlanPriceLink));
  }

  previousState(): void {
    window.history.back();
  }
}
