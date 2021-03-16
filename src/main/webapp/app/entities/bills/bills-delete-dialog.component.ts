import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBills } from 'app/shared/model/bills.model';
import { BillsService } from './bills.service';

@Component({
  templateUrl: './bills-delete-dialog.component.html',
})
export class BillsDeleteDialogComponent {
  bills?: IBills;

  constructor(protected billsService: BillsService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.billsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('billsListModification');
      this.activeModal.close();
    });
  }
}
