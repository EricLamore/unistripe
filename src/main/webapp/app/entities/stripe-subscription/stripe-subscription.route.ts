import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IStripeSubscription, StripeSubscription } from 'app/shared/model/stripe-subscription.model';
import { StripeSubscriptionService } from './stripe-subscription.service';
import { StripeSubscriptionComponent } from './stripe-subscription.component';
import { StripeSubscriptionDetailComponent } from './stripe-subscription-detail.component';
import { StripeSubscriptionUpdateComponent } from './stripe-subscription-update.component';

@Injectable({ providedIn: 'root' })
export class StripeSubscriptionResolve implements Resolve<IStripeSubscription> {
  constructor(private service: StripeSubscriptionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IStripeSubscription> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((stripeSubscription: HttpResponse<StripeSubscription>) => {
          if (stripeSubscription.body) {
            return of(stripeSubscription.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new StripeSubscription());
  }
}

export const stripeSubscriptionRoute: Routes = [
  {
    path: '',
    component: StripeSubscriptionComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'StripeSubscriptions',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: StripeSubscriptionDetailComponent,
    resolve: {
      stripeSubscription: StripeSubscriptionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'StripeSubscriptions',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: StripeSubscriptionUpdateComponent,
    resolve: {
      stripeSubscription: StripeSubscriptionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'StripeSubscriptions',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: StripeSubscriptionUpdateComponent,
    resolve: {
      stripeSubscription: StripeSubscriptionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'StripeSubscriptions',
    },
    canActivate: [UserRouteAccessService],
  },
];
