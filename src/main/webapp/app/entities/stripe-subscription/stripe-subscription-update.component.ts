import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IStripeSubscription, StripeSubscription } from 'app/shared/model/stripe-subscription.model';
import { StripeSubscriptionService } from './stripe-subscription.service';

@Component({
  selector: 'jhi-stripe-subscription-update',
  templateUrl: './stripe-subscription-update.component.html',
})
export class StripeSubscriptionUpdateComponent implements OnInit {
  isSaving = false;
  migrateAtDp: any;
  updatedAtDp: any;

  editForm = this.fb.group({
    id: [],
    stripeId: [],
    stripeEmail: [],
    subscriptionId: [],
    migrateAt: [],
    updatedAt: [],
  });

  constructor(
    protected stripeSubscriptionService: StripeSubscriptionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ stripeSubscription }) => {
      this.updateForm(stripeSubscription);
    });
  }

  updateForm(stripeSubscription: IStripeSubscription): void {
    this.editForm.patchValue({
      id: stripeSubscription.id,
      stripeId: stripeSubscription.stripeId,
      stripeEmail: stripeSubscription.stripeEmail,
      subscriptionId: stripeSubscription.subscriptionId,
      migrateAt: stripeSubscription.migrateAt,
      updatedAt: stripeSubscription.updatedAt,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const stripeSubscription = this.createFromForm();
    if (stripeSubscription.id !== undefined) {
      this.subscribeToSaveResponse(this.stripeSubscriptionService.update(stripeSubscription));
    } else {
      this.subscribeToSaveResponse(this.stripeSubscriptionService.create(stripeSubscription));
    }
  }

  private createFromForm(): IStripeSubscription {
    return {
      ...new StripeSubscription(),
      id: this.editForm.get(['id'])!.value,
      stripeId: this.editForm.get(['stripeId'])!.value,
      stripeEmail: this.editForm.get(['stripeEmail'])!.value,
      subscriptionId: this.editForm.get(['subscriptionId'])!.value,
      migrateAt: this.editForm.get(['migrateAt'])!.value,
      updatedAt: this.editForm.get(['updatedAt'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStripeSubscription>>): void {
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
