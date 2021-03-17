import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUniproxyConso } from 'app/shared/model/uniproxy-conso.model';
import { UniproxyConsoService } from './uniproxy-conso.service';

@Component({
  templateUrl: './uniproxy-conso-delete-dialog.component.html',
})
export class UniproxyConsoDeleteDialogComponent {
  uniproxyConso?: IUniproxyConso;

  constructor(
    protected uniproxyConsoService: UniproxyConsoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.uniproxyConsoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('uniproxyConsoListModification');
      this.activeModal.close();
    });
  }
}
