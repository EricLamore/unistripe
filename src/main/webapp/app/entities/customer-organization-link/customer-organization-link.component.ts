import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICustomerOrganizationLink } from 'app/shared/model/customer-organization-link.model';
import { CustomerOrganizationLinkService } from './customer-organization-link.service';
import { CustomerOrganizationLinkDeleteDialogComponent } from './customer-organization-link-delete-dialog.component';

@Component({
  selector: 'jhi-customer-organization-link',
  templateUrl: './customer-organization-link.component.html',
})
export class CustomerOrganizationLinkComponent implements OnInit, OnDestroy {
  customerOrganizationLinks?: ICustomerOrganizationLink[];
  eventSubscriber?: Subscription;

  constructor(
    protected customerOrganizationLinkService: CustomerOrganizationLinkService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.customerOrganizationLinkService
      .query()
      .subscribe((res: HttpResponse<ICustomerOrganizationLink[]>) => (this.customerOrganizationLinks = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCustomerOrganizationLinks();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICustomerOrganizationLink): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCustomerOrganizationLinks(): void {
    this.eventSubscriber = this.eventManager.subscribe('customerOrganizationLinkListModification', () => this.loadAll());
  }

  delete(customerOrganizationLink: ICustomerOrganizationLink): void {
    const modalRef = this.modalService.open(CustomerOrganizationLinkDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.customerOrganizationLink = customerOrganizationLink;
  }
}
