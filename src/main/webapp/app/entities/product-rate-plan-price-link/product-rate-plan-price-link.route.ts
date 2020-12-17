import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProductRatePlanPriceLink, ProductRatePlanPriceLink } from 'app/shared/model/product-rate-plan-price-link.model';
import { ProductRatePlanPriceLinkService } from './product-rate-plan-price-link.service';
import { ProductRatePlanPriceLinkComponent } from './product-rate-plan-price-link.component';
import { ProductRatePlanPriceLinkDetailComponent } from './product-rate-plan-price-link-detail.component';
import { ProductRatePlanPriceLinkUpdateComponent } from './product-rate-plan-price-link-update.component';

@Injectable({ providedIn: 'root' })
export class ProductRatePlanPriceLinkResolve implements Resolve<IProductRatePlanPriceLink> {
  constructor(private service: ProductRatePlanPriceLinkService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProductRatePlanPriceLink> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((productRatePlanPriceLink: HttpResponse<ProductRatePlanPriceLink>) => {
          if (productRatePlanPriceLink.body) {
            return of(productRatePlanPriceLink.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ProductRatePlanPriceLink());
  }
}

export const productRatePlanPriceLinkRoute: Routes = [
  {
    path: '',
    component: ProductRatePlanPriceLinkComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProductRatePlanPriceLinks',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProductRatePlanPriceLinkDetailComponent,
    resolve: {
      productRatePlanPriceLink: ProductRatePlanPriceLinkResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProductRatePlanPriceLinks',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ProductRatePlanPriceLinkUpdateComponent,
    resolve: {
      productRatePlanPriceLink: ProductRatePlanPriceLinkResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProductRatePlanPriceLinks',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ProductRatePlanPriceLinkUpdateComponent,
    resolve: {
      productRatePlanPriceLink: ProductRatePlanPriceLinkResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProductRatePlanPriceLinks',
    },
    canActivate: [UserRouteAccessService],
  },
];
