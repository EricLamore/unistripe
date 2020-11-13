import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBillingCustomer } from 'app/shared/model/billing-customer.model';
import { BillingCustomerService } from './billing-customer.service';

@Component({
  templateUrl: './billing-customer-delete-dialog.component.html',
})
export class BillingCustomerDeleteDialogComponent {
  billingCustomer?: IBillingCustomer;

  constructor(
    protected billingCustomerService: BillingCustomerService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.billingCustomerService.delete(id).subscribe(() => {
      this.eventManager.broadcast('billingCustomerListModification');
      this.activeModal.close();
    });
  }
}
