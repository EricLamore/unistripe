import { Moment } from 'moment';
import { IPrice } from 'app/shared/model/price.model';

export interface IOrganization {
  id?: number;
  organizationId?: string;
  address?: string;
  city?: string;
  country?: string;
  name?: string;
  registerDate?: Moment;
  status?: number;
  zipCode?: string;
  individual?: boolean;
  price?: IPrice;
}

export class Organization implements IOrganization {
  constructor(
    public id?: number,
    public organizationId?: string,
    public address?: string,
    public city?: string,
    public country?: string,
    public name?: string,
    public registerDate?: Moment,
    public status?: number,
    public zipCode?: string,
    public individual?: boolean,
    public price?: IPrice
  ) {
    this.individual = this.individual || false;
  }
}
