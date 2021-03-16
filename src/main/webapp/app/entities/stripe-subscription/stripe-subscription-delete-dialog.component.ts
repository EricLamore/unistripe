import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IStripeSubscription } from 'app/shared/model/stripe-subscription.model';
import { StripeSubscriptionService } from './stripe-subscription.service';

@Component({
  templateUrl: './stripe-subscription-delete-dialog.component.html',
})
export class StripeSubscriptionDeleteDialogComponent {
  stripeSubscription?: IStripeSubscription;

  constructor(
    protected stripeSubscriptionService: StripeSubscriptionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.stripeSubscriptionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('stripeSubscriptionListModification');
      this.activeModal.close();
    });
  }
}
