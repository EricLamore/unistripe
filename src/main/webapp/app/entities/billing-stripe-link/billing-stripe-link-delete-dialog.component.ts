import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBillingStripeLink } from 'app/shared/model/billing-stripe-link.model';
import { BillingStripeLinkService } from './billing-stripe-link.service';

@Component({
  templateUrl: './billing-stripe-link-delete-dialog.component.html',
})
export class BillingStripeLinkDeleteDialogComponent {
  billingStripeLink?: IBillingStripeLink;

  constructor(
    protected billingStripeLinkService: BillingStripeLinkService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.billingStripeLinkService.delete(id).subscribe(() => {
      this.eventManager.broadcast('billingStripeLinkListModification');
      this.activeModal.close();
    });
  }
}
