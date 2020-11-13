import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICustomerOrganizationLink, CustomerOrganizationLink } from 'app/shared/model/customer-organization-link.model';
import { CustomerOrganizationLinkService } from './customer-organization-link.service';

@Component({
  selector: 'jhi-customer-organization-link-update',
  templateUrl: './customer-organization-link-update.component.html',
})
export class CustomerOrganizationLinkUpdateComponent implements OnInit {
  isSaving = false;
  organisationRegisterDateDp: any;

  editForm = this.fb.group({
    id: [],
    organisationId: [],
    organisationName: [],
    organisationRegisterDate: [],
    customerId: [],
    customerName: [],
  });

  constructor(
    protected customerOrganizationLinkService: CustomerOrganizationLinkService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ customerOrganizationLink }) => {
      this.updateForm(customerOrganizationLink);
    });
  }

  updateForm(customerOrganizationLink: ICustomerOrganizationLink): void {
    this.editForm.patchValue({
      id: customerOrganizationLink.id,
      organisationId: customerOrganizationLink.organisationId,
      organisationName: customerOrganizationLink.organisationName,
      organisationRegisterDate: customerOrganizationLink.organisationRegisterDate,
      customerId: customerOrganizationLink.customerId,
      customerName: customerOrganizationLink.customerName,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const customerOrganizationLink = this.createFromForm();
    if (customerOrganizationLink.id !== undefined) {
      this.subscribeToSaveResponse(this.customerOrganizationLinkService.update(customerOrganizationLink));
    } else {
      this.subscribeToSaveResponse(this.customerOrganizationLinkService.create(customerOrganizationLink));
    }
  }

  private createFromForm(): ICustomerOrganizationLink {
    return {
      ...new CustomerOrganizationLink(),
      id: this.editForm.get(['id'])!.value,
      organisationId: this.editForm.get(['organisationId'])!.value,
      organisationName: this.editForm.get(['organisationName'])!.value,
      organisationRegisterDate: this.editForm.get(['organisationRegisterDate'])!.value,
      customerId: this.editForm.get(['customerId'])!.value,
      customerName: this.editForm.get(['customerName'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICustomerOrganizationLink>>): void {
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
