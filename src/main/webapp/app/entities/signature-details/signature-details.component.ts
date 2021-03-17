import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISignatureDetails } from 'app/shared/model/signature-details.model';
import { SignatureDetailsService } from './signature-details.service';
import { SignatureDetailsDeleteDialogComponent } from './signature-details-delete-dialog.component';

@Component({
  selector: 'jhi-signature-details',
  templateUrl: './signature-details.component.html',
})
export class SignatureDetailsComponent implements OnInit, OnDestroy {
  signatureDetails?: ISignatureDetails[];
  eventSubscriber?: Subscription;

  constructor(
    protected signatureDetailsService: SignatureDetailsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.signatureDetailsService.query().subscribe((res: HttpResponse<ISignatureDetails[]>) => (this.signatureDetails = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSignatureDetails();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISignatureDetails): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSignatureDetails(): void {
    this.eventSubscriber = this.eventManager.subscribe('signatureDetailsListModification', () => this.loadAll());
  }

  delete(signatureDetails: ISignatureDetails): void {
    const modalRef = this.modalService.open(SignatureDetailsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.signatureDetails = signatureDetails;
  }
}
