import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISignatureDetails } from 'app/shared/model/signature-details.model';

type EntityResponseType = HttpResponse<ISignatureDetails>;
type EntityArrayResponseType = HttpResponse<ISignatureDetails[]>;

@Injectable({ providedIn: 'root' })
export class SignatureDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/signature-details';

  constructor(protected http: HttpClient) {}

  create(signatureDetails: ISignatureDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(signatureDetails);
    return this.http
      .post<ISignatureDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(signatureDetails: ISignatureDetails): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(signatureDetails);
    return this.http
      .put<ISignatureDetails>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ISignatureDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISignatureDetails[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(signatureDetails: ISignatureDetails): ISignatureDetails {
    const copy: ISignatureDetails = Object.assign({}, signatureDetails, {
      sigantureDate:
        signatureDetails.sigantureDate && signatureDetails.sigantureDate.isValid() ? signatureDetails.sigantureDate.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.sigantureDate = res.body.sigantureDate ? moment(res.body.sigantureDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((signatureDetails: ISignatureDetails) => {
        signatureDetails.sigantureDate = signatureDetails.sigantureDate ? moment(signatureDetails.sigantureDate) : undefined;
      });
    }
    return res;
  }
}
