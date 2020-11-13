import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEventUses } from 'app/shared/model/event-uses.model';
import { EventUsesService } from './event-uses.service';
import { EventUsesDeleteDialogComponent } from './event-uses-delete-dialog.component';

@Component({
  selector: 'jhi-event-uses',
  templateUrl: './event-uses.component.html',
})
export class EventUsesComponent implements OnInit, OnDestroy {
  eventUses?: IEventUses[];
  eventSubscriber?: Subscription;

  constructor(protected eventUsesService: EventUsesService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.eventUsesService.query().subscribe((res: HttpResponse<IEventUses[]>) => (this.eventUses = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEventUses();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEventUses): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEventUses(): void {
    this.eventSubscriber = this.eventManager.subscribe('eventUsesListModification', () => this.loadAll());
  }

  delete(eventUses: IEventUses): void {
    const modalRef = this.modalService.open(EventUsesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.eventUses = eventUses;
  }
}
