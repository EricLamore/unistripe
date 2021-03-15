import { Moment } from 'moment';
import { IUniproxyConso } from 'app/shared/model/uniproxy-conso.model';

export interface ISignatureDetails {
  id?: number;
  firstName?: string;
  lastName?: string;
  sigantureDate?: Moment;
  sigantureCount?: number;
  uniproxyConso?: IUniproxyConso;
}

export class SignatureDetails implements ISignatureDetails {
  constructor(
    public id?: number,
    public firstName?: string,
    public lastName?: string,
    public sigantureDate?: Moment,
    public sigantureCount?: number,
    public uniproxyConso?: IUniproxyConso
  ) {}
}
