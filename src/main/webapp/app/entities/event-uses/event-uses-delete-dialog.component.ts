import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEventUses } from 'app/shared/model/event-uses.model';
import { EventUsesService } from './event-uses.service';

@Component({
  templateUrl: './event-uses-delete-dialog.component.html',
})
export class EventUsesDeleteDialogComponent {
  eventUses?: IEventUses;

  constructor(protected eventUsesService: EventUsesService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.eventUsesService.delete(id).subscribe(() => {
      this.eventManager.broadcast('eventUsesListModification');
      this.activeModal.close();
    });
  }
}
