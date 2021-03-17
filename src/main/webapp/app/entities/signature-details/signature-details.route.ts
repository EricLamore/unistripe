import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISignatureDetails, SignatureDetails } from 'app/shared/model/signature-details.model';
import { SignatureDetailsService } from './signature-details.service';
import { SignatureDetailsComponent } from './signature-details.component';
import { SignatureDetailsDetailComponent } from './signature-details-detail.component';
import { SignatureDetailsUpdateComponent } from './signature-details-update.component';

@Injectable({ providedIn: 'root' })
export class SignatureDetailsResolve implements Resolve<ISignatureDetails> {
  constructor(private service: SignatureDetailsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISignatureDetails> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((signatureDetails: HttpResponse<SignatureDetails>) => {
          if (signatureDetails.body) {
            return of(signatureDetails.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SignatureDetails());
  }
}

export const signatureDetailsRoute: Routes = [
  {
    path: '',
    component: SignatureDetailsComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'SignatureDetails',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SignatureDetailsDetailComponent,
    resolve: {
      signatureDetails: SignatureDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'SignatureDetails',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SignatureDetailsUpdateComponent,
    resolve: {
      signatureDetails: SignatureDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'SignatureDetails',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SignatureDetailsUpdateComponent,
    resolve: {
      signatureDetails: SignatureDetailsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'SignatureDetails',
    },
    canActivate: [UserRouteAccessService],
  },
];
