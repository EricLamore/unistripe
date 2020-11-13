import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBillingStripeLink } from 'app/shared/model/billing-stripe-link.model';

type EntityResponseType = HttpResponse<IBillingStripeLink>;
type EntityArrayResponseType = HttpResponse<IBillingStripeLink[]>;

@Injectable({ providedIn: 'root' })
export class BillingStripeLinkService {
  public resourceUrl = SERVER_API_URL + 'api/billing-stripe-links';

  constructor(protected http: HttpClient) {}

  create(billingStripeLink: IBillingStripeLink): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(billingStripeLink);
    return this.http
      .post<IBillingStripeLink>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(billingStripeLink: IBillingStripeLink): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(billingStripeLink);
    return this.http
      .put<IBillingStripeLink>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBillingStripeLink>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBillingStripeLink[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(billingStripeLink: IBillingStripeLink): IBillingStripeLink {
    const copy: IBillingStripeLink = Object.assign({}, billingStripeLink, {
      migrateAt:
        billingStripeLink.migrateAt && billingStripeLink.migrateAt.isValid() ? billingStripeLink.migrateAt.format(DATE_FORMAT) : undefined,
      updatedAt:
        billingStripeLink.updatedAt && billingStripeLink.updatedAt.isValid() ? billingStripeLink.updatedAt.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.migrateAt = res.body.migrateAt ? moment(res.body.migrateAt) : undefined;
      res.body.updatedAt = res.body.updatedAt ? moment(res.body.updatedAt) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((billingStripeLink: IBillingStripeLink) => {
        billingStripeLink.migrateAt = billingStripeLink.migrateAt ? moment(billingStripeLink.migrateAt) : undefined;
        billingStripeLink.updatedAt = billingStripeLink.updatedAt ? moment(billingStripeLink.updatedAt) : undefined;
      });
    }
    return res;
  }
}
