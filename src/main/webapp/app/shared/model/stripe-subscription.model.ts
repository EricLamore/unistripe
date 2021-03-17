import { Moment } from 'moment';
import { IPrice } from 'app/shared/model/price.model';

export interface IStripeSubscription {
  id?: number;
  stripeId?: string;
  stripeEmail?: string;
  taxeRate?: number;
  migrateAt?: Moment;
  updatedAt?: Moment;
  prices?: IPrice[];
}

export class StripeSubscription implements IStripeSubscription {
  constructor(
    public id?: number,
    public stripeId?: string,
    public stripeEmail?: string,
    public taxeRate?: number,
    public migrateAt?: Moment,
    public updatedAt?: Moment,
    public prices?: IPrice[]
  ) {}
}
