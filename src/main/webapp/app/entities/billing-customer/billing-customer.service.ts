import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBillingCustomer } from 'app/shared/model/billing-customer.model';

type EntityResponseType = HttpResponse<IBillingCustomer>;
type EntityArrayResponseType = HttpResponse<IBillingCustomer[]>;

@Injectable({ providedIn: 'root' })
export class BillingCustomerService {
  public resourceUrl = SERVER_API_URL + 'api/billing-customers';

  constructor(protected http: HttpClient) {}

  create(billingCustomer: IBillingCustomer): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(billingCustomer);
    return this.http
      .post<IBillingCustomer>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(billingCustomer: IBillingCustomer): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(billingCustomer);
    return this.http
      .put<IBillingCustomer>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBillingCustomer>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBillingCustomer[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(billingCustomer: IBillingCustomer): IBillingCustomer {
    const copy: IBillingCustomer = Object.assign({}, billingCustomer, {
      migrateAt:
        billingCustomer.migrateAt && billingCustomer.migrateAt.isValid() ? billingCustomer.migrateAt.format(DATE_FORMAT) : undefined,
      updatedAt:
        billingCustomer.updatedAt && billingCustomer.updatedAt.isValid() ? billingCustomer.updatedAt.format(DATE_FORMAT) : undefined,
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
      res.body.forEach((billingCustomer: IBillingCustomer) => {
        billingCustomer.migrateAt = billingCustomer.migrateAt ? moment(billingCustomer.migrateAt) : undefined;
        billingCustomer.updatedAt = billingCustomer.updatedAt ? moment(billingCustomer.updatedAt) : undefined;
      });
    }
    return res;
  }
}
