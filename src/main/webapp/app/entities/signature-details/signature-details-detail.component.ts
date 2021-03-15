import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISignatureDetails } from 'app/shared/model/signature-details.model';

@Component({
  selector: 'jhi-signature-details-detail',
  templateUrl: './signature-details-detail.component.html',
})
export class SignatureDetailsDetailComponent implements OnInit {
  signatureDetails: ISignatureDetails | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ signatureDetails }) => (this.signatureDetails = signatureDetails));
  }

  previousState(): void {
    window.history.back();
  }
}
