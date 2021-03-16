import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IOrganization, Organization } from 'app/shared/model/organization.model';
import { OrganizationService } from './organization.service';
import { IPrice } from 'app/shared/model/price.model';
import { PriceService } from 'app/entities/price/price.service';

@Component({
  selector: 'jhi-organization-update',
  templateUrl: './organization-update.component.html',
})
export class OrganizationUpdateComponent implements OnInit {
  isSaving = false;
  prices: IPrice[] = [];
  registerDateDp: any;

  editForm = this.fb.group({
    id: [],
    organisationId: [],
    address: [],
    city: [],
    country: [],
    name: [],
    registerDate: [],
    status: [],
    zipCode: [],
    individual: [],
    organization: [],
  });

  constructor(
    protected organizationService: OrganizationService,
    protected priceService: PriceService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ organization }) => {
      this.updateForm(organization);

      this.priceService.query().subscribe((res: HttpResponse<IPrice[]>) => (this.prices = res.body || []));
    });
  }

  updateForm(organization: IOrganization): void {
    this.editForm.patchValue({
      id: organization.id,
      organisationId: organization.organisationId,
      address: organization.address,
      city: organization.city,
      country: organization.country,
      name: organization.name,
      registerDate: organization.registerDate,
      status: organization.status,
      zipCode: organization.zipCode,
      individual: organization.individual,
      organization: organization.organization,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const organization = this.createFromForm();
    if (organization.id !== undefined) {
      this.subscribeToSaveResponse(this.organizationService.update(organization));
    } else {
      this.subscribeToSaveResponse(this.organizationService.create(organization));
    }
  }

  private createFromForm(): IOrganization {
    return {
      ...new Organization(),
      id: this.editForm.get(['id'])!.value,
      organisationId: this.editForm.get(['organisationId'])!.value,
      address: this.editForm.get(['address'])!.value,
      city: this.editForm.get(['city'])!.value,
      country: this.editForm.get(['country'])!.value,
      name: this.editForm.get(['name'])!.value,
      registerDate: this.editForm.get(['registerDate'])!.value,
      status: this.editForm.get(['status'])!.value,
      zipCode: this.editForm.get(['zipCode'])!.value,
      individual: this.editForm.get(['individual'])!.value,
      organization: this.editForm.get(['organization'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrganization>>): void {
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

  trackById(index: number, item: IPrice): any {
    return item.id;
  }
}
