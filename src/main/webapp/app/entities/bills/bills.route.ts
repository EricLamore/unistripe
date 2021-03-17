import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBills, Bills } from 'app/shared/model/bills.model';
import { BillsService } from './bills.service';
import { BillsComponent } from './bills.component';
import { BillsDetailComponent } from './bills-detail.component';
import { BillsUpdateComponent } from './bills-update.component';

@Injectable({ providedIn: 'root' })
export class BillsResolve implements Resolve<IBills> {
  constructor(private service: BillsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBills> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bills: HttpResponse<Bills>) => {
          if (bills.body) {
            return of(bills.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Bills());
  }
}

export const billsRoute: Routes = [
  {
    path: '',
    component: BillsComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Bills',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BillsDetailComponent,
    resolve: {
      bills: BillsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bills',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BillsUpdateComponent,
    resolve: {
      bills: BillsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bills',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BillsUpdateComponent,
    resolve: {
      bills: BillsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bills',
    },
    canActivate: [UserRouteAccessService],
  },
];
