import { Moment } from 'moment';

export interface IStripeSubscription {
  id?: number;
  stripeId?: string;
  stripeEmail?: string;
  subscriptionId?: string;
  migrateAt?: Moment;
  updatedAt?: Moment;
}

export class StripeSubscription implements IStripeSubscription {
  constructor(
    public id?: number,
    public stripeId?: string,
    public stripeEmail?: string,
    public subscriptionId?: string,
    public migrateAt?: Moment,
    public updatedAt?: Moment
  ) {}
}
