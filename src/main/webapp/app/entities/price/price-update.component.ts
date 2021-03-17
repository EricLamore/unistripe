import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPrice, Price } from 'app/shared/model/price.model';
import { PriceService } from './price.service';
import { IStripeSubscription } from 'app/shared/model/stripe-subscription.model';
import { StripeSubscriptionService } from 'app/entities/stripe-subscription/stripe-subscription.service';

@Component({
  selector: 'jhi-price-update',
  templateUrl: './price-update.component.html',
})
export class PriceUpdateComponent implements OnInit {
  isSaving = false;
  stripesubscriptions: IStripeSubscription[] = [];

  editForm = this.fb.group({
    id: [],
    stripeId: [],
    nickname: [],
    productId: [],
    subscription: [],
  });

  constructor(
    protected priceService: PriceService,
    protected stripeSubscriptionService: StripeSubscriptionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ price }) => {
      this.updateForm(price);

      this.stripeSubscriptionService
        .query()
        .subscribe((res: HttpResponse<IStripeSubscription[]>) => (this.stripesubscriptions = res.body || []));
    });
  }

  updateForm(price: IPrice): void {
    this.editForm.patchValue({
      id: price.id,
      stripeId: price.stripeId,
      nickname: price.nickname,
      productId: price.productId,
      subscription: price.subscription,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const price = this.createFromForm();
    if (price.id !== undefined) {
      this.subscribeToSaveResponse(this.priceService.update(price));
    } else {
      this.subscribeToSaveResponse(this.priceService.create(price));
    }
  }

  private createFromForm(): IPrice {
    return {
      ...new Price(),
      id: this.editForm.get(['id'])!.value,
      stripeId: this.editForm.get(['stripeId'])!.value,
      nickname: this.editForm.get(['nickname'])!.value,
      productId: this.editForm.get(['productId'])!.value,
      subscription: this.editForm.get(['subscription'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPrice>>): void {
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

  trackById(index: number, item: IStripeSubscription): any {
    return item.id;
  }
}
