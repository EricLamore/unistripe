import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProductRatePlanPriceLink, ProductRatePlanPriceLink } from 'app/shared/model/product-rate-plan-price-link.model';
import { ProductRatePlanPriceLinkService } from './product-rate-plan-price-link.service';

@Component({
  selector: 'jhi-product-rate-plan-price-link-update',
  templateUrl: './product-rate-plan-price-link-update.component.html',
})
export class ProductRatePlanPriceLinkUpdateComponent implements OnInit {
  isSaving = false;
  migrateAtDp: any;
  updatedAtDp: any;

  editForm = this.fb.group({
    id: [],
    stripeId: [],
    stripeNickName: [],
    productRatePlanId: [],
    productRatePlanName: [],
    migrateAt: [],
    updatedAt: [],
  });

  constructor(
    protected productRatePlanPriceLinkService: ProductRatePlanPriceLinkService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ productRatePlanPriceLink }) => {
      this.updateForm(productRatePlanPriceLink);
    });
  }

  updateForm(productRatePlanPriceLink: IProductRatePlanPriceLink): void {
    this.editForm.patchValue({
      id: productRatePlanPriceLink.id,
      stripeId: productRatePlanPriceLink.stripeId,
      stripeNickName: productRatePlanPriceLink.stripeNickName,
      productRatePlanId: productRatePlanPriceLink.productRatePlanId,
      productRatePlanName: productRatePlanPriceLink.productRatePlanName,
      migrateAt: productRatePlanPriceLink.migrateAt,
      updatedAt: productRatePlanPriceLink.updatedAt,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const productRatePlanPriceLink = this.createFromForm();
    if (productRatePlanPriceLink.id !== undefined) {
      this.subscribeToSaveResponse(this.productRatePlanPriceLinkService.update(productRatePlanPriceLink));
    } else {
      this.subscribeToSaveResponse(this.productRatePlanPriceLinkService.create(productRatePlanPriceLink));
    }
  }

  private createFromForm(): IProductRatePlanPriceLink {
    return {
      ...new ProductRatePlanPriceLink(),
      id: this.editForm.get(['id'])!.value,
      stripeId: this.editForm.get(['stripeId'])!.value,
      stripeNickName: this.editForm.get(['stripeNickName'])!.value,
      productRatePlanId: this.editForm.get(['productRatePlanId'])!.value,
      productRatePlanName: this.editForm.get(['productRatePlanName'])!.value,
      migrateAt: this.editForm.get(['migrateAt'])!.value,
      updatedAt: this.editForm.get(['updatedAt'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProductRatePlanPriceLink>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}