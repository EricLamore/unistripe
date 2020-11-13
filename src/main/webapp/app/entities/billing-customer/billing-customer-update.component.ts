import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBillingCustomer, BillingCustomer } from 'app/shared/model/billing-customer.model';
import { BillingCustomerService } from './billing-customer.service';

@Component({
  selector: 'jhi-billing-customer-update',
  templateUrl: './billing-customer-update.component.html',
})
export class BillingCustomerUpdateComponent implements OnInit {
  isSaving = false;
  migrateAtDp: any;
  updatedAtDp: any;

  editForm = this.fb.group({
    id: [],
    taxNo: [],
    thirdPartyAccountingCode: [],
    siret: [],
    ownerId: [],
    isParticular: [],
    partner: [],
    partnerId: [],
    customerId: [],
    customerName: [],
    stripeId: [],
    stripeEmail: [],
    migrateAt: [],
    updatedAt: [],
  });

  constructor(
    protected billingCustomerService: BillingCustomerService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ billingCustomer }) => {
      this.updateForm(billingCustomer);
    });
  }

  updateForm(billingCustomer: IBillingCustomer): void {
    this.editForm.patchValue({
      id: billingCustomer.id,
      taxNo: billingCustomer.taxNo,
      thirdPartyAccountingCode: billingCustomer.thirdPartyAccountingCode,
      siret: billingCustomer.siret,
      ownerId: billingCustomer.ownerId,
      isParticular: billingCustomer.isParticular,
      partner: billingCustomer.partner,
      partnerId: billingCustomer.partnerId,
      customerId: billingCustomer.customerId,
      customerName: billingCustomer.customerName,
      stripeId: billingCustomer.stripeId,
      stripeEmail: billingCustomer.stripeEmail,
      migrateAt: billingCustomer.migrateAt,
      updatedAt: billingCustomer.updatedAt,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const billingCustomer = this.createFromForm();
    if (billingCustomer.id !== undefined) {
      this.subscribeToSaveResponse(this.billingCustomerService.update(billingCustomer));
    } else {
      this.subscribeToSaveResponse(this.billingCustomerService.create(billingCustomer));
    }
  }

  private createFromForm(): IBillingCustomer {
    return {
      ...new BillingCustomer(),
      id: this.editForm.get(['id'])!.value,
      taxNo: this.editForm.get(['taxNo'])!.value,
      thirdPartyAccountingCode: this.editForm.get(['thirdPartyAccountingCode'])!.value,
      siret: this.editForm.get(['siret'])!.value,
      ownerId: this.editForm.get(['ownerId'])!.value,
      isParticular: this.editForm.get(['isParticular'])!.value,
      partner: this.editForm.get(['partner'])!.value,
      partnerId: this.editForm.get(['partnerId'])!.value,
      customerId: this.editForm.get(['customerId'])!.value,
      customerName: this.editForm.get(['customerName'])!.value,
      stripeId: this.editForm.get(['stripeId'])!.value,
      stripeEmail: this.editForm.get(['stripeEmail'])!.value,
      migrateAt: this.editForm.get(['migrateAt'])!.value,
      updatedAt: this.editForm.get(['updatedAt'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBillingCustomer>>): void {
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
