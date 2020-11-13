import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBillingCustomer } from 'app/shared/model/billing-customer.model';
import { BillingCustomerService } from './billing-customer.service';
import { BillingCustomerDeleteDialogComponent } from './billing-customer-delete-dialog.component';

@Component({
  selector: 'jhi-billing-customer',
  templateUrl: './billing-customer.component.html',
})
export class BillingCustomerComponent implements OnInit, OnDestroy {
  billingCustomers?: IBillingCustomer[];
  eventSubscriber?: Subscription;

  constructor(
    protected billingCustomerService: BillingCustomerService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.billingCustomerService.query().subscribe((res: HttpResponse<IBillingCustomer[]>) => (this.billingCustomers = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBillingCustomers();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBillingCustomer): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBillingCustomers(): void {
    this.eventSubscriber = this.eventManager.subscribe('billingCustomerListModification', () => this.loadAll());
  }

  delete(billingCustomer: IBillingCustomer): void {
    const modalRef = this.modalService.open(BillingCustomerDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.billingCustomer = billingCustomer;
  }
}
