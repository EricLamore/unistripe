import { Moment } from 'moment';

export interface IProductRatePlanPriceLink {
  id?: number;
  stripeId?: string;
  stripeNickName?: string;
  productRatePlanId?: string;
  productRatePlanName?: string;
  migrateAt?: Moment;
  updatedAt?: Moment;
}

export class ProductRatePlanPriceLink implements IProductRatePlanPriceLink {
  constructor(
    public id?: number,
    public stripeId?: string,
    public stripeNickName?: string,
    public productRatePlanId?: string,
    public productRatePlanName?: string,
    public migrateAt?: Moment,
    public updatedAt?: Moment
  ) {}
}
