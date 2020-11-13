import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICustomerOrganizationLink } from 'app/shared/model/customer-organization-link.model';
import { CustomerOrganizationLinkService } from './customer-organization-link.service';

@Component({
  templateUrl: './customer-organization-link-delete-dialog.component.html',
})
export class CustomerOrganizationLinkDeleteDialogComponent {
  customerOrganizationLink?: ICustomerOrganizationLink;

  constructor(
    protected customerOrganizationLinkService: CustomerOrganizationLinkService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.customerOrganizationLinkService.delete(id).subscribe(() => {
      this.eventManager.broadcast('customerOrganizationLinkListModification');
      this.activeModal.close();
    });
  }
}
