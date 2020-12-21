import { Moment } from 'moment';

export interface IProductRatePlanPriceLink {
  id?: number;
  stripeAboId?: string;
  stripeAboNickName?: string;
  stripeConsoId?: string;
  stripeConsoNickName?: string;
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
    public stripeAboId?: string,
    public stripeAboNickName?: string,
    public stripeConsoId?: string,
    public stripeConsoNickName?: string,
    public productId?: string,
    public productName?: string,
    public productRatePlanId?: string,
    public productRatePlanName?: string,
    public migrateAt?: Moment,
    public updatedAt?: Moment
  ) {}
}
