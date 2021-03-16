import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUniproxyConso } from 'app/shared/model/uniproxy-conso.model';

@Component({
  selector: 'jhi-uniproxy-conso-detail',
  templateUrl: './uniproxy-conso-detail.component.html',
})
export class UniproxyConsoDetailComponent implements OnInit {
  uniproxyConso: IUniproxyConso | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ uniproxyConso }) => (this.uniproxyConso = uniproxyConso));
  }

  previousState(): void {
    window.history.back();
  }
}
