import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICustomerOrganizationLink } from 'app/shared/model/customer-organization-link.model';

type EntityResponseType = HttpResponse<ICustomerOrganizationLink>;
type EntityArrayResponseType = HttpResponse<ICustomerOrganizationLink[]>;

@Injectable({ providedIn: 'root' })
export class CustomerOrganizationLinkService {
  public resourceUrl = SERVER_API_URL + 'api/customer-organization-links';

  constructor(protected http: HttpClient) {}

  create(customerOrganizationLink: ICustomerOrganizationLink): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(customerOrganizationLink);
    return this.http
      .post<ICustomerOrganizationLink>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(customerOrganizationLink: ICustomerOrganizationLink): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(customerOrganizationLink);
    return this.http
      .put<ICustomerOrganizationLink>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICustomerOrganizationLink>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICustomerOrganizationLink[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(customerOrganizationLink: ICustomerOrganizationLink): ICustomerOrganizationLink {
    const copy: ICustomerOrganizationLink = Object.assign({}, customerOrganizationLink, {
      organisationRegisterDate:
        customerOrganizationLink.organisationRegisterDate && customerOrganizationLink.organisationRegisterDate.isValid()
          ? customerOrganizationLink.organisationRegisterDate.format(DATE_FORMAT)
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.organisationRegisterDate = res.body.organisationRegisterDate ? moment(res.body.organisationRegisterDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((customerOrganizationLink: ICustomerOrganizationLink) => {
        customerOrganizationLink.organisationRegisterDate = customerOrganizationLink.organisationRegisterDate
          ? moment(customerOrganizationLink.organisationRegisterDate)
          : undefined;
      });
    }
    return res;
  }
}
