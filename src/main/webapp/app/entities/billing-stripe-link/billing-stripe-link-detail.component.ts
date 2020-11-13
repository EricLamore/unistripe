import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBillingStripeLink } from 'app/shared/model/billing-stripe-link.model';

@Component({
  selector: 'jhi-billing-stripe-link-detail',
  templateUrl: './billing-stripe-link-detail.component.html',
})
export class BillingStripeLinkDetailComponent implements OnInit {
  billingStripeLink: IBillingStripeLink | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ billingStripeLink }) => (this.billingStripeLink = billingStripeLink));
  }

  previousState(): void {
    window.history.back();
  }
}
