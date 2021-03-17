import { Moment } from 'moment';

export interface IBills {
  id?: number;
  customerId?: string;
  customerName?: string;
  customerEmail?: string;
  year?: number;
  month?: number;
  periodStart?: Moment;
  periodEnd?: Moment;
  created?: Moment;
  send?: Moment;
  dueDate?: Moment;
  amountDue?: number;
  total?: number;
  tax?: number;
  totalDiscountAmounts?: number;
  totalTaxAmounts?: number;
  url?: string;
  pdfUrl?: string;
}

export class Bills implements IBills {
  constructor(
    public id?: number,
    public customerId?: string,
    public customerName?: string,
    public customerEmail?: string,
    public year?: number,
    public month?: number,
    public periodStart?: Moment,
    public periodEnd?: Moment,
    public created?: Moment,
    public send?: Moment,
    public dueDate?: Moment,
    public amountDue?: number,
    public total?: number,
    public tax?: number,
    public totalDiscountAmounts?: number,
    public totalTaxAmounts?: number,
    public url?: string,
    public pdfUrl?: string
  ) {}
}
