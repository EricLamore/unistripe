import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IUniproxyConso, UniproxyConso } from 'app/shared/model/uniproxy-conso.model';
import { UniproxyConsoService } from './uniproxy-conso.service';
import { UniproxyConsoComponent } from './uniproxy-conso.component';
import { UniproxyConsoDetailComponent } from './uniproxy-conso-detail.component';
import { UniproxyConsoUpdateComponent } from './uniproxy-conso-update.component';

@Injectable({ providedIn: 'root' })
export class UniproxyConsoResolve implements Resolve<IUniproxyConso> {
  constructor(private service: UniproxyConsoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUniproxyConso> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((uniproxyConso: HttpResponse<UniproxyConso>) => {
          if (uniproxyConso.body) {
            return of(uniproxyConso.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new UniproxyConso());
  }
}

export const uniproxyConsoRoute: Routes = [
  {
    path: '',
    component: UniproxyConsoComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'UniproxyConsos',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: UniproxyConsoDetailComponent,
    resolve: {
      uniproxyConso: UniproxyConsoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'UniproxyConsos',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: UniproxyConsoUpdateComponent,
    resolve: {
      uniproxyConso: UniproxyConsoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'UniproxyConsos',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: UniproxyConsoUpdateComponent,
    resolve: {
      uniproxyConso: UniproxyConsoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'UniproxyConsos',
    },
    canActivate: [UserRouteAccessService],
  },
];
