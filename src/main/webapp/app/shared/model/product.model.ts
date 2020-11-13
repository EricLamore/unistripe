export interface IProduct {
  id?: number;
  productName?: string;
  productId?: string;
  calculator?: string;
}

export class Product implements IProduct {
  constructor(public id?: number, public productName?: string, public productId?: string, public calculator?: string) {}
}
