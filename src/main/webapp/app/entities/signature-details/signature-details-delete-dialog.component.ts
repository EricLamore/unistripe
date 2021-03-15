import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISignatureDetails } from 'app/shared/model/signature-details.model';
import { SignatureDetailsService } from './signature-details.service';

@Component({
  templateUrl: './signature-details-delete-dialog.component.html',
})
export class SignatureDetailsDeleteDialogComponent {
  signatureDetails?: ISignatureDetails;

  constructor(
    protected signatureDetailsService: SignatureDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.signatureDetailsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('signatureDetailsListModification');
      this.activeModal.close();
    });
  }
}
