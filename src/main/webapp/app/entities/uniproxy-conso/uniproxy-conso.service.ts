import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUniproxyConso } from 'app/shared/model/uniproxy-conso.model';

type EntityResponseType = HttpResponse<IUniproxyConso>;
type EntityArrayResponseType = HttpResponse<IUniproxyConso[]>;

@Injectable({ providedIn: 'root' })
export class UniproxyConsoService {
  public resourceUrl = SERVER_API_URL + 'api/uniproxy-consos';

  constructor(protected http: HttpClient) {}

  create(uniproxyConso: IUniproxyConso): Observable<EntityResponseType> {
    return this.http.post<IUniproxyConso>(this.resourceUrl, uniproxyConso, { observe: 'response' });
  }

  update(uniproxyConso: IUniproxyConso): Observable<EntityResponseType> {
    return this.http.put<IUniproxyConso>(this.resourceUrl, uniproxyConso, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IUniproxyConso>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IUniproxyConso[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
