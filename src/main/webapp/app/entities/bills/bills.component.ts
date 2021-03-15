import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBills } from 'app/shared/model/bills.model';
import { BillsService } from './bills.service';
import { BillsDeleteDialogComponent } from './bills-delete-dialog.component';

@Component({
  selector: 'jhi-bills',
  templateUrl: './bills.component.html',
})
export class BillsComponent implements OnInit, OnDestroy {
  bills?: IBills[];
  eventSubscriber?: Subscription;

  constructor(protected billsService: BillsService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.billsService.query().subscribe((res: HttpResponse<IBills[]>) => (this.bills = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBills();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBills): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBills(): void {
    this.eventSubscriber = this.eventManager.subscribe('billsListModification', () => this.loadAll());
  }

  delete(bills: IBills): void {
    const modalRef = this.modalService.open(BillsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.bills = bills;
  }
}
