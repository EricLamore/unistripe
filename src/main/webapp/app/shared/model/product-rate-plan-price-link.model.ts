import { Moment } from 'moment';

export interface IProductRatePlanPriceLink {
  id?: number;
  stripeId?: string;
  stripeNickName?: string;
  productId?: string;
  productName?: string;
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
    public productId?: string,
    public productName?: string,
    public productRatePlanId?: string,
    public productRatePlanName?: string,
    public migrateAt?: Moment,
    public updatedAt?: Moment
  ) {}
}
