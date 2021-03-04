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
      {
        path: 'stripe-subscription',
        loadChildren: () => import('./stripe-subscription/stripe-subscription.module').then(m => m.UnistripeStripeSubscriptionModule),
      },
      {
        path: 'uniproxy-conso',
        loadChildren: () => import('./uniproxy-conso/uniproxy-conso.module').then(m => m.UnistripeUniproxyConsoModule),
      },
      {
        path: 'signature-details',
        loadChildren: () => import('./signature-details/signature-details.module').then(m => m.UnistripeSignatureDetailsModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class UnistripeEntityModule {}
