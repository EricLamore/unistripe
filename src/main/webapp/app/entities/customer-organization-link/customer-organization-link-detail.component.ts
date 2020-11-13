import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICustomerOrganizationLink } from 'app/shared/model/customer-organization-link.model';

@Component({
  selector: 'jhi-customer-organization-link-detail',
  templateUrl: './customer-organization-link-detail.component.html',
})
export class CustomerOrganizationLinkDetailComponent implements OnInit {
  customerOrganizationLink: ICustomerOrganizationLink | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ customerOrganizationLink }) => (this.customerOrganizationLink = customerOrganizationLink));
  }

  previousState(): void {
    window.history.back();
  }
}
