import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEventUses, EventUses } from 'app/shared/model/event-uses.model';
import { EventUsesService } from './event-uses.service';

@Component({
  selector: 'jhi-event-uses-update',
  templateUrl: './event-uses-update.component.html',
})
export class EventUsesUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    cusotmerId: [],
    customerName: [],
    subscriptionId: [],
    subscriptionName: [],
    productId: [],
    productName: [],
    month: [],
    year: [],
    status: [],
    error: [],
    total: [],
  });

  constructor(protected eventUsesService: EventUsesService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ eventUses }) => {
      this.updateForm(eventUses);
    });
  }

  updateForm(eventUses: IEventUses): void {
    this.editForm.patchValue({
      id: eventUses.id,
      cusotmerId: eventUses.cusotmerId,
      customerName: eventUses.customerName,
      subscriptionId: eventUses.subscriptionId,
      subscriptionName: eventUses.subscriptionName,
      productId: eventUses.productId,
      productName: eventUses.productName,
      month: eventUses.month,
      year: eventUses.year,
      status: eventUses.status,
      error: eventUses.error,
      total: eventUses.total,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const eventUses = this.createFromForm();
    if (eventUses.id !== undefined) {
      this.subscribeToSaveResponse(this.eventUsesService.update(eventUses));
    } else {
      this.subscribeToSaveResponse(this.eventUsesService.create(eventUses));
    }
  }

  private createFromForm(): IEventUses {
    return {
      ...new EventUses(),
      id: this.editForm.get(['id'])!.value,
      cusotmerId: this.editForm.get(['cusotmerId'])!.value,
      customerName: this.editForm.get(['customerName'])!.value,
      subscriptionId: this.editForm.get(['subscriptionId'])!.value,
      subscriptionName: this.editForm.get(['subscriptionName'])!.value,
      productId: this.editForm.get(['productId'])!.value,
      productName: this.editForm.get(['productName'])!.value,
      month: this.editForm.get(['month'])!.value,
      year: this.editForm.get(['year'])!.value,
      status: this.editForm.get(['status'])!.value,
      error: this.editForm.get(['error'])!.value,
      total: this.editForm.get(['total'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEventUses>>): void {
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
