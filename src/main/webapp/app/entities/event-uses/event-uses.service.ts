import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEventUses } from 'app/shared/model/event-uses.model';

type EntityResponseType = HttpResponse<IEventUses>;
type EntityArrayResponseType = HttpResponse<IEventUses[]>;

@Injectable({ providedIn: 'root' })
export class EventUsesService {
  public resourceUrl = SERVER_API_URL + 'api/event-uses';

  constructor(protected http: HttpClient) {}

  create(eventUses: IEventUses): Observable<EntityResponseType> {
    return this.http.post<IEventUses>(this.resourceUrl, eventUses, { observe: 'response' });
  }

  update(eventUses: IEventUses): Observable<EntityResponseType> {
    return this.http.put<IEventUses>(this.resourceUrl, eventUses, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEventUses>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEventUses[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
