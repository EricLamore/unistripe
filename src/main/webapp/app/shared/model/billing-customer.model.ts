import { Moment } from 'moment';

export interface IBillingCustomer {
  id?: number;
  taxNo?: string;
  thirdPartyAccountingCode?: string;
  siret?: string;
  ownerId?: string;
  isParticular?: boolean;
  partner?: boolean;
  partnerId?: string;
  customerId?: string;
  customerName?: string;
  stripeId?: string;
  stripeEmail?: string;
  migrateAt?: Moment;
  updatedAt?: Moment;
}

export class BillingCustomer implements IBillingCustomer {
  constructor(
    public id?: number,
    public taxNo?: string,
    public thirdPartyAccountingCode?: string,
    public siret?: string,
    public ownerId?: string,
    public isParticular?: boolean,
    public partner?: boolean,
    public partnerId?: string,
    public customerId?: string,
    public customerName?: string,
    public stripeId?: string,
    public stripeEmail?: string,
    public migrateAt?: Moment,
    public updatedAt?: Moment
  ) {
    this.isParticular = this.isParticular || false;
    this.partner = this.partner || false;
  }
}
