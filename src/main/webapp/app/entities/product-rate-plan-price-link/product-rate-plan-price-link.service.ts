import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProductRatePlanPriceLink } from 'app/shared/model/product-rate-plan-price-link.model';

type EntityResponseType = HttpResponse<IProductRatePlanPriceLink>;
type EntityArrayResponseType = HttpResponse<IProductRatePlanPriceLink[]>;

@Injectable({ providedIn: 'root' })
export class ProductRatePlanPriceLinkService {
  public resourceUrl = SERVER_API_URL + 'api/product-rate-plan-price-links';

  constructor(protected http: HttpClient) {}

  create(productRatePlanPriceLink: IProductRatePlanPriceLink): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(productRatePlanPriceLink);
    return this.http
      .post<IProductRatePlanPriceLink>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(productRatePlanPriceLink: IProductRatePlanPriceLink): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(productRatePlanPriceLink);
    return this.http
      .put<IProductRatePlanPriceLink>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IProductRatePlanPriceLink>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IProductRatePlanPriceLink[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(productRatePlanPriceLink: IProductRatePlanPriceLink): IProductRatePlanPriceLink {
    const copy: IProductRatePlanPriceLink = Object.assign({}, productRatePlanPriceLink, {
      migrateAt:
        productRatePlanPriceLink.migrateAt && productRatePlanPriceLink.migrateAt.isValid()
          ? productRatePlanPriceLink.migrateAt.format(DATE_FORMAT)
          : undefined,
      updatedAt:
        productRatePlanPriceLink.updatedAt && productRatePlanPriceLink.updatedAt.isValid()
          ? productRatePlanPriceLink.updatedAt.format(DATE_FORMAT)
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
      res.body.forEach((productRatePlanPriceLink: IProductRatePlanPriceLink) => {
        productRatePlanPriceLink.migrateAt = productRatePlanPriceLink.migrateAt ? moment(productRatePlanPriceLink.migrateAt) : undefined;
        productRatePlanPriceLink.updatedAt = productRatePlanPriceLink.updatedAt ? moment(productRatePlanPriceLink.updatedAt) : undefined;
      });
    }
    return res;
  }
}
