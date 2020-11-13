import { StatusType } from 'app/shared/model/enumerations/status-type.model';

export interface IEventUses {
  id?: number;
  cusotmerId?: string;
  customerName?: string;
  subscriptionId?: string;
  subscriptionName?: string;
  productId?: string;
  productName?: string;
  month?: number;
  year?: number;
  status?: StatusType;
  error?: string;
  total?: number;
}

export class EventUses implements IEventUses {
  constructor(
    public id?: number,
    public cusotmerId?: string,
    public customerName?: string,
    public subscriptionId?: string,
    public subscriptionName?: string,
    public productId?: string,
    public productName?: string,
    public month?: number,
    public year?: number,
    public status?: StatusType,
    public error?: string,
    public total?: number
  ) {}
}
