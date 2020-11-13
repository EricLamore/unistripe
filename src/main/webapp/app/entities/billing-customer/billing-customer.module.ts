import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { UnistripeSharedModule } from 'app/shared/shared.module';
import { BillingCustomerComponent } from './billing-customer.component';
import { BillingCustomerDetailComponent } from './billing-customer-detail.component';
import { BillingCustomerUpdateComponent } from './billing-customer-update.component';
import { BillingCustomerDeleteDialogComponent } from './billing-customer-delete-dialog.component';
import { billingCustomerRoute } from './billing-customer.route';

@NgModule({
  imports: [UnistripeSharedModule, RouterModule.forChild(billingCustomerRoute)],
  declarations: [
    BillingCustomerComponent,
    BillingCustomerDetailComponent,
    BillingCustomerUpdateComponent,
    BillingCustomerDeleteDialogComponent,
  ],
  entryComponents: [BillingCustomerDeleteDialogComponent],
})
export class UnistripeBillingCustomerModule {}
