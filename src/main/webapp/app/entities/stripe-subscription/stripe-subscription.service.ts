import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IStripeSubscription } from 'app/shared/model/stripe-subscription.model';

type EntityResponseType = HttpResponse<IStripeSubscription>;
type EntityArrayResponseType = HttpResponse<IStripeSubscription[]>;

@Injectable({ providedIn: 'root' })
export class StripeSubscriptionService {
  public resourceUrl = SERVER_API_URL + 'api/stripe-subscriptions';

  constructor(protected http: HttpClient) {}

  create(stripeSubscription: IStripeSubscription): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(stripeSubscription);
    return this.http
      .post<IStripeSubscription>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(stripeSubscription: IStripeSubscription): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(stripeSubscription);
    return this.http
      .put<IStripeSubscription>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IStripeSubscription>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IStripeSubscription[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(stripeSubscription: IStripeSubscription): IStripeSubscription {
    const copy: IStripeSubscription = Object.assign({}, stripeSubscription, {
      migrateAt:
        stripeSubscription.migrateAt && stripeSubscription.migrateAt.isValid()
          ? stripeSubscription.migrateAt.format(DATE_FORMAT)
          : undefined,
      updatedAt:
        stripeSubscription.updatedAt && stripeSubscription.updatedAt.isValid()
          ? stripeSubscription.updatedAt.format(DATE_FORMAT)
          : undefined,
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
      res.body.forEach((stripeSubscription: IStripeSubscription) => {
        stripeSubscription.migrateAt = stripeSubscription.migrateAt ? moment(stripeSubscription.migrateAt) : undefined;
        stripeSubscription.updatedAt = stripeSubscription.updatedAt ? moment(stripeSubscription.updatedAt) : undefined;
      });
    }
    return res;
  }
}
