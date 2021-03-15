import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStripeSubscription } from 'app/shared/model/stripe-subscription.model';

@Component({
  selector: 'jhi-stripe-subscription-detail',
  templateUrl: './stripe-subscription-detail.component.html',
})
export class StripeSubscriptionDetailComponent implements OnInit {
  stripeSubscription: IStripeSubscription | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ stripeSubscription }) => (this.stripeSubscription = stripeSubscription));
  }

  previousState(): void {
    window.history.back();
  }
}
