import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEventUses, EventUses } from 'app/shared/model/event-uses.model';
import { EventUsesService } from './event-uses.service';
import { EventUsesComponent } from './event-uses.component';
import { EventUsesDetailComponent } from './event-uses-detail.component';
import { EventUsesUpdateComponent } from './event-uses-update.component';

@Injectable({ providedIn: 'root' })
export class EventUsesResolve implements Resolve<IEventUses> {
  constructor(private service: EventUsesService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEventUses> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((eventUses: HttpResponse<EventUses>) => {
          if (eventUses.body) {
            return of(eventUses.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EventUses());
  }
}

export const eventUsesRoute: Routes = [
  {
    path: '',
    component: EventUsesComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'EventUses',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EventUsesDetailComponent,
    resolve: {
      eventUses: EventUsesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'EventUses',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EventUsesUpdateComponent,
    resolve: {
      eventUses: EventUsesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'EventUses',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EventUsesUpdateComponent,
    resolve: {
      eventUses: EventUsesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'EventUses',
    },
    canActivate: [UserRouteAccessService],
  },
];
