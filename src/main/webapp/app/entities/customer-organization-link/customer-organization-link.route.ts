import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICustomerOrganizationLink, CustomerOrganizationLink } from 'app/shared/model/customer-organization-link.model';
import { CustomerOrganizationLinkService } from './customer-organization-link.service';
import { CustomerOrganizationLinkComponent } from './customer-organization-link.component';
import { CustomerOrganizationLinkDetailComponent } from './customer-organization-link-detail.component';
import { CustomerOrganizationLinkUpdateComponent } from './customer-organization-link-update.component';

@Injectable({ providedIn: 'root' })
export class CustomerOrganizationLinkResolve implements Resolve<ICustomerOrganizationLink> {
  constructor(private service: CustomerOrganizationLinkService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICustomerOrganizationLink> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((customerOrganizationLink: HttpResponse<CustomerOrganizationLink>) => {
          if (customerOrganizationLink.body) {
            return of(customerOrganizationLink.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CustomerOrganizationLink());
  }
}

export const customerOrganizationLinkRoute: Routes = [
  {
    path: '',
    component: CustomerOrganizationLinkComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'CustomerOrganizationLinks',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CustomerOrganizationLinkDetailComponent,
    resolve: {
      customerOrganizationLink: CustomerOrganizationLinkResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'CustomerOrganizationLinks',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CustomerOrganizationLinkUpdateComponent,
    resolve: {
      customerOrganizationLink: CustomerOrganizationLinkResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'CustomerOrganizationLinks',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CustomerOrganizationLinkUpdateComponent,
    resolve: {
      customerOrganizationLink: CustomerOrganizationLinkResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'CustomerOrganizationLinks',
    },
    canActivate: [UserRouteAccessService],
  },
];
