import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISignatureDetails, SignatureDetails } from 'app/shared/model/signature-details.model';
import { SignatureDetailsService } from './signature-details.service';
import { IUniproxyConso } from 'app/shared/model/uniproxy-conso.model';
import { UniproxyConsoService } from 'app/entities/uniproxy-conso/uniproxy-conso.service';

@Component({
  selector: 'jhi-signature-details-update',
  templateUrl: './signature-details-update.component.html',
})
export class SignatureDetailsUpdateComponent implements OnInit {
  isSaving = false;
  uniproxyconsos: IUniproxyConso[] = [];

  editForm = this.fb.group({
    id: [],
    firstName: [],
    lastName: [],
    sigantureDate: [],
    sigantureCount: [],
    uniproxyConso: [],
  });

  constructor(
    protected signatureDetailsService: SignatureDetailsService,
    protected uniproxyConsoService: UniproxyConsoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ signatureDetails }) => {
      if (!signatureDetails.id) {
        const today = moment().startOf('day');
        signatureDetails.sigantureDate = today;
      }

      this.updateForm(signatureDetails);

      this.uniproxyConsoService.query().subscribe((res: HttpResponse<IUniproxyConso[]>) => (this.uniproxyconsos = res.body || []));
    });
  }

  updateForm(signatureDetails: ISignatureDetails): void {
    this.editForm.patchValue({
      id: signatureDetails.id,
      firstName: signatureDetails.firstName,
      lastName: signatureDetails.lastName,
      sigantureDate: signatureDetails.sigantureDate ? signatureDetails.sigantureDate.format(DATE_TIME_FORMAT) : null,
      sigantureCount: signatureDetails.sigantureCount,
      uniproxyConso: signatureDetails.uniproxyConso,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const signatureDetails = this.createFromForm();
    if (signatureDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.signatureDetailsService.update(signatureDetails));
    } else {
      this.subscribeToSaveResponse(this.signatureDetailsService.create(signatureDetails));
    }
  }

  private createFromForm(): ISignatureDetails {
    return {
      ...new SignatureDetails(),
      id: this.editForm.get(['id'])!.value,
      firstName: this.editForm.get(['firstName'])!.value,
      lastName: this.editForm.get(['lastName'])!.value,
      sigantureDate: this.editForm.get(['sigantureDate'])!.value
        ? moment(this.editForm.get(['sigantureDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      sigantureCount: this.editForm.get(['sigantureCount'])!.value,
      uniproxyConso: this.editForm.get(['uniproxyConso'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISignatureDetails>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IUniproxyConso): any {
    return item.id;
  }
}
