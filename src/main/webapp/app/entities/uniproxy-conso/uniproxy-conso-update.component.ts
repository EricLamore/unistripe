import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IUniproxyConso, UniproxyConso } from 'app/shared/model/uniproxy-conso.model';
import { UniproxyConsoService } from './uniproxy-conso.service';

@Component({
  selector: 'jhi-uniproxy-conso-update',
  templateUrl: './uniproxy-conso-update.component.html',
})
export class UniproxyConsoUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    subscriptionId: [],
    orgId: [],
    month: [],
    year: [],
    type: [],
    conso: [],
  });

  constructor(protected uniproxyConsoService: UniproxyConsoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ uniproxyConso }) => {
      this.updateForm(uniproxyConso);
    });
  }

  updateForm(uniproxyConso: IUniproxyConso): void {
    this.editForm.patchValue({
      id: uniproxyConso.id,
      subscriptionId: uniproxyConso.subscriptionId,
      orgId: uniproxyConso.orgId,
      month: uniproxyConso.month,
      year: uniproxyConso.year,
      type: uniproxyConso.type,
      conso: uniproxyConso.conso,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const uniproxyConso = this.createFromForm();
    if (uniproxyConso.id !== undefined) {
      this.subscribeToSaveResponse(this.uniproxyConsoService.update(uniproxyConso));
    } else {
      this.subscribeToSaveResponse(this.uniproxyConsoService.create(uniproxyConso));
    }
  }

  private createFromForm(): IUniproxyConso {
    return {
      ...new UniproxyConso(),
      id: this.editForm.get(['id'])!.value,
      subscriptionId: this.editForm.get(['subscriptionId'])!.value,
      orgId: this.editForm.get(['orgId'])!.value,
      month: this.editForm.get(['month'])!.value,
      year: this.editForm.get(['year'])!.value,
      type: this.editForm.get(['type'])!.value,
      conso: this.editForm.get(['conso'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUniproxyConso>>): void {
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
}
