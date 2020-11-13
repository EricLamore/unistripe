import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBillingStripeLink, BillingStripeLink } from 'app/shared/model/billing-stripe-link.model';
import { BillingStripeLinkService } from './billing-stripe-link.service';

@Component({
  selector: 'jhi-billing-stripe-link-update',
  templateUrl: './billing-stripe-link-update.component.html',
})
export class BillingStripeLinkUpdateComponent implements OnInit {
  isSaving = false;
  migrateAtDp: any;
  updatedAtDp: any;

  editForm = this.fb.group({
    id: [],
    stripeId: [],
    stripeEmail: [],
    customerId: [],
    customerName: [],
    migrateAt: [],
    updatedAt: [],
  });

  constructor(
    protected billingStripeLinkService: BillingStripeLinkService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ billingStripeLink }) => {
      this.updateForm(billingStripeLink);
    });
  }

  updateForm(billingStripeLink: IBillingStripeLink): void {
    this.editForm.patchValue({
      id: billingStripeLink.id,
      stripeId: billingStripeLink.stripeId,
      stripeEmail: billingStripeLink.stripeEmail,
      customerId: billingStripeLink.customerId,
      customerName: billingStripeLink.customerName,
      migrateAt: billingStripeLink.migrateAt,
      updatedAt: billingStripeLink.updatedAt,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const billingStripeLink = this.createFromForm();
    if (billingStripeLink.id !== undefined) {
      this.subscribeToSaveResponse(this.billingStripeLinkService.update(billingStripeLink));
    } else {
      this.subscribeToSaveResponse(this.billingStripeLinkService.create(billingStripeLink));
    }
  }

  private createFromForm(): IBillingStripeLink {
    return {
      ...new BillingStripeLink(),
      id: this.editForm.get(['id'])!.value,
      stripeId: this.editForm.get(['stripeId'])!.value,
      stripeEmail: this.editForm.get(['stripeEmail'])!.value,
      customerId: this.editForm.get(['customerId'])!.value,
      customerName: this.editForm.get(['customerName'])!.value,
      migrateAt: this.editForm.get(['migrateAt'])!.value,
      updatedAt: this.editForm.get(['updatedAt'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBillingStripeLink>>): void {
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
