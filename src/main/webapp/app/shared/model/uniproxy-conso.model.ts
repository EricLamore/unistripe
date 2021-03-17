import { ISignatureDetails } from 'app/shared/model/signature-details.model';
import { TYPE } from 'app/shared/model/enumerations/type.model';

export interface IUniproxyConso {
  id?: number;
  subscriptionId?: string;
  orgId?: string;
  month?: number;
  year?: number;
  type?: TYPE;
  conso?: number;
  signatureDetails?: ISignatureDetails[];
}

export class UniproxyConso implements IUniproxyConso {
  constructor(
    public id?: number,
    public subscriptionId?: string,
    public orgId?: string,
    public month?: number,
    public year?: number,
    public type?: TYPE,
    public conso?: number,
    public signatureDetails?: ISignatureDetails[]
  ) {}
}
