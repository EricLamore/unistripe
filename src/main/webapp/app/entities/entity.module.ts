import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'event-uses',
        loadChildren: () => import('./event-uses/event-uses.module').then(m => m.UnistripeEventUsesModule),
      },
      {
        path: 'product',
        loadChildren: () => import('./product/product.module').then(m => m.UnistripeProductModule),
      },
      {
        path: 'customer-organization-link',
        loadChildren: () =>
          import('./customer-organization-link/customer-organization-link.module').then(m => m.UnistripeCustomerOrganizationLinkModule),
      },
      {
        path: 'billing-customer',
        loadChildren: () => import('./billing-customer/billing-customer.module').then(m => m.UnistripeBillingCustomerModule),
      },
      {
        path: 'billing-stripe-link',
        loadChildren: () => import('./billing-stripe-link/billing-stripe-link.module').then(m => m.UnistripeBillingStripeLinkModule),
      },
      {
        path: 'product-rate-plan-price-link',
        loadChildren: () =>
          import('./product-rate-plan-price-link/product-rate-plan-price-link.module').then(m => m.UnistripeProductRatePlanPriceLinkModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class UnistripeEntityModule {}
