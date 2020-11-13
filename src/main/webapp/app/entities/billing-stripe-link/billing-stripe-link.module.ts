import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { UnistripeSharedModule } from 'app/shared/shared.module';
import { BillingStripeLinkComponent } from './billing-stripe-link.component';
import { BillingStripeLinkDetailComponent } from './billing-stripe-link-detail.component';
import { BillingStripeLinkUpdateComponent } from './billing-stripe-link-update.component';
import { BillingStripeLinkDeleteDialogComponent } from './billing-stripe-link-delete-dialog.component';
import { billingStripeLinkRoute } from './billing-stripe-link.route';

@NgModule({
  imports: [UnistripeSharedModule, RouterModule.forChild(billingStripeLinkRoute)],
  declarations: [
    BillingStripeLinkComponent,
    BillingStripeLinkDetailComponent,
    BillingStripeLinkUpdateComponent,
    BillingStripeLinkDeleteDialogComponent,
  ],
  entryComponents: [BillingStripeLinkDeleteDialogComponent],
})
export class UnistripeBillingStripeLinkModule {}
