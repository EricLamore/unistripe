import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBillingCustomer } from 'app/shared/model/billing-customer.model';

@Component({
  selector: 'jhi-billing-customer-detail',
  templateUrl: './billing-customer-detail.component.html',
})
export class BillingCustomerDetailComponent implements OnInit {
  billingCustomer: IBillingCustomer | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ billingCustomer }) => (this.billingCustomer = billingCustomer));
  }

  previousState(): void {
    window.history.back();
  }
}
