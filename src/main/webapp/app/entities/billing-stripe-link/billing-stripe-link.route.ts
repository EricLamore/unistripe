import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBillingStripeLink, BillingStripeLink } from 'app/shared/model/billing-stripe-link.model';
import { BillingStripeLinkService } from './billing-stripe-link.service';
import { BillingStripeLinkComponent } from './billing-stripe-link.component';
import { BillingStripeLinkDetailComponent } from './billing-stripe-link-detail.component';
import { BillingStripeLinkUpdateComponent } from './billing-stripe-link-update.component';

@Injectable({ providedIn: 'root' })
export class BillingStripeLinkResolve implements Resolve<IBillingStripeLink> {
  constructor(private service: BillingStripeLinkService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBillingStripeLink> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((billingStripeLink: HttpResponse<BillingStripeLink>) => {
          if (billingStripeLink.body) {
            return of(billingStripeLink.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BillingStripeLink());
  }
}

export const billingStripeLinkRoute: Routes = [
  {
    path: '',
    component: BillingStripeLinkComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'BillingStripeLinks',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BillingStripeLinkDetailComponent,
    resolve: {
      billingStripeLink: BillingStripeLinkResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BillingStripeLinks',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BillingStripeLinkUpdateComponent,
    resolve: {
      billingStripeLink: BillingStripeLinkResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BillingStripeLinks',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BillingStripeLinkUpdateComponent,
    resolve: {
      billingStripeLink: BillingStripeLinkResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BillingStripeLinks',
    },
    canActivate: [UserRouteAccessService],
  },
];
