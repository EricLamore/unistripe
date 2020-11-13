import { Moment } from 'moment';

export interface ICustomerOrganizationLink {
  id?: number;
  organisationId?: string;
  organisationName?: string;
  organisationRegisterDate?: Moment;
  customerId?: string;
  customerName?: string;
}

export class CustomerOrganizationLink implements ICustomerOrganizationLink {
  constructor(
    public id?: number,
    public organisationId?: string,
    public organisationName?: string,
    public organisationRegisterDate?: Moment,
    public customerId?: string,
    public customerName?: string
  ) {}
}
