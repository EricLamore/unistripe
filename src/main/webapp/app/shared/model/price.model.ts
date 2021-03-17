import { IOrganization } from 'app/shared/model/organization.model';
import { IStripeSubscription } from 'app/shared/model/stripe-subscription.model';

export interface IPrice {
  id?: number;
  stripeId?: string;
  nickname?: string;
  productId?: string;
  organizations?: IOrganization[];
  subscription?: IStripeSubscription;
}

export class Price implements IPrice {
  constructor(
    public id?: number,
    public stripeId?: string,
    public nickname?: string,
    public productId?: string,
    public organizations?: IOrganization[],
    public subscription?: IStripeSubscription
  ) {}
}
