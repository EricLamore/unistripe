import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IBills, Bills } from 'app/shared/model/bills.model';
import { BillsService } from './bills.service';

@Component({
  selector: 'jhi-bills-update',
  templateUrl: './bills-update.component.html',
})
export class BillsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    customerId: [],
    customerName: [],
    customerEmail: [],
    year: [],
    month: [],
    periodStart: [],
    periodEnd: [],
    created: [],
    send: [],
    dueDate: [],
    amountDue: [],
    total: [],
    tax: [],
    totalDiscountAmounts: [],
    totalTaxAmounts: [],
    url: [],
    pdfUrl: [],
  });

  constructor(protected billsService: BillsService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bills }) => {
      if (!bills.id) {
        const today = moment().startOf('day');
        bills.periodStart = today;
        bills.periodEnd = today;
        bills.created = today;
        bills.send = today;
        bills.dueDate = today;
      }

      this.updateForm(bills);
    });
  }

  updateForm(bills: IBills): void {
    this.editForm.patchValue({
      id: bills.id,
      customerId: bills.customerId,
      customerName: bills.customerName,
      customerEmail: bills.customerEmail,
      year: bills.year,
      month: bills.month,
      periodStart: bills.periodStart ? bills.periodStart.format(DATE_TIME_FORMAT) : null,
      periodEnd: bills.periodEnd ? bills.periodEnd.format(DATE_TIME_FORMAT) : null,
      created: bills.created ? bills.created.format(DATE_TIME_FORMAT) : null,
      send: bills.send ? bills.send.format(DATE_TIME_FORMAT) : null,
      dueDate: bills.dueDate ? bills.dueDate.format(DATE_TIME_FORMAT) : null,
      amountDue: bills.amountDue,
      total: bills.total,
      tax: bills.tax,
      totalDiscountAmounts: bills.totalDiscountAmounts,
      totalTaxAmounts: bills.totalTaxAmounts,
      url: bills.url,
      pdfUrl: bills.pdfUrl,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bills = this.createFromForm();
    if (bills.id !== undefined) {
      this.subscribeToSaveResponse(this.billsService.update(bills));
    } else {
      this.subscribeToSaveResponse(this.billsService.create(bills));
    }
  }

  private createFromForm(): IBills {
    return {
      ...new Bills(),
      id: this.editForm.get(['id'])!.value,
      customerId: this.editForm.get(['customerId'])!.value,
      customerName: this.editForm.get(['customerName'])!.value,
      customerEmail: this.editForm.get(['customerEmail'])!.value,
      year: this.editForm.get(['year'])!.value,
      month: this.editForm.get(['month'])!.value,
      periodStart: this.editForm.get(['periodStart'])!.value
        ? moment(this.editForm.get(['periodStart'])!.value, DATE_TIME_FORMAT)
        : undefined,
      periodEnd: this.editForm.get(['periodEnd'])!.value ? moment(this.editForm.get(['periodEnd'])!.value, DATE_TIME_FORMAT) : undefined,
      created: this.editForm.get(['created'])!.value ? moment(this.editForm.get(['created'])!.value, DATE_TIME_FORMAT) : undefined,
      send: this.editForm.get(['send'])!.value ? moment(this.editForm.get(['send'])!.value, DATE_TIME_FORMAT) : undefined,
      dueDate: this.editForm.get(['dueDate'])!.value ? moment(this.editForm.get(['dueDate'])!.value, DATE_TIME_FORMAT) : undefined,
      amountDue: this.editForm.get(['amountDue'])!.value,
      total: this.editForm.get(['total'])!.value,
      tax: this.editForm.get(['tax'])!.value,
      totalDiscountAmounts: this.editForm.get(['totalDiscountAmounts'])!.value,
      totalTaxAmounts: this.editForm.get(['totalTaxAmounts'])!.value,
      url: this.editForm.get(['url'])!.value,
      pdfUrl: this.editForm.get(['pdfUrl'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBills>>): void {
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
