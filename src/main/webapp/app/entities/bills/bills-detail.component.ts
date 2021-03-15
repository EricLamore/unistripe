import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBills } from 'app/shared/model/bills.model';

@Component({
  selector: 'jhi-bills-detail',
  templateUrl: './bills-detail.component.html',
})
export class BillsDetailComponent implements OnInit {
  bills: IBills | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bills }) => (this.bills = bills));
  }

  previousState(): void {
    window.history.back();
  }
}
