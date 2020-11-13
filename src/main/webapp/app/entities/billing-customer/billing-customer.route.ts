import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBillingCustomer, BillingCustomer } from 'app/shared/model/billing-customer.model';
import { BillingCustomerService } from './billing-customer.service';
import { BillingCustomerComponent } from './billing-customer.component';
import { BillingCustomerDetailComponent } from './billing-customer-detail.component';
import { BillingCustomerUpdateComponent } from './billing-customer-update.component';

@Injectable({ providedIn: 'root' })
export class BillingCustomerResolve implements Resolve<IBillingCustomer> {
  constructor(private service: BillingCustomerService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBillingCustomer> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((billingCustomer: HttpResponse<BillingCustomer>) => {
          if (billingCustomer.body) {
            return of(billingCustomer.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BillingCustomer());
  }
}

export const billingCustomerRoute: Routes = [
  {
    path: '',
    component: BillingCustomerComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BillingCustomers',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BillingCustomerDetailComponent,
    resolve: {
      billingCustomer: BillingCustomerResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BillingCustomers',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BillingCustomerUpdateComponent,
    resolve: {
      billingCustomer: BillingCustomerResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BillingCustomers',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BillingCustomerUpdateComponent,
    resolve: {
      billingCustomer: BillingCustomerResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BillingCustomers',
    },
    canActivate: [UserRouteAccessService],
  },
];
