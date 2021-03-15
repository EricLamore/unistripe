import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBills } from 'app/shared/model/bills.model';

type EntityResponseType = HttpResponse<IBills>;
type EntityArrayResponseType = HttpResponse<IBills[]>;

@Injectable({ providedIn: 'root' })
export class BillsService {
  public resourceUrl = SERVER_API_URL + 'api/bills';

  constructor(protected http: HttpClient) {}

  create(bills: IBills): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bills);
    return this.http
      .post<IBills>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(bills: IBills): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bills);
    return this.http
      .put<IBills>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBills>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBills[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(bills: IBills): IBills {
    const copy: IBills = Object.assign({}, bills, {
      periodStart: bills.periodStart && bills.periodStart.isValid() ? bills.periodStart.toJSON() : undefined,
      periodEnd: bills.periodEnd && bills.periodEnd.isValid() ? bills.periodEnd.toJSON() : undefined,
      created: bills.created && bills.created.isValid() ? bills.created.toJSON() : undefined,
      send: bills.send && bills.send.isValid() ? bills.send.toJSON() : undefined,
      dueDate: bills.dueDate && bills.dueDate.isValid() ? bills.dueDate.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.periodStart = res.body.periodStart ? moment(res.body.periodStart) : undefined;
      res.body.periodEnd = res.body.periodEnd ? moment(res.body.periodEnd) : undefined;
      res.body.created = res.body.created ? moment(res.body.created) : undefined;
      res.body.send = res.body.send ? moment(res.body.send) : undefined;
      res.body.dueDate = res.body.dueDate ? moment(res.body.dueDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((bills: IBills) => {
        bills.periodStart = bills.periodStart ? moment(bills.periodStart) : undefined;
        bills.periodEnd = bills.periodEnd ? moment(bills.periodEnd) : undefined;
        bills.created = bills.created ? moment(bills.created) : undefined;
        bills.send = bills.send ? moment(bills.send) : undefined;
        bills.dueDate = bills.dueDate ? moment(bills.dueDate) : undefined;
      });
    }
    return res;
  }
}
