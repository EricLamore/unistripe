import { Moment } from 'moment';

export interface IBillingStripeLink {
  id?: number;
  stripeId?: string;
  stripeEmail?: string;
  customerId?: string;
  customerName?: string;
  migrateAt?: Moment;
  updatedAt?: Moment;
}

export class BillingStripeLink implements IBillingStripeLink {
  constructor(
    public id?: number,
    public stripeId?: string,
    public stripeEmail?: string,
    public customerId?: string,
    public customerName?: string,
    public migrateAt?: Moment,
    public updatedAt?: Moment
  ) {}
}
