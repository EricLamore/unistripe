import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { UnistripeSharedModule } from 'app/shared/shared.module';
import { StripeSubscriptionComponent } from './stripe-subscription.component';
import { StripeSubscriptionDetailComponent } from './stripe-subscription-detail.component';
import { StripeSubscriptionUpdateComponent } from './stripe-subscription-update.component';
import { StripeSubscriptionDeleteDialogComponent } from './stripe-subscription-delete-dialog.component';
import { stripeSubscriptionRoute } from './stripe-subscription.route';

@NgModule({
  imports: [UnistripeSharedModule, RouterModule.forChild(stripeSubscriptionRoute)],
  declarations: [
    StripeSubscriptionComponent,
    StripeSubscriptionDetailComponent,
    StripeSubscriptionUpdateComponent,
    StripeSubscriptionDeleteDialogComponent,
  ],
  entryComponents: [StripeSubscriptionDeleteDialogComponent],
})
export class UnistripeStripeSubscriptionModule {}
