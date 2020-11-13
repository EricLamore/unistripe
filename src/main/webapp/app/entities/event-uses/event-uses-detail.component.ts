import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEventUses } from 'app/shared/model/event-uses.model';

@Component({
  selector: 'jhi-event-uses-detail',
  templateUrl: './event-uses-detail.component.html',
})
export class EventUsesDetailComponent implements OnInit {
  eventUses: IEventUses | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ eventUses }) => (this.eventUses = eventUses));
  }

  previousState(): void {
    window.history.back();
  }
}
